package com.entity.vo;

import com.entity.AddressEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 地址
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
public class AddressVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 地址
	 */
	
	private String address;
		
	/**
	 * 收货人
	 */
	
	private String name;
		
	/**
	 * 电话
	 */
	
	private String phone;
		
	/**
	 * 是否默认地址[是/否]
	 */
	
	private String isdefault;
				
	
	/**
	 * 设置：地址
	 */
	 
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
				
	
	/**
	 * 设置：收货人
	 */
	 
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取：收货人
	 */
	public String getName() {
		return name;
	}
				
	
	/**
	 * 设置：电话
	 */
	 
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
	}
				
	
	/**
	 * 设置：是否默认地址[是/否]
	 */
	 
	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}
	
	/**
	 * 获取：是否默认地址[是/否]
	 */
	public String getIsdefault() {
		return isdefault;
	}
			
}
