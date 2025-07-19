package com.entity.model;

import com.entity.XinnengyuanqicheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * New Energy Vehicles
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
public class XinnengyuanqicheModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * Image
	 */
	
	private String tupian;
		
	/**
	 * Car Categories
	 */
	
	private String qichefenlei;
		
	/**
	 * Specification
	 */
	
	private String guige;
		
	/**
	 * Features
	 */
	
	private String tedian;
		
	/**
	 * 汽车参数
	 */
	
	private String qichecanshu;
		
	/**
	 * Unit Limit
	 */
	
	private Integer onelimittimes;
		
	/**
	 * Stock
	 */
	
	private Integer alllimittimes;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
		
	/**
	 * Number of Reviews
	 */
	
	private Integer discussnum;
		
	/**
	 * Rating
	 */
	
	private Double totalscore;
		
	/**
	 * Price
	 */
	
	private Double price;
		
	/**
	 * Listed or Not(1:上架，0:下架)
	 */
	
	private Integer onshelves;
		
	/**
	 * Number of Favorites
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：Image
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：Image
	 */
	public String getTupian() {
		return tupian;
	}
				
	
	/**
	 * 设置：Car Categories
	 */
	 
	public void setQichefenlei(String qichefenlei) {
		this.qichefenlei = qichefenlei;
	}
	
	/**
	 * 获取：Car Categories
	 */
	public String getQichefenlei() {
		return qichefenlei;
	}
				
	
	/**
	 * 设置：Specification
	 */
	 
	public void setGuige(String guige) {
		this.guige = guige;
	}
	
	/**
	 * 获取：Specification
	 */
	public String getGuige() {
		return guige;
	}
				
	
	/**
	 * 设置：Features
	 */
	 
	public void setTedian(String tedian) {
		this.tedian = tedian;
	}
	
	/**
	 * 获取：Features
	 */
	public String getTedian() {
		return tedian;
	}
				
	
	/**
	 * 设置：汽车参数
	 */
	 
	public void setQichecanshu(String qichecanshu) {
		this.qichecanshu = qichecanshu;
	}
	
	/**
	 * 获取：汽车参数
	 */
	public String getQichecanshu() {
		return qichecanshu;
	}
				
	
	/**
	 * 设置：Unit Limit
	 */
	 
	public void setOnelimittimes(Integer onelimittimes) {
		this.onelimittimes = onelimittimes;
	}
	
	/**
	 * 获取：Unit Limit
	 */
	public Integer getOnelimittimes() {
		return onelimittimes;
	}
				
	
	/**
	 * 设置：Stock
	 */
	 
	public void setAlllimittimes(Integer alllimittimes) {
		this.alllimittimes = alllimittimes;
	}
	
	/**
	 * 获取：Stock
	 */
	public Integer getAlllimittimes() {
		return alllimittimes;
	}
				
	
	/**
	 * 设置：赞
	 */
	 
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
				
	
	/**
	 * 设置：踩
	 */
	 
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：Number of Reviews
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：Number of Reviews
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：Rating
	 */
	 
	public void setTotalscore(Double totalscore) {
		this.totalscore = totalscore;
	}
	
	/**
	 * 获取：Rating
	 */
	public Double getTotalscore() {
		return totalscore;
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
	 * 设置：Listed or Not(1:上架，0:下架)
	 */
	 
	public void setOnshelves(Integer onshelves) {
		this.onshelves = onshelves;
	}
	
	/**
	 * 获取：Listed or Not(1:上架，0:下架)
	 */
	public Integer getOnshelves() {
		return onshelves;
	}
				
	
	/**
	 * 设置：Number of Favorites
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：Number of Favorites
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
