package com.entity.vo;

import com.entity.StoreupEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 收藏表
 * @author 
 * @email 
 * @date 2025-06-03 21:49:05
 */
public class StoreupVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * Productid
	 */
	
	private Long refid;
		
	/**
	 * 表名
	 */
	
	private String tablename;
		
	/**
	 * 名称
	 */
	
	private String name;
		
	/**
	 * Image
	 */
	
	private String picture;
		
	/**
	 * 类型
	 */
	
	private String type;
		
	/**
	 * 推荐类型
	 */
	
	private String inteltype;
		
	/**
	 * 备注
	 */
	
	private String remark;
				
	
	/**
	 * 设置：Productid
	 */
	 
	public void setRefid(Long refid) {
		this.refid = refid;
	}
	
	/**
	 * 获取：Productid
	 */
	public Long getRefid() {
		return refid;
	}
				
	
	/**
	 * 设置：表名
	 */
	 
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	
	/**
	 * 获取：表名
	 */
	public String getTablename() {
		return tablename;
	}
				
	
	/**
	 * 设置：名称
	 */
	 
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
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
	 * 设置：类型
	 */
	 
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
				
	
	/**
	 * 设置：推荐类型
	 */
	 
	public void setInteltype(String inteltype) {
		this.inteltype = inteltype;
	}
	
	/**
	 * 获取：推荐类型
	 */
	public String getInteltype() {
		return inteltype;
	}
				
	
	/**
	 * 设置：备注
	 */
	 
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
			
}
