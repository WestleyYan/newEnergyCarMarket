package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 购物车表
 * 数据库通用Actions实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
@TableName("cart")
public class CartEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public CartEntity() {
		
	}
	
	public CartEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * Product表名
	 */
					
	private String tablename;
	
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
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：Product表名
	 */
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	/**
	 * 获取：Product表名
	 */
	public String getTablename() {
		return tablename;
	}
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
