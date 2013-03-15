/**
 * 
 */
package com.rubik.support.entity;

/**
 * @author Administrator
 *
 */
public class TbSystemAuthority extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3634756307999722232L;
	private Integer id;
	private String name;
	private String authority;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}
	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
