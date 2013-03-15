/**
 * 
 */
package com.rubik.support.entity;

/**
 * @author Administrator
 *
 */
public class TbRoleAuthority extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6181202175014722374L;
	private Integer roleId;
	private Integer authorityId;
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
	/**
	 * @return the authorityId
	 */
	public Integer getAuthorityId() {
		return authorityId;
	}
	/**
	 * @param authorityId the authorityId to set
	 */
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	
}
