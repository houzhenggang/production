package com.rubik.eds.common.utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;


public class CommonUtils {

	/**
	 * 格式化数字（小数位数）
	 * @return
	 */
	public static String formatNumber(Object number){
		//定义格式化格式
		DecimalFormat decimalFormat = new DecimalFormat("0.##");
		if(number != null){
			return decimalFormat.format(number);
		}
		return null;
	}
	
	/**
	 * 格式化数字（小数位数）
	 * @return
	 */
	public static String formatNumber(Float number, Integer decimal){
		//定义格式化格式
//		String decimals = "0";
//		if(decimal > 0){
//			decimals += ".";
//		}
//		for(int i=0; i<decimal; i++){
//			decimals += "#";
//		}
//		DecimalFormat decimalFormat = new DecimalFormat(decimals);
//		if(number != null){
//			return decimalFormat.format(number);
//		}
		float m = 1;
		for(int i=0; i<decimal; i++){
			m = m*10;
		}
		if(number != null){
			return String.valueOf(Math.round(number*m)/m);
		}
		return null;
	}
	
	/**
	 * 土壤湿度四舍五入，并保证不超过99
	 * @param args
	 */
	public static String formatRound(Float number){
		String string = "";
		Integer num;
		if(number != null){
			if(number > 99){
				number = 99f;
			}
			num = Math.abs(Math.round(number));
			if(num < 10){
				string = "0" + num;
			}else{
				string = String.valueOf(num);
			}
			return string;
		}
		return null;
	}
	
	/**
	 * 补位
	 * @param number
	 * @return
	 */
	public static String formatLength(Float number){
		String num = "";
		if(number != null){
			num = String.valueOf(Math.round(number));
			int length = 4-num.length();
			for(int i=0; i < length; i++){
				num += "/";
			}
			num = " 3" + num;
		}
		
		return num;
	}
	
	/**
	 * 格式化字符串对象
	 * @param string
	 * @return
	 */
	public static String formatString(Object string){
		if(string == null){
			return null;
		}
		return String.valueOf(string);
	}
	
	/**
	 * 获取当前日期上个月的最后一天
	 * @param date
	 * @return
	 */
	public static Date getLastdayOfLastmonth(Date date){
		//获取日历对象
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}
}
