package com.entity.view;

import com.entity.DiscussxinnengyuanqicheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * New Energy Vehicles评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-06-03 21:49:05
 */
@TableName("discussxinnengyuanqiche")
public class DiscussxinnengyuanqicheView  extends DiscussxinnengyuanqicheEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussxinnengyuanqicheView(){
	}
 
 	public DiscussxinnengyuanqicheView(DiscussxinnengyuanqicheEntity discussxinnengyuanqicheEntity){
 	try {
			BeanUtils.copyProperties(this, discussxinnengyuanqicheEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
