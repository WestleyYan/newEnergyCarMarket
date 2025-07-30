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

import com.entity.QichefenleiEntity;
import com.entity.view.QichefenleiView;

import com.service.QichefenleiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * Car Categories
 * 后端接口
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
@RestController
@RequestMapping("/qichefenlei")
public class QichefenleiController {
    @Autowired
    private QichefenleiService qichefenleiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QichefenleiEntity qichefenlei,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QichefenleiEntity> ew = new EntityWrapper<QichefenleiEntity>();


        //查询结果
		PageUtils page = qichefenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qichefenlei), params), params));
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
    public R list(@RequestParam Map<String, Object> params,QichefenleiEntity qichefenlei, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QichefenleiEntity> ew = new EntityWrapper<QichefenleiEntity>();

        //查询结果
		PageUtils page = qichefenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qichefenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QichefenleiEntity qichefenlei){
       	EntityWrapper<QichefenleiEntity> ew = new EntityWrapper<QichefenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qichefenlei, "qichefenlei")); 
        return R.ok().put("data", qichefenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QichefenleiEntity qichefenlei){
        EntityWrapper< QichefenleiEntity> ew = new EntityWrapper< QichefenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qichefenlei, "qichefenlei")); 
		QichefenleiView qichefenleiView =  qichefenleiService.selectView(ew);
		return R.ok("查询Car Categories成功").put("data", qichefenleiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QichefenleiEntity qichefenlei = qichefenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qichefenlei,deSens);
        return R.ok().put("data", qichefenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QichefenleiEntity qichefenlei = qichefenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qichefenlei,deSens);
        return R.ok().put("data", qichefenlei);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QichefenleiEntity qichefenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(qichefenleiService.selectCount(new EntityWrapper<QichefenleiEntity>().eq("qichefenlei", qichefenlei.getQichefenlei()))>0) {
            return R.error("Car Categories已存在");
        }
        //ValidatorUtils.validateEntity(qichefenlei);
        qichefenleiService.insert(qichefenlei);
        return R.ok().put("data",qichefenlei.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QichefenleiEntity qichefenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(qichefenleiService.selectCount(new EntityWrapper<QichefenleiEntity>().eq("qichefenlei", qichefenlei.getQichefenlei()))>0) {
            return R.error("Car Categories已存在");
        }
        //ValidatorUtils.validateEntity(qichefenlei);
        qichefenleiService.insert(qichefenlei);
        return R.ok().put("data",qichefenlei.getId());
    }





    /**
     * UPDATE
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QichefenleiEntity qichefenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qichefenlei);
        //验证字段唯一性，否则返回错误信息
        if(qichefenleiService.selectCount(new EntityWrapper<QichefenleiEntity>().ne("id", qichefenlei.getId()).eq("qichefenlei", qichefenlei.getQichefenlei()))>0) {
            return R.error("Car Categories已存在");
        }
        //全部更新
        qichefenleiService.updateById(qichefenlei);
        return R.ok();
    }



    

    /**
     * DELETE
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        qichefenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
