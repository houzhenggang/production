/**
 * 
 */
package com.rubik.support.entity;

/**
 * @author Administrator
 *
 */
public class TbSystemRole extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2349472561924098944L;
	private Integer id;
	private String name;
	private String type;
	private Integer parentId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}
