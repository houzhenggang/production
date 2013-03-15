package com.rubik.eds.entity;

import com.rubik.support.entity.BaseEntity;

public class TbUserStation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2606770288946487429L;
	private Integer userId;
	private String stationId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
}
