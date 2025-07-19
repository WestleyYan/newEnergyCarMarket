package com.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * User基于协同过滤推荐算法实现类
 */
public class UserBasedCollaborativeFiltering {

    private static final Logger logger = LoggerFactory.getLogger(UserBasedCollaborativeFiltering.class);


    // 存储User对物品的Rating
    private Map<String, Map<String, Double>> userRatings;
    // 存储物品被哪些UserRating过
    private Map<String, List<String>> itemUsers;
    // User到索引的映射
    private Map<String, Integer> userIndex;
    // 索引到User的映射
    private Map<Integer, String> indexUser;
    // 存储User之间相似度的稀疏矩阵
    private Long[][] sparseMatrix;

    /**
     * 构造函数，初始化UserRating数据并构建稀疏矩阵
     *
     * @param userRatings User对物品的Rating
     */
    public UserBasedCollaborativeFiltering(Map<String, Map<String, Double>> userRatings) {
        this.userRatings = userRatings;
        this.itemUsers = new HashMap<>();

        this.userIndex = new HashMap<>();//辅助存储每一个User的User索引index映射:user->index
        this.indexUser = new HashMap<>();//辅助存储每一个索引index对应的User映射:index->user

        // 构建物品-User倒排表
        int keyIndex = 0;
        for (String user : userRatings.keySet()) {
            Map<String, Double> ratings = userRatings.get(user);
            for (String item : ratings.keySet()) {
                if (!itemUsers.containsKey(item)) {
                    itemUsers.put(item, new ArrayList<>());
                }
                itemUsers.get(item).add(user);
            }
            //UserID与稀疏矩阵建立对应关系
            this.userIndex.put(user, keyIndex);
            this.indexUser.put(keyIndex, user);
            keyIndex++;
        }

        int N = userRatings.size();
        this.sparseMatrix = new Long[N][N];//建立User稀疏矩阵，用于User相似度计算【相似度矩阵】
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                this.sparseMatrix[i][j] = (long) 0;
        }
        for (String item : itemUsers.keySet()) {
            List<String> userList = itemUsers.get(item);
            for (String u1 : userList) {
                for (String u2 : userList) {
                    if (u1.equals(u2)) {
                        continue;
                    }
                    this.sparseMatrix[this.userIndex.get(u1)][this.userIndex.get(u2)] += 1;
                }
            }
        }

    }

    /**
     * 计算两个User的相似度
     *
     * @param user1 User1
     * @param user2 User2
     * @return 两个User的相似度
     */
    public double calculateSimilarity(String user1, String user2) {
        Integer id1 = this.userIndex.get(user1);
        Integer id2 = this.userIndex.get(user2);
        if (id1 == null || id2 == null) return 0.0;
        return this.sparseMatrix[id1][id2] / Math.sqrt(userRatings.get(indexUser.get(id1)).size() * userRatings.get(indexUser.get(id2)).size());
    }

    /**
     * 为目标User推荐物品
     *
     * @param targetUser         目标User
     * @param numRecommendations 推荐物品的数量
     * @return 推荐的物品列表
     */
    public List<String> recommendItems(String targetUser, int numRecommendations) {
        // 计算目标User与其他User的相似度
        Map<String, Double> userSimilarities = new HashMap<>();
        for (String user : userRatings.keySet()) {
            if (!user.equals(targetUser)) {
                double similarity = calculateSimilarity(targetUser, user);
                userSimilarities.put(user, similarity);
            }
        }

        // 对User相似度进行降序排序
        List<Map.Entry<String, Double>> sortedSimilarities = new ArrayList<>(userSimilarities.entrySet());
        sortedSimilarities.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 选择最相似的User作为推荐来源
        List<String> similarUsers = new ArrayList<>();
        for (int i = 0; i < numRecommendations; i++) {
            if (i < sortedSimilarities.size()) {
                similarUsers.add(sortedSimilarities.get(i).getKey());
            } else {
                break;
            }
        }

        // 根据最相似User喜欢的物品来生成推荐列表
        Map<String, Double> recommendations = new HashMap<>();
        for (String user : similarUsers) {
            Map<String, Double> ratings = userRatings.get(user);
            for (String item : ratings.keySet()) {
                if (userRatings.get(targetUser) != null && !userRatings.get(targetUser).containsKey(item)) {
                    recommendations.put(item, ratings.get(item));
                }
            }
        }

        // 对推荐物品进行排序并返回前N个
        LinkedHashMap<String, Double> sortedRecommendations = new LinkedHashMap<>(recommendations);
        int numItems = Math.min(numRecommendations, sortedRecommendations.size());
        sortedRecommendations = sortedRecommendations.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Double>comparingByValue().reversed())).limit(numItems)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<String> result = new ArrayList<String>();
        result.addAll(sortedRecommendations.keySet());
        return result;
    }

