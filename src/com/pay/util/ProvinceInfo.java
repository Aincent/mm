package com.pay.util;

import java.util.HashMap;
import java.util.Map;


public class ProvinceInfo {
	public static final Map<String, String> cm_ProviceInfo = new HashMap<String, String>();
	public static final Map<String, String> wo_ProviceInfo = new HashMap<String, String>();
	public static final Map<String, String> ct_ProviceInfo = new HashMap<String, String>();
	static {
		// 移动
		cm_ProviceInfo.put("01", "北京");
		cm_ProviceInfo.put("02", "天津");
		cm_ProviceInfo.put("03", "河北");
		cm_ProviceInfo.put("04", "山西");
		cm_ProviceInfo.put("05", "内蒙古");
		cm_ProviceInfo.put("06", "辽宁");
		cm_ProviceInfo.put("07", "吉林");
		cm_ProviceInfo.put("08", "黑龙江");
		cm_ProviceInfo.put("09", "上海");
		cm_ProviceInfo.put("10", "江苏");
		cm_ProviceInfo.put("11", "浙江");
		cm_ProviceInfo.put("12", "安徽");
		cm_ProviceInfo.put("13", "福建");
		cm_ProviceInfo.put("14", "江西");
		cm_ProviceInfo.put("15", "山东");
		cm_ProviceInfo.put("16", "河南");
		cm_ProviceInfo.put("17", "湖北");
		cm_ProviceInfo.put("18", "湖南");
		cm_ProviceInfo.put("19", "广东");
		cm_ProviceInfo.put("20", "广西");
		cm_ProviceInfo.put("21", "海南");
		cm_ProviceInfo.put("22", "四川");
		cm_ProviceInfo.put("23", "贵州");
		cm_ProviceInfo.put("24", "云南");
		cm_ProviceInfo.put("25", "西藏");
		cm_ProviceInfo.put("26", "陕西");
		cm_ProviceInfo.put("27", "甘肃");
		cm_ProviceInfo.put("28", "青海");
		cm_ProviceInfo.put("29", "宁夏");
		cm_ProviceInfo.put("30", "新疆");
		cm_ProviceInfo.put("31", "重庆");
		// 联通
		wo_ProviceInfo.put("11", "北京");
		wo_ProviceInfo.put("13", "天津");
		wo_ProviceInfo.put("18", "河北");
		wo_ProviceInfo.put("19", "山西");
		wo_ProviceInfo.put("10", "内蒙古");
		wo_ProviceInfo.put("91", "辽宁");
		wo_ProviceInfo.put("90", "吉林");
		wo_ProviceInfo.put("97", "黑龙江");
		wo_ProviceInfo.put("31", "上海");
		wo_ProviceInfo.put("34", "江苏");
		wo_ProviceInfo.put("36", "浙江");
		wo_ProviceInfo.put("30", "安徽");
		wo_ProviceInfo.put("38", "福建");
		wo_ProviceInfo.put("75", "江西");
		wo_ProviceInfo.put("17", "山东");
		wo_ProviceInfo.put("76", "河南");
		wo_ProviceInfo.put("71", "湖北");
		wo_ProviceInfo.put("74", "湖南");
		wo_ProviceInfo.put("51", "广东");
		wo_ProviceInfo.put("59", "广西");
		wo_ProviceInfo.put("50", "海南");
		wo_ProviceInfo.put("81", "四川");
		wo_ProviceInfo.put("85", "贵州");
		wo_ProviceInfo.put("86", "云南");
		wo_ProviceInfo.put("79", "西藏");
		wo_ProviceInfo.put("84", "陕西");
		wo_ProviceInfo.put("87", "甘肃");
		wo_ProviceInfo.put("70", "青海");
		wo_ProviceInfo.put("88", "宁夏");
		wo_ProviceInfo.put("89", "新疆");
		wo_ProviceInfo.put("83", "重庆");
		
		
		ct_ProviceInfo.put("010", "北京");
		ct_ProviceInfo.put("021", "上海");
		ct_ProviceInfo.put("022", "天津");
		ct_ProviceInfo.put("023", "重庆");
		ct_ProviceInfo.put("031", "河北");
		ct_ProviceInfo.put("033", "河北");
		ct_ProviceInfo.put("057", "浙江");
		ct_ProviceInfo.put("058", "浙江");
		ct_ProviceInfo.put("024", "辽宁");
		ct_ProviceInfo.put("041", "辽宁");
		ct_ProviceInfo.put("042", "辽宁");
		ct_ProviceInfo.put("027", "湖北");
		ct_ProviceInfo.put("071", "湖北");
		ct_ProviceInfo.put("072", "湖北");
		ct_ProviceInfo.put("025", "江苏");
		ct_ProviceInfo.put("051", "江苏");
		ct_ProviceInfo.put("052", "江苏");
		ct_ProviceInfo.put("047", "内蒙古");
		ct_ProviceInfo.put("048", "内蒙古");
		ct_ProviceInfo.put("079", "江西");
		ct_ProviceInfo.put("070", "江西");
		ct_ProviceInfo.put("035", "山西");
		ct_ProviceInfo.put("093", "甘肃");
		ct_ProviceInfo.put("094", "甘肃");
		ct_ProviceInfo.put("053", "山东");
		ct_ProviceInfo.put("045", "黑龙江");
		ct_ProviceInfo.put("059", "福建");
		ct_ProviceInfo.put("020", "广东");
		ct_ProviceInfo.put("075", "广东");
		ct_ProviceInfo.put("076", "广东");
		ct_ProviceInfo.put("066", "广东");
		ct_ProviceInfo.put("028", "四川");
		ct_ProviceInfo.put("081", "四川");
		ct_ProviceInfo.put("082", "四川");
		ct_ProviceInfo.put("083", "四川");
		ct_ProviceInfo.put("084", "四川");
		ct_ProviceInfo.put("073", "湖南");
		ct_ProviceInfo.put("074", "湖南");
		ct_ProviceInfo.put("037", "河南");
		ct_ProviceInfo.put("039", "河南");
		ct_ProviceInfo.put("087", "云南");
		ct_ProviceInfo.put("088", "云南");
		ct_ProviceInfo.put("069", "云南");
		ct_ProviceInfo.put("055", "安徽");
		ct_ProviceInfo.put("056", "安徽");
		ct_ProviceInfo.put("095", "宁夏");
		ct_ProviceInfo.put("043", "吉林");
		ct_ProviceInfo.put("044", "吉林");
		ct_ProviceInfo.put("077", "广西");
		ct_ProviceInfo.put("085", "贵州");
		ct_ProviceInfo.put("029", "陕西");
		ct_ProviceInfo.put("091", "陕西");
		ct_ProviceInfo.put("097", "青海");
		ct_ProviceInfo.put("099", "新疆");
		ct_ProviceInfo.put("090", "新疆");
		
		ct_ProviceInfo.put("089", "海南");
		ct_ProviceInfo.put("089", "西藏");
		
		
		
		
		
	}

