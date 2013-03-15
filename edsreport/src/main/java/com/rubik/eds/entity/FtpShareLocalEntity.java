/**
 * 
 */
package com.rubik.eds.entity;

import java.util.Date;

import com.rubik.support.entity.BaseEntity;

/**
 * @author Administrator
 *
 */
public class FtpShareLocalEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 操作时间
	 */
	private Date operationTime;
	
	/**
	 * FTP地址
	 */
	private String ftpAddress;
	/**
	 * FTP主目录
	 */
	private String ftpBasePath;
	/**
	 * FTP端口
	 */
	private Integer ftpPort;
	/**
	 * FTP用户名
	 */
	private String ftpUsername;
	/**
	 * FTP用户密码
	 */
	private String ftpPassword;
	/**
	 * 共享保存主目录
	 */
	private String shareBasePath;;
	
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
	 * 用户ID
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 用户ID
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 操作时间
	 * @return the operationTime
	 */
	public Date getOperationTime() {
		return operationTime;
	}
	/**
	 * 操作时间
	 * @param operationTime the operationTime to set
	 */
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	/**
	 * FTP地址
	 * @return the ftpAddress
	 */
	public String getFtpAddress() {
		return ftpAddress;
	}
	/**
	 * FTP地址
	 * @param ftpAddress the ftpAddress to set
	 */
	public void setFtpAddress(String ftpAddress) {
		this.ftpAddress = ftpAddress;
	}
	/**
	 * FTP主目录
	 * @return the ftpBasePath
	 */
	public String getFtpBasePath() {
		return ftpBasePath;
	}
	/**
	 * FTP主目录
	 * @param ftpBasePath the ftpBasePath to set
	 */
	public void setFtpBasePath(String ftpBasePath) {
		this.ftpBasePath = ftpBasePath;
	}
	/**
	 * FTP端口
	 * @return the ftpPort
	 */
	public Integer getFtpPort() {
		return ftpPort;
	}
	/**
	 * FTP端口
	 * @param ftpPort the ftpPort to set
	 */
	public void setFtpPort(Integer ftpPort) {
		this.ftpPort = ftpPort;
	}
	/**
	 * FTP用户名
	 * @return the ftpUsername
	 */
	public String getFtpUsername() {
		return ftpUsername;
	}
	/**
	 * FTP用户名
	 * @param ftpUsername the ftpUsername to set
	 */
	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}
	/**
	 * FTP用户密码
	 * @return the ftpPassword
	 */
	public String getFtpPassword() {
		return ftpPassword;
	}
	/**
	 * FTP用户密码
	 * @param ftpPassword the ftpPassword to set
	 */
	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}
	/**
	 * 共享保存主目录
	 * @return the shareBasePath
	 */
	public String getShareBasePath() {
		return shareBasePath;
	}
	/**
	 * 共享保存主目录
	 * @param shareBasePath the shareBasePath to set
	 */
	public void setShareBasePath(String shareBasePath) {
		this.shareBasePath = shareBasePath;
	}
	
}
