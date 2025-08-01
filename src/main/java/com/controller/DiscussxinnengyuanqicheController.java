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

import com.entity.DiscussxinnengyuanqicheEntity;
import com.entity.view.DiscussxinnengyuanqicheView;

import com.service.DiscussxinnengyuanqicheService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * New Energy Vehicles评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-06-03 21:49:05
 */
@RestController
@RequestMapping("/discussxinnengyuanqiche")
public class DiscussxinnengyuanqicheController {
    @Autowired
    private DiscussxinnengyuanqicheService discussxinnengyuanqicheService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussxinnengyuanqicheEntity discussxinnengyuanqiche,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussxinnengyuanqicheEntity> ew = new EntityWrapper<DiscussxinnengyuanqicheEntity>();


        //查询结果
		PageUtils page = discussxinnengyuanqicheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussxinnengyuanqiche), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussxinnengyuanqicheEntity discussxinnengyuanqiche, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussxinnengyuanqicheEntity> ew = new EntityWrapper<DiscussxinnengyuanqicheEntity>();

        //查询结果
		PageUtils page = discussxinnengyuanqicheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussxinnengyuanqiche), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussxinnengyuanqicheEntity discussxinnengyuanqiche){
       	EntityWrapper<DiscussxinnengyuanqicheEntity> ew = new EntityWrapper<DiscussxinnengyuanqicheEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussxinnengyuanqiche, "discussxinnengyuanqiche")); 
        return R.ok().put("data", discussxinnengyuanqicheService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussxinnengyuanqicheEntity discussxinnengyuanqiche){
        EntityWrapper< DiscussxinnengyuanqicheEntity> ew = new EntityWrapper< DiscussxinnengyuanqicheEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussxinnengyuanqiche, "discussxinnengyuanqiche")); 
		DiscussxinnengyuanqicheView discussxinnengyuanqicheView =  discussxinnengyuanqicheService.selectView(ew);
		return R.ok("查询New Energy Vehicles评论表成功").put("data", discussxinnengyuanqicheView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussxinnengyuanqicheEntity discussxinnengyuanqiche = discussxinnengyuanqicheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussxinnengyuanqiche,deSens);
        return R.ok().put("data", discussxinnengyuanqiche);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussxinnengyuanqicheEntity discussxinnengyuanqiche = discussxinnengyuanqicheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussxinnengyuanqiche,deSens);
        return R.ok().put("data", discussxinnengyuanqiche);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussxinnengyuanqicheEntity discussxinnengyuanqiche, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussxinnengyuanqiche);
        discussxinnengyuanqicheService.insert(discussxinnengyuanqiche);
        return R.ok().put("data",discussxinnengyuanqiche.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussxinnengyuanqicheEntity discussxinnengyuanqiche, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussxinnengyuanqiche);
        discussxinnengyuanqicheService.insert(discussxinnengyuanqiche);
        return R.ok().put("data",discussxinnengyuanqiche.getId());
    }



     /**
     * 获取User密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussxinnengyuanqicheEntity discussxinnengyuanqiche = discussxinnengyuanqicheService.selectOne(new EntityWrapper<DiscussxinnengyuanqicheEntity>().eq("", username));
        return R.ok().put("data", discussxinnengyuanqiche);
    }


    /**
     * UPDATE
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussxinnengyuanqicheEntity discussxinnengyuanqiche, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussxinnengyuanqiche);
        //全部更新
        discussxinnengyuanqicheService.updateById(discussxinnengyuanqiche);
        return R.ok();
    }



    

    /**
     * DELETE
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussxinnengyuanqicheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussxinnengyuanqicheEntity discussxinnengyuanqiche, HttpServletRequest request,String pre){
        EntityWrapper<DiscussxinnengyuanqicheEntity> ew = new EntityWrapper<DiscussxinnengyuanqicheEntity>();
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

		PageUtils page = discussxinnengyuanqicheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussxinnengyuanqiche), params), params));
        return R.ok().put("data", page);
    }








}
