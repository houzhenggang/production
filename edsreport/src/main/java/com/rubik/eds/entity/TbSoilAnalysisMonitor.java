package com.rubik.eds.entity;

import java.util.Date;

public class TbSoilAnalysisMonitor extends ReportHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3206333388825632953L;

	private Integer id;
	private Integer userId;
	private Date operationTime;
	private String stationId;
	private String stationName;
	
	/**
	 * 土壤分析时间
	 */
	private Date soilAnalysisDate;
	
	/**
	 * 干土层厚度
	 */
	private Float drySoilHeight;
	
	//第一组
	private Float wetSoilboxWeight1_10;
	private Float wetSoilboxWeight1_20;
	private Float wetSoilboxWeight1_30;
	private Float wetSoilboxWeight1_40;
	private Float wetSoilboxWeight1_50;
	
	private Float drySoilboxWeight1_10;
	private Float drySoilboxWeight1_20;
	private Float drySoilboxWeight1_30;
	private Float drySoilboxWeight1_40;
	private Float drySoilboxWeight1_50;
	
	//第二组
	private Float wetSoilboxWeight2_10;
	private Float wetSoilboxWeight2_20;
	private Float wetSoilboxWeight2_30;
	private Float wetSoilboxWeight2_40;
	private Float wetSoilboxWeight2_50;
	
	private Float drySoilboxWeight2_10;
	private Float drySoilboxWeight2_20;
	private Float drySoilboxWeight2_30;
	private Float drySoilboxWeight2_40;
	private Float drySoilboxWeight2_50;
	
	//第三组
	private Float wetSoilboxWeight3_10;
	private Float wetSoilboxWeight3_20;
	private Float wetSoilboxWeight3_30;
	private Float wetSoilboxWeight3_40;
	private Float wetSoilboxWeight3_50;
	
	private Float drySoilboxWeight3_10;
	private Float drySoilboxWeight3_20;
	private Float drySoilboxWeight3_30;
	private Float drySoilboxWeight3_40;
	private Float drySoilboxWeight3_50;
	
	//第四组
	private Float wetSoilboxWeight4_10;
	private Float wetSoilboxWeight4_20;
	private Float wetSoilboxWeight4_30;
	private Float wetSoilboxWeight4_40;
	private Float wetSoilboxWeight4_50;
	
	private Float drySoilboxWeight4_10;
	private Float drySoilboxWeight4_20;
	private Float drySoilboxWeight4_30;
	private Float drySoilboxWeight4_40;
	private Float drySoilboxWeight4_50;
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
	 * @return the soilAnalysisDate
	 */
	public Date getSoilAnalysisDate() {
		return soilAnalysisDate;
	}
	/**
	 * @param soilAnalysisDate the soilAnalysisDate to set
	 */
	public void setSoilAnalysisDate(Date soilAnalysisDate) {
		this.soilAnalysisDate = soilAnalysisDate;
	}
	/**
	 * 干土层厚度
	 * @return the drySoilHeight
	 */
	public Float getDrySoilHeight() {
		return drySoilHeight;
	}
	/**
	 * 干土层厚度
	 * @param drySoilHeight the drySoilHeight to set
	 */
	public void setDrySoilHeight(Float drySoilHeight) {
		this.drySoilHeight = drySoilHeight;
	}
	/**
	 * @return the wetSoilboxWeight1_10
	 */
	public Float getWetSoilboxWeight1_10() {
		return wetSoilboxWeight1_10;
	}
	/**
	 * @param wetSoilboxWeight1_10 the wetSoilboxWeight1_10 to set
	 */
	public void setWetSoilboxWeight1_10(Float wetSoilboxWeight1_10) {
		this.wetSoilboxWeight1_10 = wetSoilboxWeight1_10;
	}
	/**
	 * @return the wetSoilboxWeight1_20
	 */
	public Float getWetSoilboxWeight1_20() {
		return wetSoilboxWeight1_20;
	}
	/**
	 * @param wetSoilboxWeight1_20 the wetSoilboxWeight1_20 to set
	 */
	public void setWetSoilboxWeight1_20(Float wetSoilboxWeight1_20) {
		this.wetSoilboxWeight1_20 = wetSoilboxWeight1_20;
	}
	/**
	 * @return the wetSoilboxWeight1_30
	 */
	public Float getWetSoilboxWeight1_30() {
		return wetSoilboxWeight1_30;
	}
	/**
	 * @param wetSoilboxWeight1_30 the wetSoilboxWeight1_30 to set
	 */
	public void setWetSoilboxWeight1_30(Float wetSoilboxWeight1_30) {
		this.wetSoilboxWeight1_30 = wetSoilboxWeight1_30;
	}
	/**
	 * @return the wetSoilboxWeight1_40
	 */
	public Float getWetSoilboxWeight1_40() {
		return wetSoilboxWeight1_40;
	}
	/**
	 * @param wetSoilboxWeight1_40 the wetSoilboxWeight1_40 to set
	 */
	public void setWetSoilboxWeight1_40(Float wetSoilboxWeight1_40) {
		this.wetSoilboxWeight1_40 = wetSoilboxWeight1_40;
	}
	/**
	 * @return the wetSoilboxWeight1_50
	 */
	public Float getWetSoilboxWeight1_50() {
		return wetSoilboxWeight1_50;
	}
	/**
	 * @param wetSoilboxWeight1_50 the wetSoilboxWeight1_50 to set
	 */
	public void setWetSoilboxWeight1_50(Float wetSoilboxWeight1_50) {
		this.wetSoilboxWeight1_50 = wetSoilboxWeight1_50;
	}
	/**
	 * @return the drySoilboxWeight1_10
	 */
	public Float getDrySoilboxWeight1_10() {
		return drySoilboxWeight1_10;
	}
	/**
	 * @param drySoilboxWeight1_10 the drySoilboxWeight1_10 to set
	 */
	public void setDrySoilboxWeight1_10(Float drySoilboxWeight1_10) {
		this.drySoilboxWeight1_10 = drySoilboxWeight1_10;
	}
	/**
	 * @return the drySoilboxWeight1_20
	 */
	public Float getDrySoilboxWeight1_20() {
		return drySoilboxWeight1_20;
	}
	/**
	 * @param drySoilboxWeight1_20 the drySoilboxWeight1_20 to set
	 */
	public void setDrySoilboxWeight1_20(Float drySoilboxWeight1_20) {
		this.drySoilboxWeight1_20 = drySoilboxWeight1_20;
	}
	/**
	 * @return the drySoilboxWeight1_30
	 */
	public Float getDrySoilboxWeight1_30() {
		return drySoilboxWeight1_30;
	}
	/**
	 * @param drySoilboxWeight1_30 the drySoilboxWeight1_30 to set
	 */
	public void setDrySoilboxWeight1_30(Float drySoilboxWeight1_30) {
		this.drySoilboxWeight1_30 = drySoilboxWeight1_30;
	}
	/**
	 * @return the drySoilboxWeight1_40
	 */
	public Float getDrySoilboxWeight1_40() {
		return drySoilboxWeight1_40;
	}
	/**
	 * @param drySoilboxWeight1_40 the drySoilboxWeight1_40 to set
	 */
	public void setDrySoilboxWeight1_40(Float drySoilboxWeight1_40) {
		this.drySoilboxWeight1_40 = drySoilboxWeight1_40;
	}
	/**
	 * @return the drySoilboxWeight1_50
	 */
	public Float getDrySoilboxWeight1_50() {
		return drySoilboxWeight1_50;
	}
	/**
	 * @param drySoilboxWeight1_50 the drySoilboxWeight1_50 to set
	 */
	public void setDrySoilboxWeight1_50(Float drySoilboxWeight1_50) {
		this.drySoilboxWeight1_50 = drySoilboxWeight1_50;
	}
	/**
	 * @return the wetSoilboxWeight2_10
	 */
	public Float getWetSoilboxWeight2_10() {
		return wetSoilboxWeight2_10;
	}
	/**
	 * @param wetSoilboxWeight2_10 the wetSoilboxWeight2_10 to set
	 */
	public void setWetSoilboxWeight2_10(Float wetSoilboxWeight2_10) {
		this.wetSoilboxWeight2_10 = wetSoilboxWeight2_10;
	}
	/**
	 * @return the wetSoilboxWeight2_20
	 */
	public Float getWetSoilboxWeight2_20() {
		return wetSoilboxWeight2_20;
	}
	/**
	 * @param wetSoilboxWeight2_20 the wetSoilboxWeight2_20 to set
	 */
	public void setWetSoilboxWeight2_20(Float wetSoilboxWeight2_20) {
		this.wetSoilboxWeight2_20 = wetSoilboxWeight2_20;
	}
	/**
	 * @return the wetSoilboxWeight2_30
	 */
	public Float getWetSoilboxWeight2_30() {
		return wetSoilboxWeight2_30;
	}
	/**
	 * @param wetSoilboxWeight2_30 the wetSoilboxWeight2_30 to set
	 */
	public void setWetSoilboxWeight2_30(Float wetSoilboxWeight2_30) {
		this.wetSoilboxWeight2_30 = wetSoilboxWeight2_30;
	}
	/**
	 * @return the wetSoilboxWeight2_40
	 */
	public Float getWetSoilboxWeight2_40() {
		return wetSoilboxWeight2_40;
	}
	/**
	 * @param wetSoilboxWeight2_40 the wetSoilboxWeight2_40 to set
	 */
	public void setWetSoilboxWeight2_40(Float wetSoilboxWeight2_40) {
		this.wetSoilboxWeight2_40 = wetSoilboxWeight2_40;
	}
	/**
	 * @return the wetSoilboxWeight2_50
	 */
	public Float getWetSoilboxWeight2_50() {
		return wetSoilboxWeight2_50;
	}
	/**
	 * @param wetSoilboxWeight2_50 the wetSoilboxWeight2_50 to set
	 */
	public void setWetSoilboxWeight2_50(Float wetSoilboxWeight2_50) {
		this.wetSoilboxWeight2_50 = wetSoilboxWeight2_50;
	}
	/**
	 * @return the drySoilboxWeight2_10
	 */
	public Float getDrySoilboxWeight2_10() {
		return drySoilboxWeight2_10;
	}
	/**
	 * @param drySoilboxWeight2_10 the drySoilboxWeight2_10 to set
	 */
	public void setDrySoilboxWeight2_10(Float drySoilboxWeight2_10) {
		this.drySoilboxWeight2_10 = drySoilboxWeight2_10;
	}
	/**
	 * @return the drySoilboxWeight2_20
	 */
	public Float getDrySoilboxWeight2_20() {
		return drySoilboxWeight2_20;
	}
	/**
	 * @param drySoilboxWeight2_20 the drySoilboxWeight2_20 to set
	 */
	public void setDrySoilboxWeight2_20(Float drySoilboxWeight2_20) {
		this.drySoilboxWeight2_20 = drySoilboxWeight2_20;
	}
	/**
	 * @return the drySoilboxWeight2_30
	 */
	public Float getDrySoilboxWeight2_30() {
		return drySoilboxWeight2_30;
	}
	/**
	 * @param drySoilboxWeight2_30 the drySoilboxWeight2_30 to set
	 */
	public void setDrySoilboxWeight2_30(Float drySoilboxWeight2_30) {
		this.drySoilboxWeight2_30 = drySoilboxWeight2_30;
	}
	/**
	 * @return the drySoilboxWeight2_40
	 */
	public Float getDrySoilboxWeight2_40() {
		return drySoilboxWeight2_40;
	}
	/**
	 * @param drySoilboxWeight2_40 the drySoilboxWeight2_40 to set
	 */
	public void setDrySoilboxWeight2_40(Float drySoilboxWeight2_40) {
		this.drySoilboxWeight2_40 = drySoilboxWeight2_40;
	}
	/**
	 * @return the drySoilboxWeight2_50
	 */
	public Float getDrySoilboxWeight2_50() {
		return drySoilboxWeight2_50;
	}
	/**
	 * @param drySoilboxWeight2_50 the drySoilboxWeight2_50 to set
	 */
	public void setDrySoilboxWeight2_50(Float drySoilboxWeight2_50) {
		this.drySoilboxWeight2_50 = drySoilboxWeight2_50;
	}
	/**
	 * @return the wetSoilboxWeight3_10
	 */
	public Float getWetSoilboxWeight3_10() {
		return wetSoilboxWeight3_10;
	}
	/**
	 * @param wetSoilboxWeight3_10 the wetSoilboxWeight3_10 to set
	 */
	public void setWetSoilboxWeight3_10(Float wetSoilboxWeight3_10) {
		this.wetSoilboxWeight3_10 = wetSoilboxWeight3_10;
	}
	/**
	 * @return the wetSoilboxWeight3_20
	 */
	public Float getWetSoilboxWeight3_20() {
		return wetSoilboxWeight3_20;
	}
	/**
	 * @param wetSoilboxWeight3_20 the wetSoilboxWeight3_20 to set
	 */
	public void setWetSoilboxWeight3_20(Float wetSoilboxWeight3_20) {
		this.wetSoilboxWeight3_20 = wetSoilboxWeight3_20;
	}
	/**
	 * @return the wetSoilboxWeight3_30
	 */
	public Float getWetSoilboxWeight3_30() {
		return wetSoilboxWeight3_30;
	}
	/**
	 * @param wetSoilboxWeight3_30 the wetSoilboxWeight3_30 to set
	 */
	public void setWetSoilboxWeight3_30(Float wetSoilboxWeight3_30) {
		this.wetSoilboxWeight3_30 = wetSoilboxWeight3_30;
	}
	/**
	 * @return the wetSoilboxWeight3_40
	 */
	public Float getWetSoilboxWeight3_40() {
		return wetSoilboxWeight3_40;
	}
	/**
	 * @param wetSoilboxWeight3_40 the wetSoilboxWeight3_40 to set
	 */
	public void setWetSoilboxWeight3_40(Float wetSoilboxWeight3_40) {
		this.wetSoilboxWeight3_40 = wetSoilboxWeight3_40;
	}
	/**
	 * @return the wetSoilboxWeight3_50
	 */
	public Float getWetSoilboxWeight3_50() {
		return wetSoilboxWeight3_50;
	}
	/**
	 * @param wetSoilboxWeight3_50 the wetSoilboxWeight3_50 to set
	 */
	public void setWetSoilboxWeight3_50(Float wetSoilboxWeight3_50) {
		this.wetSoilboxWeight3_50 = wetSoilboxWeight3_50;
	}
	/**
	 * @return the drySoilboxWeight3_10
	 */
	public Float getDrySoilboxWeight3_10() {
		return drySoilboxWeight3_10;
	}
	/**
	 * @param drySoilboxWeight3_10 the drySoilboxWeight3_10 to set
	 */
	public void setDrySoilboxWeight3_10(Float drySoilboxWeight3_10) {
		this.drySoilboxWeight3_10 = drySoilboxWeight3_10;
	}
	/**
	 * @return the drySoilboxWeight3_20
	 */
	public Float getDrySoilboxWeight3_20() {
		return drySoilboxWeight3_20;
	}
	/**
	 * @param drySoilboxWeight3_20 the drySoilboxWeight3_20 to set
	 */
	public void setDrySoilboxWeight3_20(Float drySoilboxWeight3_20) {
		this.drySoilboxWeight3_20 = drySoilboxWeight3_20;
	}
	/**
	 * @return the drySoilboxWeight3_30
	 */
	public Float getDrySoilboxWeight3_30() {
		return drySoilboxWeight3_30;
	}
	/**
	 * @param drySoilboxWeight3_30 the drySoilboxWeight3_30 to set
	 */
	public void setDrySoilboxWeight3_30(Float drySoilboxWeight3_30) {
		this.drySoilboxWeight3_30 = drySoilboxWeight3_30;
	}
	/**
	 * @return the drySoilboxWeight3_40
	 */
	public Float getDrySoilboxWeight3_40() {
		return drySoilboxWeight3_40;
	}
	/**
	 * @param drySoilboxWeight3_40 the drySoilboxWeight3_40 to set
	 */
	public void setDrySoilboxWeight3_40(Float drySoilboxWeight3_40) {
		this.drySoilboxWeight3_40 = drySoilboxWeight3_40;
	}
	/**
	 * @return the drySoilboxWeight3_50
	 */
	public Float getDrySoilboxWeight3_50() {
		return drySoilboxWeight3_50;
	}
	/**
	 * @param drySoilboxWeight3_50 the drySoilboxWeight3_50 to set
	 */
	public void setDrySoilboxWeight3_50(Float drySoilboxWeight3_50) {
		this.drySoilboxWeight3_50 = drySoilboxWeight3_50;
	}
	/**
	 * @return the wetSoilboxWeight4_10
	 */
	public Float getWetSoilboxWeight4_10() {
		return wetSoilboxWeight4_10;
	}
	/**
	 * @param wetSoilboxWeight4_10 the wetSoilboxWeight4_10 to set
	 */
	public void setWetSoilboxWeight4_10(Float wetSoilboxWeight4_10) {
		this.wetSoilboxWeight4_10 = wetSoilboxWeight4_10;
	}
	/**
	 * @return the wetSoilboxWeight4_20
	 */
	public Float getWetSoilboxWeight4_20() {
		return wetSoilboxWeight4_20;
	}
	/**
	 * @param wetSoilboxWeight4_20 the wetSoilboxWeight4_20 to set
	 */
	public void setWetSoilboxWeight4_20(Float wetSoilboxWeight4_20) {
		this.wetSoilboxWeight4_20 = wetSoilboxWeight4_20;
	}
	/**
	 * @return the wetSoilboxWeight4_30
	 */
	public Float getWetSoilboxWeight4_30() {
		return wetSoilboxWeight4_30;
	}
	/**
	 * @param wetSoilboxWeight4_30 the wetSoilboxWeight4_30 to set
	 */
	public void setWetSoilboxWeight4_30(Float wetSoilboxWeight4_30) {
		this.wetSoilboxWeight4_30 = wetSoilboxWeight4_30;
	}
	/**
	 * @return the wetSoilboxWeight4_40
	 */
	public Float getWetSoilboxWeight4_40() {
		return wetSoilboxWeight4_40;
	}
	/**
	 * @param wetSoilboxWeight4_40 the wetSoilboxWeight4_40 to set
	 */
	public void setWetSoilboxWeight4_40(Float wetSoilboxWeight4_40) {
		this.wetSoilboxWeight4_40 = wetSoilboxWeight4_40;
	}
	/**
	 * @return the wetSoilboxWeight4_50
	 */
	public Float getWetSoilboxWeight4_50() {
		return wetSoilboxWeight4_50;
	}
	/**
	 * @param wetSoilboxWeight4_50 the wetSoilboxWeight4_50 to set
	 */
	public void setWetSoilboxWeight4_50(Float wetSoilboxWeight4_50) {
		this.wetSoilboxWeight4_50 = wetSoilboxWeight4_50;
	}
	/**
	 * @return the drySoilboxWeight4_10
	 */
	public Float getDrySoilboxWeight4_10() {
		return drySoilboxWeight4_10;
	}
	/**
	 * @param drySoilboxWeight4_10 the drySoilboxWeight4_10 to set
	 */
	public void setDrySoilboxWeight4_10(Float drySoilboxWeight4_10) {
		this.drySoilboxWeight4_10 = drySoilboxWeight4_10;
	}
	/**
	 * @return the drySoilboxWeight4_20
	 */
	public Float getDrySoilboxWeight4_20() {
		return drySoilboxWeight4_20;
	}
	/**
	 * @param drySoilboxWeight4_20 the drySoilboxWeight4_20 to set
	 */
	public void setDrySoilboxWeight4_20(Float drySoilboxWeight4_20) {
		this.drySoilboxWeight4_20 = drySoilboxWeight4_20;
	}
	/**
	 * @return the drySoilboxWeight4_30
	 */
	public Float getDrySoilboxWeight4_30() {
		return drySoilboxWeight4_30;
	}
	/**
	 * @param drySoilboxWeight4_30 the drySoilboxWeight4_30 to set
	 */
	public void setDrySoilboxWeight4_30(Float drySoilboxWeight4_30) {
		this.drySoilboxWeight4_30 = drySoilboxWeight4_30;
	}
	/**
	 * @return the drySoilboxWeight4_40
	 */
	public Float getDrySoilboxWeight4_40() {
		return drySoilboxWeight4_40;
	}
	/**
	 * @param drySoilboxWeight4_40 the drySoilboxWeight4_40 to set
	 */
	public void setDrySoilboxWeight4_40(Float drySoilboxWeight4_40) {
		this.drySoilboxWeight4_40 = drySoilboxWeight4_40;
	}
	/**
	 * @return the drySoilboxWeight4_50
	 */
	public Float getDrySoilboxWeight4_50() {
		return drySoilboxWeight4_50;
	}
	/**
	 * @param drySoilboxWeight4_50 the drySoilboxWeight4_50 to set
	 */
	public void setDrySoilboxWeight4_50(Float drySoilboxWeight4_50) {
		this.drySoilboxWeight4_50 = drySoilboxWeight4_50;
	}
}
