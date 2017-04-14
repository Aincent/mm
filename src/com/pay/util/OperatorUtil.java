package com.pay.util;


public class OperatorUtil {
	
	public static void main(String[] args) {
		System.out.println(OperatorUtil.getOperatorType("460026671123178", "wifi"));;
		System.out.println(OperatorUtil.getOperatorTypebyImsi("460026671123178"));;
	}
	public static int getOperatorType(String imsi, String net) {
		int opid = getOperatorTypebyImsi(PublicUtil.replaceNull(imsi));
		if (opid == 0) {
			opid = getOperatorTypeByNet(PublicUtil.replaceNull(net));
		}
		return opid;
	}

	/**
	 * 作者: Jessie<BR>
	 * 时间:2014年8月21日上午10:46:00<BR>
	 * 
	 * @param net
	 * @return 功能:<BR>
	 *         网络分析运营商 返回值:int<BR>
	 */
	public static int getOperatorTypeByNet(String net) {
		if (!"".equals(net)) {
			if (net.startsWith("cm") || net.startsWith("CM")) {
				return 1;
			} else if (net.startsWith("3g") || net.startsWith("uni")
					|| net.startsWith("UNI")) {
				return 2;
			} else if (net.startsWith("ct") || net.startsWith("CT")) {
				return 3;
			}
		}
		return 0;
	}
	/**
	 * 作者: Jessie<BR>
	 * 时间:2014年8月21日上午10:46:24<BR>
	 * 
	 * @param imsi
	 * @return 功能:<BR>
	 *         imsi分析运营商 返回值:int<BR>
	 */
	public static int getOperatorTypebyImsi(String imsi) {
		if (!"".equals(imsi)) {
			if (imsi.startsWith("46000") || imsi.startsWith("46002")
					|| imsi.startsWith("46007") || imsi.startsWith("46020")) {
				return 1;
			} else if (imsi.startsWith("46001") || imsi.startsWith("46006")) {
				return 2;
			} else if (imsi.startsWith("46003") || imsi.startsWith("46005") || imsi.startsWith("46011")) {
				return 3;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
}