///////////////////////////////////////////////以下为新版本协同推荐算法逻辑///////////////////////////////////////////////////////

    public UserBasedCollaborativeFiltering() {

    }

    /**
     * 推荐Product
     *
     * @param entities           实体列表
     * @param x                  User字段名
     * @param y                  Product字段名
     * @param xValue             当前UserID
     * @param numRecommendations 推荐数量
     * @return 推荐的ProductID列表
     */
    public List<String> recommendItems(List entities, String x, String y, String xValue, int numRecommendations) {
        List<String> recommendations = new ArrayList<>();
        try {
            logger.info("开始为User {} 推荐物品", xValue);

            // 构建稀疏x-y矩阵
            Map<String, Map<String, Double>> xMatrix = buildSparseMatrix(entities, x, y, "");

            // 计算当前x的相似度得分
            Map<String, Double> similarityScores = calculateSimilarityScores(xMatrix, xValue);

            // 为当前x生成推荐列表
            recommendations = generateRecommendations(xMatrix, similarityScores, xValue, numRecommendations);

            logger.info("成功为User {} 推荐了 {} 个物品: {}", xValue, recommendations.size(), recommendations);
        } catch (Exception e) {
            logger.error("在推荐物品时发生异常", e);
        } finally {
            return recommendations;
        }
    }

    /**
     * 推荐Product
     *
     * @param entities           实体列表
     * @param x                  User字段名
     * @param y                  Product字段名
     * @param xValue             当前UserID
     * @param yRegex             y字段值的分割正则表达式
     * @param numRecommendations 推荐数量
     * @return 推荐的ProductID列表
     */
    public List<String> recommendItems(List entities, String x, String y, String xValue, String yRegex, int numRecommendations) {
        List<String> recommendations = new ArrayList<>();
        try {
            logger.info("开始为User {} 推荐物品", xValue);
            // 构建稀疏x-y矩阵
            Map<String, Map<String, Double>> xMatrix = buildSparseMatrix(entities, x, y, StringUtils.isNotBlank(yRegex) ? yRegex : "");

            // 计算当前x的相似度得分
            Map<String, Double> similarityScores = calculateSimilarityScores(xMatrix, xValue);

            // 为当前x生成推荐y列表
            recommendations = generateRecommendations(xMatrix, similarityScores, xValue, numRecommendations);
            logger.info("成功为User {} 推荐了 {} 个物品: {}", xValue, recommendations.size(), recommendations);
        } catch (Exception e) {
            logger.error("在推荐物品时发生异常", e);
        } finally {
            return recommendations;
        }
    }

    /**
     * 构建稀疏矩阵
     *
     * @param entities 实体列表
     * @param x        User字段名
     * @param y        Product字段名
     * @param yRegex   y字段值的分割正则表达式
     * @return 稀疏矩阵
     * @throws NoSuchFieldException   如果字段不存在
     * @throws IllegalAccessException 如果字段访问被拒绝
     */
    private Map<String, Map<String, Double>> buildSparseMatrix(
            List<Object> entities, String x, String y, String yRegex)
            throws NoSuchFieldException, IllegalAccessException {
        Map<String, Map<String, Double>> matrix = new HashMap<>();
        logger.info("开始构建稀疏矩阵");
        for (Object entity : entities) {
            // 通过反射获取字段
            Field xFid = entity.getClass().getDeclaredField(x);
            Field yFid = entity.getClass().getDeclaredField(y);
            // 设置可访问性
            xFid.setAccessible(true);
            yFid.setAccessible(true);
            // 获取字段值
            String xId = xFid.get(entity) + "";
            String yId = yFid.get(entity) + "";

            logger.info("处理实体: x={}, y={}", xId, yId);
            // 分割y字段值，并在矩阵中对应位置设置值为1.0
            for (String val : StringUtils.isNotBlank(yRegex) ? yId.split(yRegex) : yId.split(",")) {
                // 使用computeIfAbsent确保每个UserID都有一个对应的Map
                matrix.computeIfAbsent(xId, k -> new HashMap<>()).put(val, 1.0);
            }
        }
        logger.info("稀疏矩阵构建完成");
        return matrix;
    }

    /**
     * 计算相似度得分
     *
     * @param xMatrix       稀疏矩阵
     * @param currentUserid 当前UserID
     * @return 相似度得分
     */
    private Map<String, Double> calculateSimilarityScores(Map<String, Map<String, Double>> xMatrix, String currentUserid) {
        Map<String, Double> currentUserPrefs = xMatrix.get(currentUserid);
        Map<String, Double> similarityScores = new HashMap<>();

        logger.info("开始计算User {} 的相似度得分", currentUserid);

        if (!CollectionUtils.isEmpty(currentUserPrefs)) {
            for (Map.Entry<String, Map<String, Double>> entry : xMatrix.entrySet()) {
                String otherUserid = entry.getKey();
                if (!otherUserid.equals(currentUserid)) {
                    Map<String, Double> otherUserPrefs = entry.getValue();
                    double similarity = calculateCosineSimilarity(currentUserPrefs, otherUserPrefs);
                    if (similarity > 0) {
                        similarityScores.put(otherUserid, similarity);
                        logger.info("User {} 和User {} 的相似度为 {}", currentUserid, otherUserid, similarity);
                    }
                }
            }
        }
        logger.info("User {} 的相似度得分计算完成，共找到 {} 个相似User", currentUserid, similarityScores.size());
        return similarityScores;
    }

    /**
     * 计算余弦相似度
     *
     * @param prefs1 User偏好
     * @param prefs2 另一个User偏好
     * @return 余弦相似度
     */
    private double calculateCosineSimilarity(Map<String, Double> prefs1, Map<String, Double> prefs2) {
        // 计算两个User的共同偏好Product
        Set<String> commonKeys = new HashSet<>(prefs1.keySet());
        commonKeys.retainAll(prefs2.keySet());

        // 如果没有共同偏好，则相似度为0
        if (commonKeys.isEmpty()) {
            return 0.0;
        }

        // 计算点积
        double dotProduct = commonKeys.stream()
                .mapToDouble(key -> prefs1.get(key) * prefs2.get(key))
                .sum();

        // 计算两个User的偏好向量的范数
        double normUser1 = Math.sqrt(prefs1.values().stream().mapToDouble(i -> i * i).sum());
        double normUser2 = Math.sqrt(prefs2.values().stream().mapToDouble(i -> i * i).sum());

        // 返回余弦相似度
        return dotProduct / (normUser1 * normUser2);
    }

    /**
     * 生成推荐列表
     *
     * @param xMatrix            稀疏矩阵
     * @param similarityScores   相似度得分
     * @param currentUserid      当前UserID
     * @param numRecommendations 推荐数量
     * @return 推荐的ProductID列表
     */
    public List<String> generateRecommendations(Map<String, Map<String, Double>> xMatrix, Map<String, Double> similarityScores, String currentUserid, int numRecommendations) {
        Map<String, Double> productScores = new HashMap<>();

        logger.info("开始为User {} 生成推荐列表", currentUserid);

        for (Map.Entry<String, Double> similarUser : similarityScores.entrySet()) {
            String xSimilar = similarUser.getKey();
            double similarityScore = similarUser.getValue();
            Map<String, Double> xSimilarPrefs = xMatrix.get(xSimilar);

            for (Map.Entry<String, Double> productEntry : xSimilarPrefs.entrySet()) {
                String y = productEntry.getKey();
                // 只考虑当前User未收藏的Product
                if (!xMatrix.getOrDefault(currentUserid, Collections.emptyMap()).containsKey(y)) {
                    productScores.merge(y, similarityScore, Double::sum);
                }
            }
        }

        // 根据y得分进行排序，并返回前numRecommendations个Product

        List<String> recommendations = productScores.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(numRecommendations)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        logger.info("为User {} 生成的推荐列表大小为 {}: {}", currentUserid, recommendations.size(), recommendations);

        return recommendations;
    }

}