/**
 * 
 */
package com.rubik.support.entity;

/**
 * @author Administrator
 *
 */
public class TbResourceAuthority extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2355649768113021392L;
	private Integer resourceId;
	private Integer authorityId;
	/**
	 * @return the resourceId
	 */
	public Integer getResourceId() {
		return resourceId;
	}
	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
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
