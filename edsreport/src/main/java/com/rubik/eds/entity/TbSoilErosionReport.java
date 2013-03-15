package com.rubik.eds.entity;

import java.util.Date;

public class TbSoilErosionReport extends ReportHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 51256688410220938L;

	private Integer id;
	private Integer userId;
	private Date operationTime;
	private String stationId;
	private String stationName;
	
	/**
	 * 监测日期
	 */
	private Date soilMonitorDate;
	/**
	 * 监测区域内平均厚度（CM）
	 */
	private Float innerSoilErosion;
	
	/**
	 * 监测区域外平均厚度（CM）
	 */
	private Float outSoilErosion;

	/**
	 * 区域内平均风积厚度
	 */
	private Float avgInnerSoilProduct;
	/**
	 * 区域内平均风蚀厚度
	 */
	private Float avgInnerSoilLose;
	
	/**
	 * 区域内、外平均风积厚度
	 */
	private Float avgOutSoilProduct;
	/**
	 * 区域外平均风蚀厚度
	 */
	private Float avgOutSoilLose;
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
	 * @return the soilMonitorDate
	 */
	public Date getSoilMonitorDate() {
		return soilMonitorDate;
	}
	/**
	 * @param soilMonitorDate the soilMonitorDate to set
	 */
	public void setSoilMonitorDate(Date soilMonitorDate) {
		this.soilMonitorDate = soilMonitorDate;
	}
	/**
	 * 监测区域内平均厚度（CM）
	 * @return the innerSoilErosion
	 */
	public Float getInnerSoilErosion() {
		return innerSoilErosion;
	}
	/**
	 * 监测区域内平均厚度（CM）
	 * @param innerSoilErosion the innerSoilErosion to set
	 */
	public void setInnerSoilErosion(Float innerSoilErosion) {
		this.innerSoilErosion = innerSoilErosion;
	}
	/**
	 * 监测区域外平均厚度（CM）
	 * @return the outSoilErosion
	 */
	public Float getOutSoilErosion() {
		return outSoilErosion;
	}
	/**
	 * 监测区域外平均厚度（CM）
	 * @param outSoilErosion the outSoilErosion to set
	 */
	public void setOutSoilErosion(Float outSoilErosion) {
		this.outSoilErosion = outSoilErosion;
	}
	/**
	 * 区域内平均风积厚度
	 * @return the avgInnerSoilProduct
	 */
	public Float getAvgInnerSoilProduct() {
		return avgInnerSoilProduct;
	}
	/**
	 * 区域内平均风积厚度
	 * @param avgInnerSoilProduct the avgInnerSoilProduct to set
	 */
	public void setAvgInnerSoilProduct(Float avgInnerSoilProduct) {
		this.avgInnerSoilProduct = avgInnerSoilProduct;
	}
	/**
	 * 区域内平均风蚀厚度
	 * @return the avgInnerSoilLose
	 */
	public Float getAvgInnerSoilLose() {
		return avgInnerSoilLose;
	}
	/**
	 * 区域内平均风蚀厚度
	 * @param avgInnerSoilLose the avgInnerSoilLose to set
	 */
	public void setAvgInnerSoilLose(Float avgInnerSoilLose) {
		this.avgInnerSoilLose = avgInnerSoilLose;
	}
	/**
	 * 区域外平均风积厚度
	 * @return the avgOutSoilProduct
	 */
	public Float getAvgOutSoilProduct() {
		return avgOutSoilProduct;
	}
	/**
	 * 区域外平均风积厚度
	 * @param avgOutSoilProduct the avgOutSoilProduct to set
	 */
	public void setAvgOutSoilProduct(Float avgOutSoilProduct) {
		this.avgOutSoilProduct = avgOutSoilProduct;
	}
	/**
	 * 区域外平均风蚀厚度
	 * @return the avgOutSoilLose
	 */
	public Float getAvgOutSoilLose() {
		return avgOutSoilLose;
	}
	/**
	 * 区域外平均风蚀厚度
	 * @param avgOutSoilLose the avgOutSoilLose to set
	 */
	public void setAvgOutSoilLose(Float avgOutSoilLose) {
		this.avgOutSoilLose = avgOutSoilLose;
	}
	
}
