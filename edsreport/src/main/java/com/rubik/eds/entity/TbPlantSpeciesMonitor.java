package com.rubik.eds.entity;

import java.util.Date;

public class TbPlantSpeciesMonitor extends ReportHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 728409019997443749L;

	private Integer id;
	private Integer userId;
	private Date operationTime;
	private String stationId;
	private String stationName;
	private TbPlantDetails plantDetails;
	/**
	 * 植物物种多样性监测月份
	 */
	private Date plantMonitorDate;

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
	 * @return the operationTime
	 */
	public Date getOperationTime() {
		return operationTime;
	}

	/**
	 * @param operationTime the operationTime to set
	 */
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	/**
	 * @return the stationId
	 */
	public String getStationId() {
		return stationId;
	}

	/**
	 * @param stationId the stationId to set
	 */
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	/**
	 * @return the stationName
	 */
	public String getStationName() {
		return stationName;
	}

	/**
	 * @param stationName the stationName to set
	 */
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	/**
	 * @return the plantDetails
	 */
	public TbPlantDetails getPlantDetails() {
		return plantDetails;
	}

	/**
	 * @param plantDetails the plantDetails to set
	 */
	public void setPlantDetails(TbPlantDetails plantDetails) {
		this.plantDetails = plantDetails;
	}

	/**
	 * 植物物种多样性监测月份
	 * @return the plantMonitorDate
	 */
	public Date getPlantMonitorDate() {
		return plantMonitorDate;
	}

	/**
	 * 植物物种多样性监测月份
	 * @param plantMonitorDate the plantMonitorDate to set
	 */
	public void setPlantMonitorDate(Date plantMonitorDate) {
		this.plantMonitorDate = plantMonitorDate;
	}
}
