package com.pay.util;

public class MobileByIMSI {
	/**
		 * 根据imsi 获取手机号段
		 * 电信分析不准确
		 * @param imsi
		 * @return 7位手机号段  如果计算不出 返回空
		 */
		public static String getMobileByIMSI(String imsi) {
			
			try{
				Double.parseDouble(imsi);
			}catch(Exception e){
				return "";
			}
			
			String moblie = "";
			
			int index = 0;
	
			if (imsi != null && !"".equals(imsi)  &&  imsi.length() > 10) {
				String mnc = imsi.substring(0, 5);
				String msin = imsi.substring(5, imsi.length());
				int nMNC = Integer.parseInt(mnc);
					switch (nMNC) {
					// 移动
					case 46000:
						index = Integer.parseInt(msin.substring(3, 4)); // 13S号码段
						if (index >= 5 && index <= 9) {
							moblie = "13";
							moblie += index;
							moblie += "0";
							moblie += msin.substring(0, 3);
						} else {
							moblie = "13";
							moblie += index+5;
							moblie += msin.substring(4, 5);
							moblie += msin.substring(0, 3);
						}
						break;
					case 46002:
						index = Integer.parseInt(msin.substring(0, 1));
						switch (index) {
							case 0:
								if (Integer.parseInt(msin.substring(1, 2)) <= 8) {
									moblie = "134" + msin.substring(1, 5);
								}
								break;
							case 1:
							case 2:
							case 8:
							case 9:
								moblie = "15"+index +  msin.substring(1, 5);
								break;
							case 3:
								moblie = "150" + msin.substring(1, 5);
								break;
							case 5:
								moblie = "183" + msin.substring(1, 5);
								break;
							case 6:
								moblie = "182" + msin.substring(1, 5);
								break;
							case 7:
								moblie = "187" + msin.substring(1, 5);
								break;
						}
						break;
					case 46007:
						index = Integer.parseInt(msin.substring(0, 1));
						switch (index) {
						case 7:
							moblie = "157" + msin.substring(1, 5);
							break;
						case 8:
							moblie = "188" + msin.substring(1, 5);
							break;
						case 9:
							moblie = "147" + msin.substring(1, 5);
							break;
						}
						break;
					// 联通
					case 46001:
						index = Integer.parseInt(msin.substring(4, 5));
						switch (index) {
						// 13段
						case 0:
						case 1:
							moblie += "130";
							moblie += msin.substring(3, 4);
							moblie += msin.substring(0, 3);
							break;
						case 9:
							moblie += "131";
							moblie += msin.substring(3, 4);
							moblie += msin.substring(0, 3);
							break;
						case 2:
							moblie += "132";
							moblie += msin.substring(3, 4);
							moblie += msin.substring(0, 3);
							break;
						// 15段
						case 3:
							moblie += "156";
							moblie += msin.substring(3, 4);
							moblie += msin.substring(0, 3);
							break;
						case 4:
							moblie += "155";
							moblie += msin.substring(3, 4);
							moblie += msin.substring(0, 3);
							break;
						// 18段
						case 6:
							moblie += "186";
							moblie += msin.substring(3, 4);
							moblie += msin.substring(0, 3);
							break;
						case 5:
							moblie += "185";
							moblie += msin.substring(3, 4);
							moblie += msin.substring(0, 3);
							break;
		
						}
						break;
					// 电信
//					case 46003:
//						if (msin.substring(0, 1).equals("0")) {
//							index = Integer.parseInt(msin.substring(1, 2));
//							switch (index) {
//							case 9:
//								if ("00" == msin.substring(2, 4)) {
//									moblie += "13301";
//									moblie += msin.substring(4, 6);
//								} else if ("53" == msin.substring(2, 4) || "54" == msin.substring(2, 4)) {
//									moblie += "133";
//									moblie += Integer.parseInt(msin.substring(2, 6)) + 4500;
//								} else {
//									moblie += "133";
//									moblie += msin.substring(2, 6);
//								}
//								break;
//							case 3:
//								moblie += "133";
//								moblie += Integer.parseInt(msin.substring(2, 6)) + 5000;
//								break;
//							}
//						} else {
//							moblie += "153";
//							moblie += msin.substring(1, 3);
//							moblie += msin.substring(4, 6);
//						}
//						break;
		
					default:
						break;
				}
			}
			return moblie;
		}
}

