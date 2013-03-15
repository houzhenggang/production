package com.rubik.eds.entity;

import java.util.Date;

public class TbSoilErosionMonitor extends ReportHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1191279076982944018L;

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
	 * 区域内监测数据20组
	 */
	private Float innerSoilErosion1;
	private Float innerSoilErosion2;
	private Float innerSoilErosion3;
	private Float innerSoilErosion4;
	private Float innerSoilErosion5;
	private Float innerSoilErosion6;
	private Float innerSoilErosion7;
	private Float innerSoilErosion8;
	private Float innerSoilErosion9;
	private Float innerSoilErosion10;
	private Float innerSoilErosion11;
	private Float innerSoilErosion12;
	private Float innerSoilErosion13;
	private Float innerSoilErosion14;
	private Float innerSoilErosion15;
	private Float innerSoilErosion16;
	private Float innerSoilErosion17;
	private Float innerSoilErosion18;
	private Float innerSoilErosion19;
	private Float innerSoilErosion20;
	
	/**
	 * 区域外监测数据20组
	 */
	private Float outSoilErosion1;
	private Float outSoilErosion2;
	private Float outSoilErosion3;
	private Float outSoilErosion4;
	private Float outSoilErosion5;
	private Float outSoilErosion6;
	private Float outSoilErosion7;
	private Float outSoilErosion8;
	private Float outSoilErosion9;
	private Float outSoilErosion10;
	private Float outSoilErosion11;
	private Float outSoilErosion12;
	private Float outSoilErosion13;
	private Float outSoilErosion14;
	private Float outSoilErosion15;
	private Float outSoilErosion16;
	private Float outSoilErosion17;
	private Float outSoilErosion18;
	private Float outSoilErosion19;
	private Float outSoilErosion20;
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
	 * @return the innerSoilErosion1
	 */
	public Float getInnerSoilErosion1() {
		return innerSoilErosion1==null?0.00f:innerSoilErosion1;
	}
	/**
	 * @param innerSoilErosion1 the innerSoilErosion1 to set
	 */
	public void setInnerSoilErosion1(Float innerSoilErosion1) {
		this.innerSoilErosion1 = innerSoilErosion1;
	}
	/**
	 * @return the innerSoilErosion2
	 */
	public Float getInnerSoilErosion2() {
		return innerSoilErosion2==null?0.00f:innerSoilErosion2;
	}
	/**
	 * @param innerSoilErosion2 the innerSoilErosion2 to set
	 */
	public void setInnerSoilErosion2(Float innerSoilErosion2) {
		this.innerSoilErosion2 = innerSoilErosion2;
	}
	/**
	 * @return the innerSoilErosion3
	 */
	public Float getInnerSoilErosion3() {
		return innerSoilErosion3==null?0.00f:innerSoilErosion3;
	}
	/**
	 * @param innerSoilErosion3 the innerSoilErosion3 to set
	 */
	public void setInnerSoilErosion3(Float innerSoilErosion3) {
		this.innerSoilErosion3 = innerSoilErosion3;
	}
	/**
	 * @return the innerSoilErosion4
	 */
	public Float getInnerSoilErosion4() {
		return innerSoilErosion4==null?0.00f:innerSoilErosion4;
	}
	/**
	 * @param innerSoilErosion4 the innerSoilErosion4 to set
	 */
	public void setInnerSoilErosion4(Float innerSoilErosion4) {
		this.innerSoilErosion4 = innerSoilErosion4;
	}
	/**
	 * @return the innerSoilErosion5
	 */
	public Float getInnerSoilErosion5() {
		return innerSoilErosion5==null?0.00f:innerSoilErosion5;
	}
	/**
	 * @param innerSoilErosion5 the innerSoilErosion5 to set
	 */
	public void setInnerSoilErosion5(Float innerSoilErosion5) {
		this.innerSoilErosion5 = innerSoilErosion5;
	}
	/**
	 * @return the innerSoilErosion6
	 */
	public Float getInnerSoilErosion6() {
		return innerSoilErosion6==null?0.00f:innerSoilErosion6;
	}
	/**
	 * @param innerSoilErosion6 the innerSoilErosion6 to set
	 */
	public void setInnerSoilErosion6(Float innerSoilErosion6) {
		this.innerSoilErosion6 = innerSoilErosion6;
	}
	/**
	 * @return the innerSoilErosion7
	 */
	public Float getInnerSoilErosion7() {
		return innerSoilErosion7==null?0.00f:innerSoilErosion7;
	}
	/**
	 * @param innerSoilErosion7 the innerSoilErosion7 to set
	 */
	public void setInnerSoilErosion7(Float innerSoilErosion7) {
		this.innerSoilErosion7 = innerSoilErosion7;
	}
	/**
	 * @return the innerSoilErosion8
	 */
	public Float getInnerSoilErosion8() {
		return innerSoilErosion8==null?0.00f:innerSoilErosion8;
	}
	/**
	 * @param innerSoilErosion8 the innerSoilErosion8 to set
	 */
	public void setInnerSoilErosion8(Float innerSoilErosion8) {
		this.innerSoilErosion8 = innerSoilErosion8;
	}
	/**
	 * @return the innerSoilErosion9
	 */
	public Float getInnerSoilErosion9() {
		return innerSoilErosion9==null?0.00f:innerSoilErosion9;
	}
	/**
	 * @param innerSoilErosion9 the innerSoilErosion9 to set
	 */
	public void setInnerSoilErosion9(Float innerSoilErosion9) {
		this.innerSoilErosion9 = innerSoilErosion9;
	}
	/**
	 * @return the innerSoilErosion10
	 */
	public Float getInnerSoilErosion10() {
		return innerSoilErosion10==null?0.00f:innerSoilErosion10;
	}
	/**
	 * @param innerSoilErosion10 the innerSoilErosion10 to set
	 */
	public void setInnerSoilErosion10(Float innerSoilErosion10) {
		this.innerSoilErosion10 = innerSoilErosion10;
	}
	/**
	 * @return the innerSoilErosion11
	 */
	public Float getInnerSoilErosion11() {
		return innerSoilErosion11==null?0.00f:innerSoilErosion11;
	}
	/**
	 * @param innerSoilErosion11 the innerSoilErosion11 to set
	 */
	public void setInnerSoilErosion11(Float innerSoilErosion11) {
		this.innerSoilErosion11 = innerSoilErosion11;
	}
	/**
	 * @return the innerSoilErosion12
	 */
	public Float getInnerSoilErosion12() {
		return innerSoilErosion12==null?0.00f:innerSoilErosion12;
	}
	/**
	 * @param innerSoilErosion12 the innerSoilErosion12 to set
	 */
	public void setInnerSoilErosion12(Float innerSoilErosion12) {
		this.innerSoilErosion12 = innerSoilErosion12;
	}
	/**
	 * @return the innerSoilErosion13
	 */
	public Float getInnerSoilErosion13() {
		return innerSoilErosion13==null?0.00f:innerSoilErosion13;
	}
	/**
	 * @param innerSoilErosion13 the innerSoilErosion13 to set
	 */
	public void setInnerSoilErosion13(Float innerSoilErosion13) {
		this.innerSoilErosion13 = innerSoilErosion13;
	}
	/**
	 * @return the innerSoilErosion14
	 */
	public Float getInnerSoilErosion14() {
		return innerSoilErosion14==null?0.00f:innerSoilErosion14;
	}
	/**
	 * @param innerSoilErosion14 the innerSoilErosion14 to set
	 */
	public void setInnerSoilErosion14(Float innerSoilErosion14) {
		this.innerSoilErosion14 = innerSoilErosion14;
	}
	/**
	 * @return the innerSoilErosion15
	 */
	public Float getInnerSoilErosion15() {
		return innerSoilErosion15==null?0.00f:innerSoilErosion15;
	}
	/**
	 * @param innerSoilErosion15 the innerSoilErosion15 to set
	 */
	public void setInnerSoilErosion15(Float innerSoilErosion15) {
		this.innerSoilErosion15 = innerSoilErosion15;
	}
	/**
	 * @return the innerSoilErosion16
	 */
	public Float getInnerSoilErosion16() {
		return innerSoilErosion16==null?0.00f:innerSoilErosion16;
	}
	/**
	 * @param innerSoilErosion16 the innerSoilErosion16 to set
	 */
	public void setInnerSoilErosion16(Float innerSoilErosion16) {
		this.innerSoilErosion16 = innerSoilErosion16;
	}
	/**
	 * @return the innerSoilErosion17
	 */
	public Float getInnerSoilErosion17() {
		return innerSoilErosion17==null?0.00f:innerSoilErosion17;
	}
	/**
	 * @param innerSoilErosion17 the innerSoilErosion17 to set
	 */
	public void setInnerSoilErosion17(Float innerSoilErosion17) {
		this.innerSoilErosion17 = innerSoilErosion17;
	}
	/**
	 * @return the innerSoilErosion18
	 */
	public Float getInnerSoilErosion18() {
		return innerSoilErosion18==null?0.00f:innerSoilErosion18;
	}
	/**
	 * @param innerSoilErosion18 the innerSoilErosion18 to set
	 */
	public void setInnerSoilErosion18(Float innerSoilErosion18) {
		this.innerSoilErosion18 = innerSoilErosion18;
	}
	/**
	 * @return the innerSoilErosion19
	 */
	public Float getInnerSoilErosion19() {
		return innerSoilErosion19==null?0.00f:innerSoilErosion19;
	}
	/**
	 * @param innerSoilErosion19 the innerSoilErosion19 to set
	 */
	public void setInnerSoilErosion19(Float innerSoilErosion19) {
		this.innerSoilErosion19 = innerSoilErosion19;
	}
	/**
	 * @return the innerSoilErosion20
	 */
	public Float getInnerSoilErosion20() {
		return innerSoilErosion20==null?0.00f:innerSoilErosion20;
	}
	/**
	 * @param innerSoilErosion20 the innerSoilErosion20 to set
	 */
	public void setInnerSoilErosion20(Float innerSoilErosion20) {
		this.innerSoilErosion20 = innerSoilErosion20;
	}
	/**
	 * @return the outSoilErosion1
	 */
	public Float getOutSoilErosion1() {
		return outSoilErosion1==null?0.00f:outSoilErosion1;
	}
	/**
	 * @param outSoilErosion1 the outSoilErosion1 to set
	 */
	public void setOutSoilErosion1(Float outSoilErosion1) {
		this.outSoilErosion1 = outSoilErosion1;
	}
	/**
	 * @return the outSoilErosion2
	 */
	public Float getOutSoilErosion2() {
		return outSoilErosion2==null?0.00f:outSoilErosion2;
	}
	/**
	 * @param outSoilErosion2 the outSoilErosion2 to set
	 */
	public void setOutSoilErosion2(Float outSoilErosion2) {
		this.outSoilErosion2 = outSoilErosion2;
	}
	/**
	 * @return the outSoilErosion3
	 */
	public Float getOutSoilErosion3() {
		return outSoilErosion3==null?0.00f:outSoilErosion3;
	}
	/**
	 * @param outSoilErosion3 the outSoilErosion3 to set
	 */
	public void setOutSoilErosion3(Float outSoilErosion3) {
		this.outSoilErosion3 = outSoilErosion3;
	}
	/**
	 * @return the outSoilErosion4
	 */
	public Float getOutSoilErosion4() {
		return outSoilErosion4==null?0.00f:outSoilErosion4;
	}
	/**
	 * @param outSoilErosion4 the outSoilErosion4 to set
	 */
	public void setOutSoilErosion4(Float outSoilErosion4) {
		this.outSoilErosion4 = outSoilErosion4;
	}
	/**
	 * @return the outSoilErosion5
	 */
	public Float getOutSoilErosion5() {
		return outSoilErosion5==null?0.00f:outSoilErosion5;
	}
	/**
	 * @param outSoilErosion5 the outSoilErosion5 to set
	 */
	public void setOutSoilErosion5(Float outSoilErosion5) {
		this.outSoilErosion5 = outSoilErosion5;
	}
	/**
	 * @return the outSoilErosion6
	 */
	public Float getOutSoilErosion6() {
		return outSoilErosion6==null?0.00f:outSoilErosion6;
	}
	/**
	 * @param outSoilErosion6 the outSoilErosion6 to set
	 */
	public void setOutSoilErosion6(Float outSoilErosion6) {
		this.outSoilErosion6 = outSoilErosion6;
	}
	/**
	 * @return the outSoilErosion7
	 */
	public Float getOutSoilErosion7() {
		return outSoilErosion7==null?0.00f:outSoilErosion7;
	}
	/**
	 * @param outSoilErosion7 the outSoilErosion7 to set
	 */
	public void setOutSoilErosion7(Float outSoilErosion7) {
		this.outSoilErosion7 = outSoilErosion7;
	}
	/**
	 * @return the outSoilErosion8
	 */
	public Float getOutSoilErosion8() {
		return outSoilErosion8==null?0.00f:outSoilErosion8;
	}
	/**
	 * @param outSoilErosion8 the outSoilErosion8 to set
	 */
	public void setOutSoilErosion8(Float outSoilErosion8) {
		this.outSoilErosion8 = outSoilErosion8;
	}
	/**
	 * @return the outSoilErosion9
	 */
	public Float getOutSoilErosion9() {
		return outSoilErosion9==null?0.00f:outSoilErosion9;
	}
	/**
	 * @param outSoilErosion9 the outSoilErosion9 to set
	 */
	public void setOutSoilErosion9(Float outSoilErosion9) {
		this.outSoilErosion9 = outSoilErosion9;
	}
	/**
	 * @return the outSoilErosion10
	 */
	public Float getOutSoilErosion10() {
		return outSoilErosion10==null?0.00f:outSoilErosion10;
	}
	/**
	 * @param outSoilErosion10 the outSoilErosion10 to set
	 */
	public void setOutSoilErosion10(Float outSoilErosion10) {
		this.outSoilErosion10 = outSoilErosion10;
	}
	/**
	 * @return the outSoilErosion11
	 */
	public Float getOutSoilErosion11() {
		return outSoilErosion11==null?0.00f:outSoilErosion11;
	}
	/**
	 * @param outSoilErosion11 the outSoilErosion11 to set
	 */
	public void setOutSoilErosion11(Float outSoilErosion11) {
		this.outSoilErosion11 = outSoilErosion11;
	}
	/**
	 * @return the outSoilErosion12
	 */
	public Float getOutSoilErosion12() {
		return outSoilErosion12==null?0.00f:outSoilErosion12;
	}
	/**
	 * @param outSoilErosion12 the outSoilErosion12 to set
	 */
	public void setOutSoilErosion12(Float outSoilErosion12) {
		this.outSoilErosion12 = outSoilErosion12;
	}
	/**
	 * @return the outSoilErosion13
	 */
	public Float getOutSoilErosion13() {
		return outSoilErosion13==null?0.00f:outSoilErosion13;
	}
	/**
	 * @param outSoilErosion13 the outSoilErosion13 to set
	 */
	public void setOutSoilErosion13(Float outSoilErosion13) {
		this.outSoilErosion13 = outSoilErosion13;
	}
	/**
	 * @return the outSoilErosion14
	 */
	public Float getOutSoilErosion14() {
		return outSoilErosion14==null?0.00f:outSoilErosion14;
	}
	/**
	 * @param outSoilErosion14 the outSoilErosion14 to set
	 */
	public void setOutSoilErosion14(Float outSoilErosion14) {
		this.outSoilErosion14 = outSoilErosion14;
	}
	/**
	 * @return the outSoilErosion15
	 */
	public Float getOutSoilErosion15() {
		return outSoilErosion15==null?0.00f:outSoilErosion15;
	}
	/**
	 * @param outSoilErosion15 the outSoilErosion15 to set
	 */
	public void setOutSoilErosion15(Float outSoilErosion15) {
		this.outSoilErosion15 = outSoilErosion15;
	}
	/**
	 * @return the outSoilErosion16
	 */
	public Float getOutSoilErosion16() {
		return outSoilErosion16==null?0.00f:outSoilErosion16;
	}
	/**
	 * @param outSoilErosion16 the outSoilErosion16 to set
	 */
	public void setOutSoilErosion16(Float outSoilErosion16) {
		this.outSoilErosion16 = outSoilErosion16;
	}
	/**
	 * @return the outSoilErosion17
	 */
	public Float getOutSoilErosion17() {
		return outSoilErosion17==null?0.00f:outSoilErosion17;
	}
	/**
	 * @param outSoilErosion17 the outSoilErosion17 to set
	 */
	public void setOutSoilErosion17(Float outSoilErosion17) {
		this.outSoilErosion17 = outSoilErosion17;
	}
	/**
	 * @return the outSoilErosion18
	 */
	public Float getOutSoilErosion18() {
		return outSoilErosion18==null?0.00f:outSoilErosion18;
	}
	/**
	 * @param outSoilErosion18 the outSoilErosion18 to set
	 */
	public void setOutSoilErosion18(Float outSoilErosion18) {
		this.outSoilErosion18 = outSoilErosion18;
	}
	/**
	 * @return the outSoilErosion19
	 */
	public Float getOutSoilErosion19() {
		return outSoilErosion19==null?0.00f:outSoilErosion19;
	}
	/**
	 * @param outSoilErosion19 the outSoilErosion19 to set
	 */
	public void setOutSoilErosion19(Float outSoilErosion19) {
		this.outSoilErosion19 = outSoilErosion19;
	}
	/**
	 * @return the outSoilErosion20
	 */
	public Float getOutSoilErosion20() {
		return outSoilErosion20==null?0.00f:outSoilErosion20;
	}
	/**
	 * @param outSoilErosion20 the outSoilErosion20 to set
	 */
	public void setOutSoilErosion20(Float outSoilErosion20) {
		this.outSoilErosion20 = outSoilErosion20;
	}
}
