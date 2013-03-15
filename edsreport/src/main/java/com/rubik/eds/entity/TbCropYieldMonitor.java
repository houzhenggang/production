package com.rubik.eds.entity;

import java.util.Date;

public class TbCropYieldMonitor extends ReportHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4776047532749852586L;

	private Integer id;
	private Integer userId;
	private Date operationTime;
	private String stationId;
	private String stationName;
	/**
	 * 农作物名称
	 */
	private String cropName;
	/**
	 * 小麦穗长
	 */
	private Float wheatLength;
	/**
	 * 小麦数
	 */
	private Float wheatCount;
	/**
	 * 不孕小麦数
	 */
	private Float infertilityWheatCount;
	/**
	 * 结实粒数
	 */
	private Float solidCount;
	/**
	 * 茎粗
	 */
	private Float stemWidth;
	/**
	 * 果穗长
	 */
	private Float cropEarLength;
	/**
	 * 果穗粗
	 */
	private Float cropEarWidth;
	/**
	 * 双穗率
	 */
	private String doubleEarCount;
	/**
	 * 农作物产量
	 */
	private Float cropYield;
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
	 * 农作物名称
	 * @return the cropName
	 */
	public String getCropName() {
		return cropName;
	}
	/**
	 * 农作物名称
	 * @param cropName the cropName to set
	 */
	public void setCropName(String cropName) {
		this.cropName = cropName;
	}
	/**
	 * 小麦穗长
	 * @return the wheatLength
	 */
	public Float getWheatLength() {
		return wheatLength;
	}
	/**
	 * 小麦穗长
	 * @param wheatLength the wheatLength to set
	 */
	public void setWheatLength(Float wheatLength) {
		this.wheatLength = wheatLength;
	}
	/**
	 * 小穗数
	 * @return the wheatCount
	 */
	public Float getWheatCount() {
		return wheatCount;
	}
	/**
	 * 小穗数
	 * @param wheatCount the wheatCount to set
	 */
	public void setWheatCount(Float wheatCount) {
		this.wheatCount = wheatCount;
	}
	/**
	 * 不孕小穗数
	 * @return the infertilityWheatCount
	 */
	public Float getInfertilityWheatCount() {
		return infertilityWheatCount;
	}
	/**
	 * 不孕小穗数
	 * @param infertilityWheatCount the infertilityWheatCount to set
	 */
	public void setInfertilityWheatCount(Float infertilityWheatCount) {
		this.infertilityWheatCount = infertilityWheatCount;
	}
	/**
	 * 结实粒数
	 * @return the solidCount
	 */
	public Float getSolidCount() {
		return solidCount;
	}
	/**
	 * 结实粒数
	 * @param solidCount the solidCount to set
	 */
	public void setSolidCount(Float solidCount) {
		this.solidCount = solidCount;
	}
	/**
	 * 茎粗
	 * @return the stemWidth
	 */
	public Float getStemWidth() {
		return stemWidth;
	}
	/**
	 * 茎粗
	 * @param stemWidth the stemWidth to set
	 */
	public void setStemWidth(Float stemWidth) {
		this.stemWidth = stemWidth;
	}
	/**
	 * 果穗长
	 * @return the cropEarLength
	 */
	public Float getCropEarLength() {
		return cropEarLength;
	}
	/**
	 * 果穗长
	 * @param cropEarLength the cropEarLength to set
	 */
	public void setCropEarLength(Float cropEarLength) {
		this.cropEarLength = cropEarLength;
	}
	/**
	 * 果穗粗
	 * @return the cropEarWidth
	 */
	public Float getCropEarWidth() {
		return cropEarWidth;
	}
	/**
	 * 果穗粗
	 * @param cropEarWidth the cropEarWidth to set
	 */
	public void setCropEarWidth(Float cropEarWidth) {
		this.cropEarWidth = cropEarWidth;
	}
	/**
	 * 双穗率
	 * @return the doubleEarCount
	 */
	public String getDoubleEarCount() {
		return doubleEarCount;
	}
	/**
	 * 双穗率
	 * @param doubleEarCount the doubleEarCount to set
	 */
	public void setDoubleEarCount(String doubleEarCount) {
		this.doubleEarCount = doubleEarCount;
	}
	/**
	 * 作物产量
	 * @return the cropYield
	 */
	public Float getCropYield() {
		return cropYield;
	}
	/**
	 * 作物产量
	 * @param cropYield the cropYield to set
	 */
	public void setCropYield(Float cropYield) {
		this.cropYield = cropYield;
	}
}
