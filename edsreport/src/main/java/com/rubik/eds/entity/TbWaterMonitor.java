package com.rubik.eds.entity;

import java.util.Date;

public class TbWaterMonitor extends ReportHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3537455998028018560L;

	private Integer id;
	/**
	 * 操作时间
	 */
	private Date operationTime;
	/**
	 * 站点ID
	 */
	private String stationId;
	private String stationName;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 水体名称
	 */
	private String waterName;
	/**
	 * 取样点经度
	 */
	private String fetchLongitude;
	/**
	 * 取样点纬度
	 */
	private String fetchLatitude;
	/**
	 * 拐点经度
	 */
	private String turnLongitude;
	/**
	 * 拐点纬度
	 */
	private String turnLatitude;
	/**
	 * 水体面积
	 */
	private String waterArea;
	/**
	 * 水位
	 */
	private String waterLevel;
	/**
	 * 透明度
	 */
	private String waterOpacity;
	/**
	 * 水体颜色
	 */
	private String waterColor;
	/**
	 * 水体温度
	 */
	private String waterTemperature;
	/**
	 * 水体PH值
	 */
	private String waterPh;
	/**
	 * 水体全盐含量
	 */
	private String waterTotalSalt;
	/**
	 * 氯化物含量
	 */
	private String waterChlorine;
	/**
	 * 硫化物含量
	 */
	private String waterSulfide;
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
	 * 操作时间
	 * @return the operationTime
	 */
	public Date getOperationTime() {
		return operationTime;
	}
	/**
	 * 站点ID
	 * @param operationTime the operationTime to set
	 */
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	/**
	 * 站点ID
	 * @return the stationId
	 */
	public String getStationId() {
		return stationId;
	}
	/**
	 * 用户ID
	 * @param stationId the stationId to set
	 */
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
	/**
	 * 站名
	 * @return the stationName
	 */
	public String getStationName() {
		return stationName;
	}
	/**
	 * 站名
	 * @param stationName the stationName to set
	 */
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	/**
	 * 用户ID
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 用户ID
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 水体名称
	 * @return the waterName
	 */
	public String getWaterName() {
		return waterName;
	}
	/**
	 * 水体名称
	 * @param waterName the waterName to set
	 */
	public void setWaterName(String waterName) {
		this.waterName = waterName;
	}
	/**
	 * 取样点经度
	 * @return the fetchLongitude
	 */
	public String getFetchLongitude() {
		return fetchLongitude;
	}
	/**
	 * 取样点经度
	 * @param fetchLongitude the fetchLongitude to set
	 */
	public void setFetchLongitude(String fetchLongitude) {
		this.fetchLongitude = fetchLongitude;
	}
	/**
	 * 取样点纬度
	 * @return the fetchLatitude
	 */
	public String getFetchLatitude() {
		return fetchLatitude;
	}
	/**
	 * 取样点纬度
	 * @param fetchLatitude the fetchLatitude to set
	 */
	public void setFetchLatitude(String fetchLatitude) {
		this.fetchLatitude = fetchLatitude;
	}
	/**
	 * 拐点经度
	 * @return the turnLongitude
	 */
	public String getTurnLongitude() {
		return turnLongitude;
	}
	/**
	 * 拐点经度
	 * @param turnLongitude the turnLongitude to set
	 */
	public void setTurnLongitude(String turnLongitude) {
		this.turnLongitude = turnLongitude;
	}
	/**
	 * 拐点纬度
	 * @return the turnLatitude
	 */
	public String getTurnLatitude() {
		return turnLatitude;
	}
	/**
	 * 拐点纬度
	 * @param turnLatitude the turnLatitude to set
	 */
	public void setTurnLatitude(String turnLatitude) {
		this.turnLatitude = turnLatitude;
	}
	/**
	 * 水体面积
	 * @return the waterArea
	 */
	public String getWaterArea() {
		return waterArea;
	}
	/**
	 * 水体面积
	 * @param waterArea the waterArea to set
	 */
	public void setWaterArea(String waterArea) {
		this.waterArea = waterArea;
	}
	/**
	 * 水位
	 * @return the waterLevel
	 */
	public String getWaterLevel() {
		return waterLevel;
	}
	/**
	 * 水位
	 * @param waterLevel the waterLevel to set
	 */
	public void setWaterLevel(String waterLevel) {
		this.waterLevel = waterLevel;
	}
	/**
	 * 水体透明度
	 * @return the waterOpacity
	 */
	public String getWaterOpacity() {
		return waterOpacity;
	}
	/**
	 * 水体透明度
	 * @param waterOpacity the waterOpacity to set
	 */
	public void setWaterOpacity(String waterOpacity) {
		this.waterOpacity = waterOpacity;
	}
	/**
	 * 水体颜色
	 * @return the waterColor
	 */
	public String getWaterColor() {
		return waterColor;
	}
	/**
	 * 水体颜色
	 * @param waterColor the waterColor to set
	 */
	public void setWaterColor(String waterColor) {
		this.waterColor = waterColor;
	}
	/**
	 * 水体温度
	 * @return the waterTemperature
	 */
	public String getWaterTemperature() {
		return waterTemperature;
	}
	/**
	 * 水体温度
	 * @param waterTemperature the waterTemperature to set
	 */
	public void setWaterTemperature(String waterTemperature) {
		this.waterTemperature = waterTemperature;
	}
	/**
	 * 水体PH值
	 * @return the waterPh
	 */
	public String getWaterPh() {
		return waterPh;
	}
	/**
	 * 水体PH值
	 * @param waterPh the waterPh to set
	 */
	public void setWaterPh(String waterPh) {
		this.waterPh = waterPh;
	}
	/**
	 * 全盐含量
	 * @return the waterTotalSalt
	 */
	public String getWaterTotalSalt() {
		return waterTotalSalt;
	}
	/**
	 * 全盐含量
	 * @param waterTotalSalt the waterTotalSalt to set
	 */
	public void setWaterTotalSalt(String waterTotalSalt) {
		this.waterTotalSalt = waterTotalSalt;
	}
	/**
	 * 氯化物含量
	 * @return the waterChlorine
	 */
	public String getWaterChlorine() {
		return waterChlorine;
	}
	/**
	 * 氯化物含量
	 * @param waterChlorine the waterChlorine to set
	 */
	public void setWaterChlorine(String waterChlorine) {
		this.waterChlorine = waterChlorine;
	}
	/**
	 * 硫化物含量
	 * @return the waterSulfide
	 */
	public String getWaterSulfide() {
		return waterSulfide;
	}
	/**
	 * 硫化物含量
	 * @param waterSulfide the waterSulfide to set
	 */
	public void setWaterSulfide(String waterSulfide) {
		this.waterSulfide = waterSulfide;
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
