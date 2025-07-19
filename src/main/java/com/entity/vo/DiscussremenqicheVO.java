package com.entity.vo;

import com.entity.DiscussremenqicheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * Popular Cars评论表
 * @author 
 * @email 
 * @date 2025-06-03 21:49:05
 */
public class DiscussremenqicheVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * Userid
	 */
	
	private Long userid;
		
	/**
	 * 头像
	 */
	
	private String avatarurl;
		
	/**
	 * User名
	 */
	
	private String nickname;
		
	/**
	 * 评论内容
	 */
	
	private String content;
		
	/**
	 * Rating
	 */
	
	private Double score;
		
	/**
	 * 回复内容
	 */
	
	private String reply;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
		
	/**
	 * 置顶(1:置顶,0:非置顶)
	 */
	
	private Integer istop;
		
	/**
	 * 赞Userids
	 */
	
	private String tuserids;
		
	/**
	 * 踩Userids
	 */
	
	private String cuserids;
				
	
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
	 * 设置：头像
	 */
	 
	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}
	
	/**
	 * 获取：头像
	 */
	public String getAvatarurl() {
		return avatarurl;
	}
				
	
	/**
	 * 设置：User名
	 */
	 
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 获取：User名
	 */
	public String getNickname() {
		return nickname;
	}
				
	
	/**
	 * 设置：评论内容
	 */
	 
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取：评论内容
	 */
	public String getContent() {
		return content;
	}
				
	
	/**
	 * 设置：Rating
	 */
	 
	public void setScore(Double score) {
		this.score = score;
	}
	
	/**
	 * 获取：Rating
	 */
	public Double getScore() {
		return score;
	}
				
	
	/**
	 * 设置：回复内容
	 */
	 
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	/**
	 * 获取：回复内容
	 */
	public String getReply() {
		return reply;
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
	 * 设置：置顶(1:置顶,0:非置顶)
	 */
	 
	public void setIstop(Integer istop) {
		this.istop = istop;
	}
	
	/**
	 * 获取：置顶(1:置顶,0:非置顶)
	 */
	public Integer getIstop() {
		return istop;
	}
				
	
	/**
	 * 设置：赞Userids
	 */
	 
	public void setTuserids(String tuserids) {
		this.tuserids = tuserids;
	}
	
	/**
	 * 获取：赞Userids
	 */
	public String getTuserids() {
		return tuserids;
	}
				
	
	/**
	 * 设置：踩Userids
	 */
	 
	public void setCuserids(String cuserids) {
		this.cuserids = cuserids;
	}
	
	/**
	 * 获取：踩Userids
	 */
	public String getCuserids() {
		return cuserids;
	}
			
}
