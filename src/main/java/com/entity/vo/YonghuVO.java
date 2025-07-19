package com.entity.vo;

import com.entity.YonghuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * User
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
public class YonghuVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 密码
	 */
	
	private String mima;
		
	/**
	 * NAME
	 */
	
	private String xingming;
		
	/**
	 * SEX
	 */
	
	private String xingbie;
		
	/**
	 * PHONE
	 */
	
	private String shouji;
		
	/**
	 * 头像
	 */
	
	private String touxiang;
		
	/**
	 * 余额
	 */
	
	private Double money;
				
	
	/**
	 * 设置：密码
	 */
	 
	public void setMima(String mima) {
		this.mima = mima;
	}
	
	/**
	 * 获取：密码
	 */
	public String getMima() {
		return mima;
	}
				
	
	/**
	 * 设置：NAME
	 */
	 
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	/**
	 * 获取：NAME
	 */
	public String getXingming() {
		return xingming;
	}
				
	
	/**
	 * 设置：SEX
	 */
	 
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}
	
	/**
	 * 获取：SEX
	 */
	public String getXingbie() {
		return xingbie;
	}
				
	
	/**
	 * 设置：PHONE
	 */
	 
	public void setShouji(String shouji) {
		this.shouji = shouji;
	}
	
	/**
	 * 获取：PHONE
	 */
	public String getShouji() {
		return shouji;
	}
				
	
	/**
	 * 设置：头像
	 */
	 
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	
	/**
	 * 获取：头像
	 */
	public String getTouxiang() {
		return touxiang;
	}
				
	
	/**
	 * 设置：余额
	 */
	 
	public void setMoney(Double money) {
		this.money = money;
	}
	
	/**
	 * 获取：余额
	 */
	public Double getMoney() {
		return money;
	}
			
}
