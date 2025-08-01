package com.entity.model;

import com.entity.OrdersEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 订单
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
public class OrdersModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
	 * ProductImage
	 */
	
	private String picture;
		
	/**
	 * 购买数量
	 */
	
	private Integer buynumber;
		
	/**
	 * Price
	 */
	
	private Double price;
		
	/**
	 * 总Price
	 */
	
	private Double total;
		
	/**
	 * 支付类型
	 */
	
	private Integer type;
		
	/**
	 * 状态
	 */
	
	private String status;
		
	/**
	 * 地址
	 */
	
	private String address;
		
	/**
	 * 电话
	 */
	
	private String tel;
		
	/**
	 * 收货人
	 */
	
	private String consignee;
		
	/**
	 * 物流
	 */
	
	private String logistics;
		
	/**
	 * 备注
	 */
	
	private String remark;
		
	/**
	 * 是否审核
	 */
	
	private String sfsh;
		
	/**
	 * 审核回复
	 */
	
	private String shhf;
		
	/**
	 * User角色
	 */
	
	private String role;
		
	/**
	 * 退货原因
	 */
	
	private String returnreason;
				
	
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
	 * 设置：ProductImage
	 */
	 
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	/**
	 * 获取：ProductImage
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
	 * 设置：Price
	 */
	 
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 获取：Price
	 */
	public Double getPrice() {
		return price;
	}
				
	
	/**
	 * 设置：总Price
	 */
	 
	public void setTotal(Double total) {
		this.total = total;
	}
	
	/**
	 * 获取：总Price
	 */
	public Double getTotal() {
		return total;
	}
				
	
	/**
	 * 设置：支付类型
	 */
	 
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 获取：支付类型
	 */
	public Integer getType() {
		return type;
	}
				
	
	/**
	 * 设置：状态
	 */
	 
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
				
	
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
	 * 设置：电话
	 */
	 
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/**
	 * 获取：电话
	 */
	public String getTel() {
		return tel;
	}
				
	
	/**
	 * 设置：收货人
	 */
	 
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	/**
	 * 获取：收货人
	 */
	public String getConsignee() {
		return consignee;
	}
				
	
	/**
	 * 设置：物流
	 */
	 
	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}
	
	/**
	 * 获取：物流
	 */
	public String getLogistics() {
		return logistics;
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
				
	
	/**
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
				
	
	/**
	 * 设置：User角色
	 */
	 
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * 获取：User角色
	 */
	public String getRole() {
		return role;
	}
				
	
	/**
	 * 设置：退货原因
	 */
	 
	public void setReturnreason(String returnreason) {
		this.returnreason = returnreason;
	}
	
	/**
	 * 获取：退货原因
	 */
	public String getReturnreason() {
		return returnreason;
	}
			
}
