package com.entity.view;

import com.entity.RemenqicheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * Popular Cars
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
@TableName("remenqiche")
public class RemenqicheView  extends RemenqicheEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public RemenqicheView(){
	}
 
 	public RemenqicheView(RemenqicheEntity remenqicheEntity){
 	try {
			BeanUtils.copyProperties(this, remenqicheEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
