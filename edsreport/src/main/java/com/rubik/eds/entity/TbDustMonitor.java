package com.rubik.eds.entity;

import java.util.Date;

public class TbDustMonitor extends ReportHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4026708084350069826L;

	private Integer id;
	private Integer userId;
	private Date operationTime;
	private String stationId;
	private String stationName;
	/**
	 * 降尘干重
	 */
	private Float dustDryWeight;
	/**
	 * 平均降尘干重
	 */
	private Float dustAvgDryWeight;
	/**
	 * 种类
	 */
	private String dustType;
	/**
	 * 持续开始时间
	 */
	private Date dustStartTime;
	/**
	 * 结束时间
	 */
	private Date dustEndTime;
	/**
	 * 备注
	 */
	private String remarks;
	
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
	 * 降尘干重
	 * @return the dustDryWeight
	 */
	public Float getDustDryWeight() {
		return dustDryWeight;
	}
	/**
	 * 降尘干重
	 * @param dustDryWeight the dustDryWeight to set
	 */
	public void setDustDryWeight(Float dustDryWeight) {
		this.dustDryWeight = dustDryWeight;
	}
	/**
	 * 平均降尘干重
	 * @return the dustAvgDryWeight
	 */
	public Float getDustAvgDryWeight() {
		return dustAvgDryWeight;
	}
	/**
	 * 平均降尘干重
	 * @param dustAvgDryWeight the dustAvgDryWeight to set
	 */
	public void setDustAvgDryWeight(Float dustAvgDryWeight) {
		this.dustAvgDryWeight = dustAvgDryWeight;
	}
	/**
	 * 沙尘天气种类
	 * @return the dustType
	 */
	public String getDustType() {
		return dustType;
	}
	/**
	 * 沙尘天气种类
	 * @param dustType the dustType to set
	 */
	public void setDustType(String dustType) {
		this.dustType = dustType;
	}
	/**
	 * 持续开始时间
	 * @return the dustStartTime
	 */
	public Date getDustStartTime() {
		return dustStartTime;
	}
	/**
	 * 持续开始时间
	 * @param dustStartTime the dustStartTime to set
	 */
	public void setDustStartTime(Date dustStartTime) {
		this.dustStartTime = dustStartTime;
	}
	/**
	 * 持续结束时间
	 * @return the dustEndTime
	 */
	public Date getDustEndTime() {
		return dustEndTime;
	}
	/**
	 * 持续结束时间
	 * @param dustEndTime the dustEndTime to set
	 */
	public void setDustEndTime(Date dustEndTime) {
		this.dustEndTime = dustEndTime;
	}
	/**
	 * 备注
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 备注
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
