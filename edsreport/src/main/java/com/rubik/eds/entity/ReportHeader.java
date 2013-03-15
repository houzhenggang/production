package com.rubik.eds.entity;

import java.util.Date;

import com.rubik.support.entity.BaseEntity;

public class ReportHeader extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 741610455557903190L;

	private Integer currentUserId;
	
	/**
	 * 台站ID
	 */
	private String reportStationId;
	
	/**
	 * 台站名称
	 */
	private String reportStationName;
	/**
	 * 水体名称
	 */
	private String reportWaterName;
	/**
	 * 牧草监测地段
	 */
	private String reportMonitorArea;
	/**
	 * 监测开始日期
	 */
	private Date reportStartDate;
	/**
	 * 监测结束日期
	 */
	private Date reportEndDate;
	/**
	 * 上报人姓名
	 */
	private String reporterName;
	/**
	 * 上报日期
	 */
	private Date reportDate;
	/**
	 * 备注
	 */
	private String reportRemarks;
	/**
	 * 怎么保存报表（0:只导出本地 1：保存到共享和本地 2：保存到FTP和本地 3：保存到共享、FTP和本地）
	 */
	private String howSaveFile;
	/**
	 * @return the currentUserId
	 */
	public Integer getCurrentUserId() {
		return currentUserId;
	}
	/**
	 * @param currentUserId the currentUserId to set
	 */
	public void setCurrentUserId(Integer currentUserId) {
		this.currentUserId = currentUserId;
	}
	/**
	 * 站点ID
	 * @return the stationId
	 */
	public String getReportStationId() {
		return reportStationId;
	}
	/**
	 * 站点ID
	 * @param stationId the stationId to set
	 */
	public void setReportStationId(String reportStationId) {
		this.reportStationId = reportStationId;
	}
	/**
	 * 站点名称
	 * @return the stationName
	 */
	public String getReportStationName() {
		return reportStationName;
	}
	/**
	 * 站点名称
	 * @param stationName the stationName to set
	 */
	public void setReportStationName(String reportStationName) {
		this.reportStationName = reportStationName;
	}
	/**
	 * 水体名称
	 * @return the waterName
	 */
	public String getReportWaterName() {
		return reportWaterName;
	}
	/**
	 * 水体名称
	 * @param waterName the waterName to set
	 */
	public void setReportWaterName(String reportWaterName) {
		this.reportWaterName = reportWaterName;
	}
	/**
	 * 牧草监测地段
	 * @return the reportMonitorArea
	 */
	public String getReportMonitorArea() {
		return reportMonitorArea;
	}
	/**
	 * 牧草监测地段
	 * @param reportMonitorArea the reportMonitorArea to set
	 */
	public void setReportMonitorArea(String reportMonitorArea) {
		this.reportMonitorArea = reportMonitorArea;
	}
	/**
	 * 监测开始时间
	 * @return the startDate
	 */
	public Date getReportStartDate() {
		return reportStartDate;
	}
	/**
	 * 监测开始时间
	 * @param startDate the startDate to set
	 */
	public void setReportStartDate(Date reportStartDate) {
		this.reportStartDate = reportStartDate;
	}
	/**
	 * 监测结束时间
	 * @return the endDate
	 */
	public Date getReportEndDate() {
		return reportEndDate;
	}
	/**
	 * 监测结束时间
	 * @param endDate the endDate to set
	 */
	public void setReportEndDate(Date reportEndDate) {
		this.reportEndDate = reportEndDate;
	}
	/**
	 * 上报人姓名
	 * @return the reporterName
	 */
	public String getReporterName() {
		return reporterName;
	}
	/**
	 * 上报人姓名
	 * @param reporterName the reporterName to set
	 */
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	/**
	 * 上报日期
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}
	/**
	 * 上报日期
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	/**
	 * 报表备注
	 * @return
	 */
	public String getReportRemarks() {
		return reportRemarks;
	}
	/**
	 * 报表备注
	 * @param reportRemarks
	 */
	public void setReportRemarks(String reportRemarks) {
		this.reportRemarks = reportRemarks;
	}
	/**
	 * 怎么保存报表（0:只导出本地 1：保存到共享和本地 2：保存到FTP和本地 3：保存到共享、FTP和本地）
	 * @return the howSaveFile
	 */
	public String getHowSaveFile() {
		return howSaveFile;
	}
	/**
	 * 怎么保存报表（0:只导出本地 1：保存到共享和本地 2：保存到FTP和本地 3：保存到共享、FTP和本地）
	 * @param howSaveFile the howSaveFile to set
	 */
	public void setHowSaveFile(String howSaveFile) {
		this.howSaveFile = howSaveFile;
	}
	
}
