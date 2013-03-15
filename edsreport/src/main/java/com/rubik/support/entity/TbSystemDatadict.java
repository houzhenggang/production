package com.rubik.support.entity;

public class TbSystemDatadict extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3388016875817375327L;
	private String code;
	private String type;
	private String name;
	private String value;
	private Integer order;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}
