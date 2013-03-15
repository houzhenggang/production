package com.rubik.eds.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class SelectOptions {

	private Map<String,String> windDirections;

	/**
	 * @return the windDirections
	 */
	public Map<String, String> getWindDirections() {
		//定义并初始化
		windDirections = new LinkedHashMap<String, String>();
		
		windDirections.put("E", "E 东");
		windDirections.put("S", "S 南");
		windDirections.put("W", "W 西");
		windDirections.put("N", "N 北");
		windDirections.put("NE", "NE 东北");
		windDirections.put("SE", "SE 东南");
		windDirections.put("NW", "NW 西北");
		windDirections.put("SW", "SW 西南");
		windDirections.put("ENE", "ENE 东东北");
		windDirections.put("ESE", "ESE 东东南");
		windDirections.put("NNE", "NNE 北东北");
		windDirections.put("NNW", "NNW 北西北");
		windDirections.put("SSE", "SSE 南东南");
		windDirections.put("SSW", "SSW 南西南");
		windDirections.put("WNW", "WNW 西西北");
		windDirections.put("WSW", "WSW 西西南");
		return windDirections;
	}
	
	private static Map<String, String> userRoles = new LinkedHashMap<String, String>();

	/**
	 * @return the userRoles
	 */
	public static Map<String, String> getUserRoles() {
		userRoles.put("normal", "一般用户");
		userRoles.put("super", "超级管理员");
		return userRoles;
	}
}
