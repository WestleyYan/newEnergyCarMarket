package com.entity.model;

import com.entity.ChatEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 在线客服
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
public class ChatModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 管理员id
	 */
	
	private Long adminid;
		
	/**
	 * 提问
	 */
	
	private String ask;
		
	/**
	 * 回复
	 */
	
	private String reply;
		
	/**
	 * 是否回复
	 */
	
	private Integer isreply;
		
	/**
	 * 已读/未读(1:已读,0:未读)
	 */
	
	private Integer isread;
		
	/**
	 * User头像
	 */
	
	private String uname;
		
	/**
	 * User名
	 */
	
	private String uimage;
		
	/**
	 * 内容类型(1:文本,2:Image,3:视频,4:文件,5:表情)
	 */
	
	private Integer type;
				
	
	/**
	 * 设置：管理员id
	 */
	 
	public void setAdminid(Long adminid) {
		this.adminid = adminid;
	}
	
	/**
	 * 获取：管理员id
	 */
	public Long getAdminid() {
		return adminid;
	}
				
	
	/**
	 * 设置：提问
	 */
	 
	public void setAsk(String ask) {
		this.ask = ask;
	}
	
	/**
	 * 获取：提问
	 */
	public String getAsk() {
		return ask;
	}
				
	
	/**
	 * 设置：回复
	 */
	 
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	/**
	 * 获取：回复
	 */
	public String getReply() {
		return reply;
	}
				
	
	/**
	 * 设置：是否回复
	 */
	 
	public void setIsreply(Integer isreply) {
		this.isreply = isreply;
	}
	
	/**
	 * 获取：是否回复
	 */
	public Integer getIsreply() {
		return isreply;
	}
				
	
	/**
	 * 设置：已读/未读(1:已读,0:未读)
	 */
	 
	public void setIsread(Integer isread) {
		this.isread = isread;
	}
	
	/**
	 * 获取：已读/未读(1:已读,0:未读)
	 */
	public Integer getIsread() {
		return isread;
	}
				
	
	/**
	 * 设置：User头像
	 */
	 
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	/**
	 * 获取：User头像
	 */
	public String getUname() {
		return uname;
	}
				
	
	/**
	 * 设置：User名
	 */
	 
	public void setUimage(String uimage) {
		this.uimage = uimage;
	}
	
	/**
	 * 获取：User名
	 */
	public String getUimage() {
		return uimage;
	}
				
	
	/**
	 * 设置：内容类型(1:文本,2:Image,3:视频,4:文件,5:表情)
	 */
	 
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 获取：内容类型(1:文本,2:Image,3:视频,4:文件,5:表情)
	 */
	public Integer getType() {
		return type;
	}
			
}
