package com.entity.view;

import com.entity.DiscussremenqicheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * Popular Cars评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-06-03 21:49:05
 */
@TableName("discussremenqiche")
public class DiscussremenqicheView  extends DiscussremenqicheEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussremenqicheView(){
	}
 
 	public DiscussremenqicheView(DiscussremenqicheEntity discussremenqicheEntity){
 	try {
			BeanUtils.copyProperties(this, discussremenqicheEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
