package com.pay.util;

import java.io.UnsupportedEncodingException;

public class HexUtil {
	public static String hexEncode(String src) {
		byte[] srcArr = null;
		try {
			srcArr = src.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// do nothing
		}
		char[] retArr = new char[srcArr.length];
		for (int i = 0; i < srcArr.length; i++) {
			retArr[i] = (char) (srcArr[i] & 0xff);
		}
		return new String(retArr);
	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	public static String hexDecode(String src) {
		char[] srcArr = src.toCharArray();
		byte[] retArr = new byte[srcArr.length];
		for (int i = 0; i < srcArr.length; i++) {
			retArr[i] = (byte) srcArr[i];
		}
		try {
			return new String(retArr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// do nothing
		}
		return null;
	}
}
