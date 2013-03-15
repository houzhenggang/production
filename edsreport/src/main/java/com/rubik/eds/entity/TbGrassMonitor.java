package com.rubik.eds.entity;

import java.util.Date;

public class TbGrassMonitor extends ReportHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8371005883405230627L;

	private Integer id;
	private Integer userId;
	private Date operationTime;
	private String stationId;
	private String stationName;
	/**
	 * 监测地段
	 */
	private String monitorArea;
	/**
	 * 牧草名称
	 */
	private String grassName;
	/**
	 * 牧草种类
	 */
	private String grassType;
	/**
	 * 返春期
	 */
	private String backDate;
	/**
	 * 开花期
	 */
	private String flowerDate;
	/**
	 * 黄枯期
	 */
	private String yellowDate;
	/**
	 * 绝对高度
	 */
	private String absoluteHeigth;
	/**
	 * 草层高度
	 */
	private String grassHeigth;
	/**
	 * 盖度
	 */
	private String coverDegree;
	/**
	 * 地上生物量
	 */
	private String earthBiomass;
	/**
	 * 优良牧草比例
	 */
	private String betterGrassRate;
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
	 * 监测地段
	 * @return the monitorArea
	 */
	public String getMonitorArea() {
		return monitorArea;
	}
	/**
	 * 监测地段
	 * @param monitorArea the monitorArea to set
	 */
	public void setMonitorArea(String monitorArea) {
		this.monitorArea = monitorArea;
	}
	/**
	 * 牧草名称
	 * @return the grassName
	 */
	public String getGrassName() {
		return grassName;
	}
	/**
	 * 牧草名称
	 * @param grassName the grassName to set
	 */
	public void setGrassName(String grassName) {
		this.grassName = grassName;
	}
	/**
	 * 牧草类型
	 * @return the grassType
	 */
	public String getGrassType() {
		return grassType;
	}
	/**
	 * 牧草类型
	 * @param grassType the grassType to set
	 */
	public void setGrassType(String grassType) {
		this.grassType = grassType;
	}
	/**
	 * 返春期
	 * @return the backDate
	 */
	public String getBackDate() {
		return backDate;
	}
	/**
	 * 返春期
	 * @param backDate the backDate to set
	 */
	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}
	/**
	 * 开花期
	 * @return the flowerDate
	 */
	public String getFlowerDate() {
		return flowerDate;
	}
	/**
	 * 开花期
	 * @param flowerDate the flowerDate to set
	 */
	public void setFlowerDate(String flowerDate) {
		this.flowerDate = flowerDate;
	}
	/**
	 * 黄枯期
	 * @return the yellowDate
	 */
	public String getYellowDate() {
		return yellowDate;
	}
	/**
	 * 黄枯期
	 * @param yellowDate the yellowDate to set
	 */
	public void setYellowDate(String yellowDate) {
		this.yellowDate = yellowDate;
	}
	/**
	 * 绝对高度
	 * @return the absoluteHeigth
	 */
	public String getAbsoluteHeigth() {
		return absoluteHeigth;
	}
	/**
	 * 绝对高度
	 * @param absoluteHeigth the absoluteHeigth to set
	 */
	public void setAbsoluteHeigth(String absoluteHeigth) {
		this.absoluteHeigth = absoluteHeigth;
	}
	/**
	 * 草层高度
	 * @return the grassHeigth
	 */
	public String getGrassHeigth() {
		return grassHeigth;
	}
	/**
	 * 草层高度
	 * @param grassHeigth the grassHeigth to set
	 */
	public void setGrassHeigth(String grassHeigth) {
		this.grassHeigth = grassHeigth;
	}
	/**
	 * 盖度
	 * @return the coverDegree
	 */
	public String getCoverDegree() {
		return coverDegree;
	}
	/**
	 * 盖度
	 * @param coverDegree the coverDegree to set
	 */
	public void setCoverDegree(String coverDegree) {
		this.coverDegree = coverDegree;
	}
	/**
	 * 地面生物量
	 * @return the earthBiomass
	 */
	public String getEarthBiomass() {
		return earthBiomass;
	}
	/**
	 * 地面生物量
	 * @param earthBiomass the earthBiomass to set
	 */
	public void setEarthBiomass(String earthBiomass) {
		this.earthBiomass = earthBiomass;
	}
	/**
	 * 优良牧草比例
	 * @return the betterGrassRate
	 */
	public String getBetterGrassRate() {
		return betterGrassRate;
	}
	/**
	 * 优良牧草比例
	 * @param betterGrassRate the betterGrassRate to set
	 */
	public void setBetterGrassRate(String betterGrassRate) {
		this.betterGrassRate = betterGrassRate;
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
