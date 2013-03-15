 package com.rubik.support.entity;
 
 import java.util.Date;

 public class TbGbLoginLog extends BaseEntity
 {
   private static final long serialVersionUID = 1L;
   private Integer id;
   private Integer userId;
   private String loginName;
   private String nickname;
   private Date loginTime;
   private String loginIp;
   private Boolean isSuccess;
   private String comments;
/**
 * @return the id
 */
public Integer getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(Integer id) {
	this.id = id;
}
/**
 * @return the userId
 */
public Integer getUserId() {
	return userId;
}
/**
 * @param userId the userId to set
 */
public void setUserId(Integer userId) {
	this.userId = userId;
}
/**
 * @return the loginName
 */
public String getLoginName() {
	return loginName;
}
/**
 * @param loginName the loginName to set
 */
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
/**
 * @return the nickname
 */
public String getNickname() {
	return nickname;
}
/**
 * @param nickname the nickname to set
 */
public void setNickname(String nickname) {
	this.nickname = nickname;
}
/**
 * @return the loginTime
 */
public Date getLoginTime() {
	return loginTime;
}
/**
 * @param loginTime the loginTime to set
 */
public void setLoginTime(Date loginTime) {
	this.loginTime = loginTime;
}
/**
 * @return the loginIp
 */
public String getLoginIp() {
	return loginIp;
}
/**
 * @param loginIp the loginIp to set
 */
public void setLoginIp(String loginIp) {
	this.loginIp = loginIp;
}
/**
 * @return the isSuccess
 */
public Boolean getIsSuccess() {
	return isSuccess;
}
/**
 * @param isSuccess the isSuccess to set
 */
public void setIsSuccess(Boolean isSuccess) {
	this.isSuccess = isSuccess;
}
/**
 * @return the comments
 */
public String getComments() {
	return comments;
}
/**
 * @param comments the comments to set
 */
public void setComments(String comments) {
	this.comments = comments;
}
   
   
   
 }