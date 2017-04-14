package com.pay.util;

import java.util.HashMap;
import java.util.Map;


public class ProvinceCode {
	public static final Map<String, String> proviceInfo = new HashMap<String, String>();
	public static final Map<String, String> proviceInfo1 = new HashMap<String, String>();
	public static final Map<String, String> proviceInfo2 = new HashMap<String, String>();
	public static final Map<String, String> codeInfo = new HashMap<String, String>();
	static {
		
		proviceInfo.put("安徽","01");		proviceInfo.put("辽宁","17");		
		proviceInfo.put("北京","02");		proviceInfo.put("内蒙古","18");		
		proviceInfo.put("福建","03");		
		proviceInfo.put("甘肃","04");		proviceInfo.put("宁夏","19");		
		proviceInfo.put("广西","06");		proviceInfo.put("青海","20");		
		proviceInfo.put("贵州","07");		proviceInfo.put("山东","21");		
		proviceInfo.put("海南","08");		proviceInfo.put("山西","22");		
		proviceInfo.put("河北","09");		proviceInfo.put("陕西","23");		
		proviceInfo.put("河南","10");		proviceInfo.put("上海","24");		
		proviceInfo.put("黑龙江","11");	proviceInfo.put("四川","25");		
		proviceInfo.put("湖北","12");		proviceInfo.put("天津","26");		
		proviceInfo.put("湖南","13");		proviceInfo.put("西藏","27");		
		proviceInfo.put("吉林","14");		proviceInfo.put("新疆","28");		
		proviceInfo.put("江苏","15");		proviceInfo.put("云南","29");		
		proviceInfo.put("浙江","30");		proviceInfo.put("重庆","31");		
		proviceInfo.put("广东","05");		proviceInfo.put("江西","32");	
		
		proviceInfo1.put("安徽","01");		proviceInfo1.put("辽宁","17");		
		proviceInfo1.put("北京","02");		proviceInfo1.put("内蒙古","18");		
		proviceInfo1.put("福建","03");		proviceInfo1.put("宁夏","19");
		proviceInfo1.put("甘肃","04");		proviceInfo1.put("青海","20");		
		proviceInfo1.put("广东","05");		proviceInfo1.put("山东","21");
		proviceInfo1.put("广西","06");		proviceInfo1.put("山西","22");		
		proviceInfo1.put("贵州","07");		proviceInfo1.put("陕西","23");		
		proviceInfo1.put("海南","08");		proviceInfo1.put("上海","24");		
		proviceInfo1.put("河北","09");		proviceInfo1.put("四川","25");		
		proviceInfo1.put("河南","10");		proviceInfo1.put("天津","26");		
		proviceInfo1.put("黑龙江","11");		proviceInfo1.put("西藏","27");		
		proviceInfo1.put("湖北","12");		proviceInfo1.put("新疆","28");		
		proviceInfo1.put("湖南","13");		proviceInfo1.put("云南","29");		
		proviceInfo1.put("吉林","14");		proviceInfo1.put("浙江","30");		
		proviceInfo1.put("江苏","15");		proviceInfo1.put("重庆","31");		
		proviceInfo1.put("江西","16");	
		
		
		proviceInfo2.put("辽宁","01");		proviceInfo2.put("北京","17");
		proviceInfo2.put("青海","02");		proviceInfo2.put("甘肃","18");
		proviceInfo2.put("新疆","03");		proviceInfo2.put("福建","19");
		proviceInfo2.put("陕西","04");		proviceInfo2.put("广西 ","20");
		proviceInfo2.put("上海 ","05");		proviceInfo2.put("广东","21");
		proviceInfo2.put("四川","06");		proviceInfo2.put("贵州","22");
		proviceInfo2.put("山西","07");		proviceInfo2.put("河南","23");
		proviceInfo2.put("山东","08");		proviceInfo2.put("河北","24");
		proviceInfo2.put("天津","09");		proviceInfo2.put("黑龙江","25");
		proviceInfo2.put("西藏","10");		proviceInfo2.put("海南 ","26");
		proviceInfo2.put("浙江","11");		proviceInfo2.put("湖南","27");
		proviceInfo2.put("云南","12");		proviceInfo2.put("湖北","28");
		proviceInfo2.put("内蒙古","13");		proviceInfo2.put("吉林","29");
		proviceInfo2.put("宁夏 ","14");		proviceInfo2.put("江苏","30");
		proviceInfo2.put("重庆","15");		proviceInfo2.put("江西","31");
		proviceInfo2.put("安徽","16");		proviceInfo2.put("未知","00");	
		
		
		
		codeInfo.put("01","安徽");		codeInfo.put("17","辽宁");		
		codeInfo.put("02","北京");		codeInfo.put("18","内蒙古");		
		codeInfo.put("03","福建");		
		codeInfo.put("04","甘肃");		codeInfo.put("19","宁夏");		
		codeInfo.put("06","广西");		codeInfo.put("20","青海");		
		codeInfo.put("07","贵州");		codeInfo.put("21","山东");		
		codeInfo.put("08","海南");		codeInfo.put("22","山西");		
		codeInfo.put("09","河北");		codeInfo.put("23","陕西");		
		codeInfo.put("10","河南");		codeInfo.put("24","上海");		
		codeInfo.put("11","黑龙江");		codeInfo.put("25","四川");		
		codeInfo.put("12","湖北");		codeInfo.put("26","天津");		
		codeInfo.put("13","湖南");		codeInfo.put("27","西藏");		
		codeInfo.put("14","吉林");		codeInfo.put("28","新疆");		
		codeInfo.put("15","江苏");		codeInfo.put("29","云南");		
		codeInfo.put("30","浙江");		codeInfo.put("31","重庆");		
		codeInfo.put("05","广东");		codeInfo.put("32","江西");
				
					
	}

	public static String getCode(String province) {
		String code = "";
		if (!PublicUtil.replaceNull(province).equals("") ) {
			code = proviceInfo.get(province);
		}
		return code;
	}
	public static String getProvinceByCode(String code) {
		String province = "";
		if (!PublicUtil.replaceNull(code).equals("") ) {
			province = codeInfo.get(code);
		}
		return province;
	}
	
	public static String getCode1(String province) {
		String code = "";
		if (!PublicUtil.replaceNull(province).equals("") ) {
			code = proviceInfo1.get(province);
		}
		return code;
	}
	public static String getCode2(String province) {
		String code = "00";
		if (!PublicUtil.replaceNull(province).equals("") ) {
			code = proviceInfo2.get(province);
			if(code == null){
				code = "00";
			}
		}
		return code;
	}
	public static void main(String[] args) {
		System.out.println(getCode("aaa"));
		System.out.println("211856107237314102814583232317268".substring(2, 13));
	}
}
