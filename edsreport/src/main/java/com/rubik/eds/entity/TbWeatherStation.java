package com.rubik.eds.entity;

import com.rubik.support.entity.BaseEntity;

public class TbWeatherStation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -883370222585617019L;
	private String stationId;
	private String stationName;
	private String stationTele;
	private String stationArea;
	private String remarks;
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getStationTele() {
		return stationTele;
	}
	public void setStationTele(String stationTele) {
		this.stationTele = stationTele;
	}
	public String getStationArea() {
		return stationArea;
	}
	public void setStationArea(String stationArea) {
		this.stationArea = stationArea;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