	public static String getProvince(String iccid) {
		String province = null;
		if (PublicUtil.replaceNull(iccid).length() == 20) {
			if (iccid.startsWith("898600") || iccid.startsWith("898602") || iccid.startsWith("898604") || 
					iccid.startsWith("898605") || iccid.startsWith("898607")|| iccid.startsWith("898608")|| 
					iccid.startsWith("898609")|| iccid.startsWith("898610")) {
				String provinceid = iccid.substring(8, 10);
				System.out.println(provinceid);
				province = cm_ProviceInfo.get(provinceid);
			} else if (iccid.startsWith("898601")) {
				String provinceid = iccid.substring(9, 11);
				System.out.println(provinceid);
				province = wo_ProviceInfo.get(provinceid);
			} else if (iccid.startsWith("898603")||iccid.startsWith("898606")||iccid.startsWith("898611")) {
				String provinceid = iccid.substring(10, 13);
				System.out.println(provinceid);
				if(provinceid.startsWith("089")){
					if(provinceid.equals("0890")||provinceid.equals("0890")||provinceid.equals("0890")){
						province = "海南";
					}else if(provinceid.equals("0891")||provinceid.equals("0892")||provinceid.equals("0893")){
						province = "西藏";
					}
				}else{
					if(!provinceid.startsWith("0")){
						provinceid = "0"+provinceid.substring(0,2);
					}
					System.out.println(provinceid);
					province = ct_ProviceInfo.get(provinceid);
				}
				
			}
		}
		return PublicUtil.replaceNull(province);
	}
	public static void main(String[] args) {
		System.out.println(getProvince("89860071011450000442"));
	}
}
