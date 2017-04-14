package com.pay.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	public synchronized static String getNowTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return formatter.format(curDate);
	}

	public synchronized static String getDiyTime(long time, String type) {
		SimpleDateFormat formatter = new SimpleDateFormat(type);
		Date curDate = new Date(time);// 获取当前时间
		return formatter.format(curDate);
	}

	public synchronized static String getNowDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return formatter.format(curDate);
	}

	public synchronized static String getNowHour() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前小时
		return formatter.format(curDate);
	}

	public synchronized static String getNowTimeNum() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return formatter.format(curDate);
	}

	public static int getAge(String BirthDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(BirthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance(); // 获取当前时间毫秒值
		long now = (new Date()).getTime();
		long Birthdate = date.getTime();
		long time = now - Birthdate;
		int count = 0;
		long days = time / 1000 / 60 / 60 / 24;
		int birthYear = Integer.parseInt((BirthDate.substring(0, 4)));
		for (int i = calendar.get(Calendar.YEAR); i >= birthYear; i--)
			if ((i % 4 == 0 && !(i % 100 == 0)) || (i % 400 == 0)) {
				count++;
			}
		return ((int) days - count) / 365;
	}

	public static Date getDateForString(String BirthDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(BirthDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}
	public static Date getDateForString2(String BirthDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		try {
			date = sdf.parse(BirthDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}
}
