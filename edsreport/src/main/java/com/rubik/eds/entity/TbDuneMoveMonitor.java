package com.rubik.eds.entity;

import java.util.Date;

public class TbDuneMoveMonitor extends ReportHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6963047765452009043L;

	private Integer id;
	private Integer userId;
	private Date operationTime;
	private String stationId;
	private String stationName;
	
	/**
	 * 监测日期
	 */
	private Date duneMoveMonitorDate;
	/**
	 * 被监测沙丘经度
	 */
	private String duneMoveLongtitude;
	/**
	 * 被监测沙丘纬度
	 */
	private String duneMoveLatitude;
	/**
	 * 沙丘海拔高度
	 */
	private Float duneAltitude;
	/**
	 * 沙丘高度变化
	 */
	private Float duneHeight;
	/**
	 * 沙丘迎风坡脚移动距离
	 */
	private Float duneWindwardSlope;
	/**
	 * 沙丘丘顶移动距离
	 */
	private Float duneHilltop;
	/**
	 * 沙丘背风坡脚移动距离
	 */
	private Float duneLeewardSlope;
	/**
	 * 沙丘迎风坡脚方位角
	 */
	private Float duneWindPosition;
	/**
	 * 沙丘丘顶方位角
	 */
	private Float hilltopPosition;
	
	/**
	 * 沙丘背风坡脚方位角
	 */
	private Float leewardPosition;
	/**
	 * 风向
	 */
	private String windDirection;
	/**
	 * 风速
	 */
	private Float windSpeed;
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
	 * 监测日期
	 * @return the duneMoveMonitorDate
	 */
	public Date getDuneMoveMonitorDate() {
		return duneMoveMonitorDate;
	}
	/**
	 * 监测日期
	 * @param duneMoveMonitorDate the duneMoveMonitorDate to set
	 */
	public void setDuneMoveMonitorDate(Date duneMoveMonitorDate) {
		this.duneMoveMonitorDate = duneMoveMonitorDate;
	}
	
	/**
	 * 被监测沙丘经度
	 * @return the duneMoveLongtitude
	 */
	public String getDuneMoveLongtitude() {
		return duneMoveLongtitude;
	}
	/**
	 *  被监测沙丘经度
	 * @param duneMoveLongtitude the duneMoveLongtitude to set
	 */
	public void setDuneMoveLongtitude(String duneMoveLongtitude) {
		this.duneMoveLongtitude = duneMoveLongtitude;
	}
	/**
	 * 被监测沙丘纬度
	 * @return the duneMoveLatitude
	 */
	public String getDuneMoveLatitude() {
		return duneMoveLatitude;
	}
	/**
	 * 被监测沙丘纬度
	 * @param duneMoveLatitude the duneMoveLatitude to set
	 */
	public void setDuneMoveLatitude(String duneMoveLatitude) {
		this.duneMoveLatitude = duneMoveLatitude;
	}
	/**
	 * 沙丘海拔高度
	 * @return the duneAltitude
	 */
	public Float getDuneAltitude() {
		return duneAltitude;
	}
	/**
	 * 沙丘海拔高度
	 * @param duneAltitude the duneAltitude to set
	 */
	public void setDuneAltitude(Float duneAltitude) {
		this.duneAltitude = duneAltitude;
	}
	/**
	 * 沙丘高度变化
	 * @return the duneHeight
	 */
	public Float getDuneHeight() {
		return duneHeight;
	}
	/**
	 * 沙丘高度变化
	 * @param duneHeight the duneHeight to set
	 */
	public void setDuneHeight(Float duneHeight) {
		this.duneHeight = duneHeight;
	}
	/**
	 * 沙丘迎风坡脚移动距离
	 * @return the duneWindwardSlope
	 */
	public Float getDuneWindwardSlope() {
		return duneWindwardSlope;
	}
	/**
	 *  沙丘迎风坡脚移动距离
	 * @param duneWindwardSlope the duneWindwardSlope to set
	 */
	public void setDuneWindwardSlope(Float duneWindwardSlope) {
		this.duneWindwardSlope = duneWindwardSlope;
	}
	/**
	 * 沙丘丘顶移动距离
	 * @return the duneHilltop
	 */
	public Float getDuneHilltop() {
		return duneHilltop;
	}
	/**
	 * 沙丘丘顶移动距离
	 * @param duneHilltop the duneHilltop to set
	 */
	public void setDuneHilltop(Float duneHilltop) {
		this.duneHilltop = duneHilltop;
	}
	/**
	 * 沙丘背风坡脚移动距离
	 * @return the duneLeewardSlope
	 */
	public Float getDuneLeewardSlope() {
		return duneLeewardSlope;
	}
	/**
	 * 沙丘背风坡脚移动距离
	 * @param duneLeewardSlope the duneLeewardSlope to set
	 */
	public void setDuneLeewardSlope(Float duneLeewardSlope) {
		this.duneLeewardSlope = duneLeewardSlope;
	}
	/**
	 * 沙丘迎风坡脚方位角
	 * @return the duneWindPosition
	 */
	public Float getDuneWindPosition() {
		return duneWindPosition;
	}
	/**
	 * 沙丘迎风坡脚方位角
	 * @param duneWindPosition the duneWindPosition to set
	 */
	public void setDuneWindPosition(Float duneWindPosition) {
		this.duneWindPosition = duneWindPosition;
	}
	/**
	 * 沙丘丘顶方位角
	 * @return the hilltopPosition
	 */
	public Float getHilltopPosition() {
		return hilltopPosition;
	}
	/**
	 * 沙丘丘顶方位角
	 * @param hilltopPosition the hilltopPosition to set
	 */
	public void setHilltopPosition(Float hilltopPosition) {
		this.hilltopPosition = hilltopPosition;
	}
	/**
	 * 沙丘背风坡脚方位角
	 * @return the leewardPosition
	 */
	public Float getLeewardPosition() {
		return leewardPosition;
	}
	/**
	 * 沙丘背风坡脚方位角
	 * @param leewardPosition the leewardPosition to set
	 */
	public void setLeewardPosition(Float leewardPosition) {
		this.leewardPosition = leewardPosition;
	}
	/**
	 * 风向
	 * @return the windDirection
	 */
	public String getWindDirection() {
		return windDirection;
	}
	/**
	 * 风向
	 * @param windDirection the windDirection to set
	 */
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	/**
	 * 风速
	 * @return the windSpeed
	 */
	public Float getWindSpeed() {
		return windSpeed;
	}
	/**
	 * 风速
	 * @param windSpeed the windSpeed to set
	 */
	public void setWindSpeed(Float windSpeed) {
		this.windSpeed = windSpeed;
	}
	
}
