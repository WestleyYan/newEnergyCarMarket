package com.entity.view;

import com.entity.ChargerecordEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 充值记录表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-06-03 21:49:05
 */
@TableName("chargerecord")
public class ChargerecordView  extends ChargerecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ChargerecordView(){
	}
 
 	public ChargerecordView(ChargerecordEntity chargerecordEntity){
 	try {
			BeanUtils.copyProperties(this, chargerecordEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
