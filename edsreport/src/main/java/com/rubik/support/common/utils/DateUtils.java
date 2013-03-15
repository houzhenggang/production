package com.rubik.support.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static final String YYYYMMDD = "yyyy/MM/dd";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YY_MM_DD = "yy-MM-dd";
	public static final String YY_MM_DD_HH_MM = "yy-MM-dd HH:mm";
	public static final String YY_MM_DD_HH_MM_SS = "yy-MM-dd HH:mm:ss";
	public static final String YYMMDD = "yyMMdd";
	public static final String YYMMDD_HHMM = "yyMMdd HHmm";
	public static final String YYMMDD_HHMMSS = "yyMMdd HHmmss";
	public static final String HH_MM = "HH:mm";
	public static final String YYYYMMDDHHMMSS= "yyyyMMddHHmmss";
	public static final String CH_YYYYMMDD= "yyyy年MM月dd日";
	public static final String CH_YYYYMM01= "yyyy年MM月01日";
	public static final String YYYY_MM_01= "yyyy-MM-01";
	public static final String SOIL_FORMAT= "M.dd";
	public static final String SOIL_ANALYSIS= "MMddmm";
	//public static final String DUST_MONITOR_BEGIN= "MM-01";
	public static final String DUST_MONITOR_END= "d";

	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		String dateString = "";
		DateFormat df = new SimpleDateFormat(pattern);
		dateString = df.format(date);
		return dateString;
	}

	public static Date parseDate(String dateString, String pattern)
			throws ParseException {
		if (dateString == null) {
			return null;
		}
		Date date = null;
		DateFormat df = new SimpleDateFormat(pattern);

		date = df.parse(dateString);

		return date;
	}

	public static Date getYearFirstSecond(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(2, 0);
		cal.set(5, 1);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		return cal.getTime();
	}

	public static Date getYearLastSecond(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(2, cal.getActualMaximum(2));
		cal.set(5, cal.getActualMaximum(5));
		cal.set(11, 23);
		cal.set(12, 59);
		cal.set(13, 59);
		return cal.getTime();
	}

	public static Date getMonthFirstSecond(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(5, 1);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		return cal.getTime();
	}

	public static Date getMonthLastSecond(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(5, cal.getActualMaximum(5));
		cal.set(11, 23);
		cal.set(12, 59);
		cal.set(13, 59);
		return cal.getTime();
	}

	public static Date getDayFirstSecond(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		return cal.getTime();
	}

	public static Date getDayLastSecond(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(11, 23);
		cal.set(12, 59);
		cal.set(13, 59);
		return cal.getTime();
	}

	public static long getElapsedSeconds(Date date1, Date date2) {
		Calendar gc1 = Calendar.getInstance();
		gc1.setTime(date1);
		Calendar gc2 = Calendar.getInstance();
		gc2.setTime(date2);
		Date d1 = gc1.getTime();
		Date d2 = gc2.getTime();
		long l1 = d1.getTime();
		long l2 = d2.getTime();
		long difference = Math.abs(l2 - l1);
		return (difference / 1000L);
	}

	public static int getElapsedDays(Date date1, Date date2) {
		int elapsed = 0;
		Calendar gc1 = Calendar.getInstance();

		Calendar gc2 = Calendar.getInstance();

		if (date2.after(date1)) {
			gc2.setTime(date2);
			gc1.setTime(date1);
		} else {
			gc2.setTime(date1);
			gc1.setTime(date2);
		}

		gc1.clear(14);
		gc1.clear(13);
		gc1.clear(12);
		gc1.clear(11);

		gc2.clear(14);
		gc2.clear(13);
		gc2.clear(12);
		gc2.clear(11);

		while (gc1.before(gc2)) {
			gc1.add(5, 1);
			++elapsed;
		}
		return elapsed;
	}

	public static int getElapsedMonths(Date date1, Date date2) {
		int elapsed = 0;
		Calendar gc1 = Calendar.getInstance();

		Calendar gc2 = Calendar.getInstance();

		if (date2.after(date1)) {
			gc2.setTime(date2);
			gc1.setTime(date1);
		} else {
			gc2.setTime(date1);
			gc1.setTime(date2);
		}

		gc1.clear(14);
		gc1.clear(13);
		gc1.clear(12);
		gc1.clear(11);
		gc1.clear(5);

		gc2.clear(14);
		gc2.clear(13);
		gc2.clear(12);
		gc2.clear(11);
		gc2.clear(5);

		while (gc1.before(gc2)) {
			gc1.add(2, 1);
			++elapsed;
		}
		return elapsed;
	}

	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(1);
	}

	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(2);
	}

	public static int getDay(Date date) {
		/* 265 */Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(5);
	}

	public static int getHour(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(11);
	}

	public static int getMinute(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(12);
	}

	public static int getSecond(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(13);
	}

	public static int getMilliSecond(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(14);
	}

	public static Date addYear(Date date, int addition) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(1, addition);
		return cal.getTime();
	}

	public static Date addMonth(Date date, int addition) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(2, addition);
		return cal.getTime();
	}

	public static Date addDay(Date date, int addition) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(5, addition);
		return cal.getTime();
	}

	public static Date addHour(Date date, int addition) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(11, addition);
		return cal.getTime();
	}

	public static Date addMinute(Date date, int addition) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(12, addition);
		return cal.getTime();
	}

	public static Date addSecond(Date date, int addition) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(13, addition);
		return cal.getTime();
	}

	public static Date addMilliSecond(Date date, int addition) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(14, addition);
		return cal.getTime();
	}
}