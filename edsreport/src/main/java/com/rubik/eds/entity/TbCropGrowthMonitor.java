package com.rubik.eds.entity;

import java.util.Date;

public class TbCropGrowthMonitor extends ReportHeader {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4215507142043833127L;
	
	private Integer id;
	private Integer userId;
	private Date operationTime;
	private String stationId;
	private String stationName;
	
	/**
	 * 作物名称
	 */
	private String cropName;
	/**
	 * 播种期
	 */
	private Date sowingDate;
	/**
	 * 出苗期
	 */
	private Date seedlingDate;
	/**
	 * 三叶期
	 */
	private Date trefoilDate;
	/**
	 * 拔节期
	 */
	private Date jointingDate;
	/**
	 * 抽穗期
	 */
	private Date headingDate;
	/**
	 * 抽雄期
	 */
	private Date tasselingDate;
	/**
	 * 开花期
	 */
	private Date floweringDate;
	/**
	 * 吐丝期
	 */
	private Date silkingDate;
	/**
	 * 乳熟期
	 */
	private Date milkyDate;
	/**
	 * 成熟期
	 */
	private Date maturityDate;
	/**
	 * 生长高度
	 */
	private Float growthHeight;
	/**
	 * 生长状况
	 */
	private String growthCondition;
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
	 * 播种期
	 * @return the sowingDate
	 */
	public Date getSowingDate() {
		return sowingDate;
	}
	/**
	 * 播种期
	 * @param sowingDate the sowingDate to set
	 */
	public void setSowingDate(Date sowingDate) {
		this.sowingDate = sowingDate;
	}
	/**
	 * 出苗期
	 * @return the seedlingDate
	 */
	public Date getSeedlingDate() {
		return seedlingDate;
	}
	/**
	 * 出苗期
	 * @param seedlingDate the seedlingDate to set
	 */
	public void setSeedlingDate(Date seedlingDate) {
		this.seedlingDate = seedlingDate;
	}
	/**
	 * 三叶期
	 * @return the trefoilDate
	 */
	public Date getTrefoilDate() {
		return trefoilDate;
	}
	/**
	 * 三叶期
	 * @param trefoilDate the trefoilDate to set
	 */
	public void setTrefoilDate(Date trefoilDate) {
		this.trefoilDate = trefoilDate;
	}
	/**
	 * 拔节期
	 * @return the jointingDate
	 */
	public Date getJointingDate() {
		return jointingDate;
	}
	/**
	 * 拔节期
	 * @param jointingDate the jointingDate to set
	 */
	public void setJointingDate(Date jointingDate) {
		this.jointingDate = jointingDate;
	}
	/**
	 * 抽穗期
	 * @return the headingDate
	 */
	public Date getHeadingDate() {
		return headingDate;
	}
	/**
	 * 抽穗期
	 * @param headingDate the headingDate to set
	 */
	public void setHeadingDate(Date headingDate) {
		this.headingDate = headingDate;
	}
	/**
	 * 抽雄期
	 * @return the tasselingDate
	 */
	public Date getTasselingDate() {
		return tasselingDate;
	}
	/**
	 * 抽雄期
	 * @param tasselingDate the tasselingDate to set
	 */
	public void setTasselingDate(Date tasselingDate) {
		this.tasselingDate = tasselingDate;
	}
	/**
	 * 开花期
	 * @return the floweringDate
	 */
	public Date getFloweringDate() {
		return floweringDate;
	}
	/**
	 * 开花期
	 * @param floweringDate the floweringDate to set
	 */
	public void setFloweringDate(Date floweringDate) {
		this.floweringDate = floweringDate;
	}
	/**
	 * 吐丝去
	 * @return the silkingDate
	 */
	public Date getSilkingDate() {
		return silkingDate;
	}
	/**
	 * 吐丝期
	 * @param silkingDate the silkingDate to set
	 */
	public void setSilkingDate(Date silkingDate) {
		this.silkingDate = silkingDate;
	}
	/**
	 * 乳熟期
	 * @return the milkyDate
	 */
	public Date getMilkyDate() {
		return milkyDate;
	}
	/**
	 * 乳熟期
	 * @param milkyDate the milkyDate to set
	 */
	public void setMilkyDate(Date milkyDate) {
		this.milkyDate = milkyDate;
	}
	/**
	 * 成熟期
	 * @return the maturityDate
	 */
	public Date getMaturityDate() {
		return maturityDate;
	}
	/**
	 * 成熟期
	 * @param maturityDate the maturityDate to set
	 */
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}
	/**
	 * 生长高度
	 * @return the growthHeight
	 */
	public Float getGrowthHeight() {
		return growthHeight;
	}
	/**
	 * 生长高度
	 * @param growthHeight the growthHeight to set
	 */
	public void setGrowthHeight(Float growthHeight) {
		this.growthHeight = growthHeight;
	}
	/**
	 * 生长状况
	 * @return the growthCondition
	 */
	public String getGrowthCondition() {
		return growthCondition;
	}
	/**
	 * 生长状况
	 * @param growthCondition the growthCondition to set
	 */
	public void setGrowthCondition(String growthCondition) {
		this.growthCondition = growthCondition;
	}
	
}
