/**
 * 
 */
package com.rubik.support.entity;

/**
 * @author Administrator
 *
 */
public class TbUserRole extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5286124708082992311L;
	private Integer userId;
	private Integer roleId;
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
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}
