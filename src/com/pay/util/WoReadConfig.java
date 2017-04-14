package com.pay.util;

import java.util.HashMap;
import java.util.Map;

public class WoReadConfig {

	private static final Map<Integer,String> payCodeMap = new HashMap<Integer,String>();
	
	static{
		payCodeMap.put(1, "000000010979");
		payCodeMap.put(800, "000000011336");
		payCodeMap.put(1000, "000000011337");
		payCodeMap.put(1200, "000000011338");
		payCodeMap.put(1500, "000000011339");
		payCodeMap.put(1600, "000000011340");
		payCodeMap.put(1800, "000000011341");
		payCodeMap.put(2000, "000000011342");
		payCodeMap.put(2500, "000000011343");
		payCodeMap.put(3000, "000000011344");
		payCodeMap.put(100, "000000011331");
		payCodeMap.put(200, "000000011332");
		payCodeMap.put(300, "000000011333");
		payCodeMap.put(400, "000000011334");
		payCodeMap.put(500, "000000011335");
	}
	
	public static String getPayCodeByPrice(int price){
		return payCodeMap.get(price);
	}
	
	
	
}
