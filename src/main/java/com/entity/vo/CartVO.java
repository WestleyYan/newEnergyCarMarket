package com.entity.vo;

import com.entity.CartEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 购物车表
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
public class CartVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * Userid
	 */
	
	private Long userid;
		
	/**
	 * Productid
	 */
	
	private Long goodid;
		
	/**
	 * Product Name
	 */
	
	private String goodname;
		
	/**
	 * Image
	 */
	
	private String picture;
		
	/**
	 * 购买数量
	 */
	
	private Integer buynumber;
		
	/**
	 * 单价
	 */
	
	private Double price;
				
	
	/**
	 * 设置：Userid
	 */
	 
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	/**
	 * 获取：Userid
	 */
	public Long getUserid() {
		return userid;
	}
				
	
	/**
	 * 设置：Productid
	 */
	 
	public void setGoodid(Long goodid) {
		this.goodid = goodid;
	}
	
	/**
	 * 获取：Productid
	 */
	public Long getGoodid() {
		return goodid;
	}
				
	
	/**
	 * 设置：Product Name
	 */
	 
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	
	/**
	 * 获取：Product Name
	 */
	public String getGoodname() {
		return goodname;
	}
				
	
	/**
	 * 设置：Image
	 */
	 
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	/**
	 * 获取：Image
	 */
	public String getPicture() {
		return picture;
	}
				
	
	/**
	 * 设置：购买数量
	 */
	 
	public void setBuynumber(Integer buynumber) {
		this.buynumber = buynumber;
	}
	
	/**
	 * 获取：购买数量
	 */
	public Integer getBuynumber() {
		return buynumber;
	}
				
	
	/**
	 * 设置：单价
	 */
	 
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 获取：单价
	 */
	public Double getPrice() {
		return price;
	}
			
}
