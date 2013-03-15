package com.rubik.eds.entity;

import java.util.List;

public class TbPlantDetails extends ReportHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3008249665745076038L;

	private Integer id;
	/**
	 * 植物物种多样性调查记录ID
	 */
	private Integer plantSpeciesId;
	/**
	 * 监测区域：围栏内1、2、3、4，围栏外1、2、3、4
	 */
	private String plantMonitorArea;
	/**
	 * 植物物种中文名称集合
	 */
	private List<String> plantNames;
	/**
	 * 植物物种名称
	 */
	private String plantName;
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
	 * 植物物种多样性调查记录ID
	 * @return the plantSpeciesId
	 */
	public Integer getPlantSpeciesId() {
		return plantSpeciesId;
	}
	/**
	 * 植物物种多样性调查记录ID
	 * @param plantSpeciesId the plantSpeciesId to set
	 */
	public void setPlantSpeciesId(Integer plantSpeciesId) {
		this.plantSpeciesId = plantSpeciesId;
	}
	/**
	 * 监测区域：围栏内1、2、3、4，围栏外1、2、3、4
	 * @return the plantMonitorArea
	 */
	public String getPlantMonitorArea() {
		return plantMonitorArea;
	}
	/**
	 * 监测区域：围栏内1、2、3、4，围栏外1、2、3、4
	 * @param plantMonitorArea the plantMonitorArea to set
	 */
	public void setPlantMonitorArea(String plantMonitorArea) {
		this.plantMonitorArea = plantMonitorArea;
	}
	/**
	 * 植物物种中文名称集合
	 * @return the plantName
	 */
	public List<String> getPlantNames() {
		return plantNames;
	}
	/**
	 * 植物物种中文名称集合
	 * @param plantName the plantName to set
	 */
	public void setPlantNames(List<String> plantNames) {
		this.plantNames = plantNames;
	}
	/**
	 * 植物物种名称
	 * @return the plantName
	 */
	public String getPlantName() {
		return plantName;
	}
	/**
	 * 植物物种名称
	 * @param plantName the plantName to set
	 */
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	
}
