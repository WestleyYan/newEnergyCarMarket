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

import com.entity.RemenqicheEntity;
import com.entity.view.RemenqicheView;

import com.service.RemenqicheService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * Popular Cars
 * 后端接口
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
@RestController
@RequestMapping("/remenqiche")
public class RemenqicheController {
    @Autowired
    private RemenqicheService remenqicheService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,RemenqicheEntity remenqiche,
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<RemenqicheEntity> ew = new EntityWrapper<RemenqicheEntity>();
        if(pricestart!=null) ew.ge("price", pricestart);
        if(priceend!=null) ew.le("price", priceend);


        //查询结果
		PageUtils page = remenqicheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, remenqiche), params), params));
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
    public R list(@RequestParam Map<String, Object> params,RemenqicheEntity remenqiche, 
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<RemenqicheEntity> ew = new EntityWrapper<RemenqicheEntity>();
        if(pricestart!=null) ew.ge("price", pricestart);
        if(priceend!=null) ew.le("price", priceend);

        //查询结果
		PageUtils page = remenqicheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, remenqiche), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( RemenqicheEntity remenqiche){
       	EntityWrapper<RemenqicheEntity> ew = new EntityWrapper<RemenqicheEntity>();
      	ew.allEq(MPUtil.allEQMapPre( remenqiche, "remenqiche")); 
        return R.ok().put("data", remenqicheService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(RemenqicheEntity remenqiche){
        EntityWrapper< RemenqicheEntity> ew = new EntityWrapper< RemenqicheEntity>();
 		ew.allEq(MPUtil.allEQMapPre( remenqiche, "remenqiche")); 
		RemenqicheView remenqicheView =  remenqicheService.selectView(ew);
		return R.ok("查询Popular Cars成功").put("data", remenqicheView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RemenqicheEntity remenqiche = remenqicheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(remenqiche,deSens);
        return R.ok().put("data", remenqiche);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RemenqicheEntity remenqiche = remenqicheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(remenqiche,deSens);
        return R.ok().put("data", remenqiche);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RemenqicheEntity remenqiche, HttpServletRequest request){
        //ValidatorUtils.validateEntity(remenqiche);
        remenqicheService.insert(remenqiche);
        return R.ok().put("data",remenqiche.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody RemenqicheEntity remenqiche, HttpServletRequest request){
        //ValidatorUtils.validateEntity(remenqiche);
        remenqicheService.insert(remenqiche);
        return R.ok().put("data",remenqiche.getId());
    }





    /**
     * UPDATE
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RemenqicheEntity remenqiche, HttpServletRequest request){
        //ValidatorUtils.validateEntity(remenqiche);
        //全部更新
        remenqicheService.updateById(remenqiche);
        return R.ok();
    }



    

    /**
     * DELETE
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        remenqicheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
