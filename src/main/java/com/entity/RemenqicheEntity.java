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
 * Popular Cars
 * 数据库通用Actions实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
@TableName("remenqiche")
public class RemenqicheEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public RemenqicheEntity() {
		
	}
	
	public RemenqicheEntity(T t) {
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
	 * Car Name
	 */
					
	private String qichemingcheng;
	
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
	 * 设置：Car Name
	 */
	public void setQichemingcheng(String qichemingcheng) {
		this.qichemingcheng = qichemingcheng;
	}
	/**
	 * 获取：Car Name
	 */
	public String getQichemingcheng() {
		return qichemingcheng;
	}
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
