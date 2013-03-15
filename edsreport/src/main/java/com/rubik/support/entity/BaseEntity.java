package com.rubik.support.entity;

import java.io.Serializable;

import org.apache.ibatis.session.RowBounds;

public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -187820130727361248L;
	private RowBounds bounds;
	private Integer pageNo = 1;
	private Integer pageSize = 15;
	private Integer totalCount=0;
	
	public RowBounds getBounds() {
		bounds = new RowBounds(this.getPageNo(), this.getPageSize());
		return bounds;
	}

	public void setBounds(RowBounds bounds) {
		this.bounds = bounds;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
