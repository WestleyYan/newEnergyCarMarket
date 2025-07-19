package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DiscussremenqicheEntity;
import com.entity.view.DiscussremenqicheView;

import com.service.DiscussremenqicheService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * Popular Cars评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-06-03 21:49:05
 */
@RestController
@RequestMapping("/discussremenqiche")
public class DiscussremenqicheController {
    @Autowired
    private DiscussremenqicheService discussremenqicheService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussremenqicheEntity discussremenqiche,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussremenqicheEntity> ew = new EntityWrapper<DiscussremenqicheEntity>();


        //查询结果
		PageUtils page = discussremenqicheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussremenqiche), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussremenqicheEntity discussremenqiche, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussremenqicheEntity> ew = new EntityWrapper<DiscussremenqicheEntity>();

        //查询结果
		PageUtils page = discussremenqicheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussremenqiche), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussremenqicheEntity discussremenqiche){
       	EntityWrapper<DiscussremenqicheEntity> ew = new EntityWrapper<DiscussremenqicheEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussremenqiche, "discussremenqiche")); 
        return R.ok().put("data", discussremenqicheService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussremenqicheEntity discussremenqiche){
        EntityWrapper< DiscussremenqicheEntity> ew = new EntityWrapper< DiscussremenqicheEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussremenqiche, "discussremenqiche")); 
		DiscussremenqicheView discussremenqicheView =  discussremenqicheService.selectView(ew);
		return R.ok("查询Popular Cars评论表成功").put("data", discussremenqicheView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussremenqicheEntity discussremenqiche = discussremenqicheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussremenqiche,deSens);
        return R.ok().put("data", discussremenqiche);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussremenqicheEntity discussremenqiche = discussremenqicheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussremenqiche,deSens);
        return R.ok().put("data", discussremenqiche);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussremenqicheEntity discussremenqiche, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussremenqiche);
        discussremenqicheService.insert(discussremenqiche);
        return R.ok().put("data",discussremenqiche.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussremenqicheEntity discussremenqiche, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussremenqiche);
        discussremenqicheService.insert(discussremenqiche);
        return R.ok().put("data",discussremenqiche.getId());
    }



     /**
     * 获取User密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussremenqicheEntity discussremenqiche = discussremenqicheService.selectOne(new EntityWrapper<DiscussremenqicheEntity>().eq("", username));
        return R.ok().put("data", discussremenqiche);
    }


    /**
     * UPDATE
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussremenqicheEntity discussremenqiche, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussremenqiche);
        //全部更新
        discussremenqicheService.updateById(discussremenqiche);
        return R.ok();
    }



    

    /**
     * DELATE
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussremenqicheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussremenqicheEntity discussremenqiche, HttpServletRequest request,String pre){
        EntityWrapper<DiscussremenqicheEntity> ew = new EntityWrapper<DiscussremenqicheEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = discussremenqicheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussremenqiche), params), params));
        return R.ok().put("data", page);
    }








}
