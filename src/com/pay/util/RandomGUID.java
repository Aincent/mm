package com.pay.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class RandomGUID {
	private static Random myRand;
	private static SecureRandom mySecureRand;
	private static String s_id;

	static {
		mySecureRand = new SecureRandom();

		long secureInitializer = mySecureRand.nextLong();
		myRand = new Random(secureInitializer);

		try {
			s_id = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public String valueBeforeMD5 = "";
	public String valueAfterMD5 = "";

	public RandomGUID() {
		getRandomGUID(false);
	}

	public RandomGUID(boolean secure) {
		getRandomGUID(secure);
	}

	private void getRandomGUID(boolean secure) {
		MessageDigest md5 = null;
		StringBuffer sbValueBeforeMD5 = new StringBuffer();

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: " + e);
		}

		try {
			long time = System.currentTimeMillis();
			long rand = 0;

			if (secure) {
				rand = mySecureRand.nextLong();
			} else {
				rand = myRand.nextLong();
			}

			sbValueBeforeMD5.append(s_id);
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(time));
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(rand));

			valueBeforeMD5 = sbValueBeforeMD5.toString();
			md5.update(valueBeforeMD5.getBytes("utf-8"));

			byte[] array = md5.digest();
			StringBuffer sb = new StringBuffer();

			for (int j = 0; j < array.length; ++j) {
				int b = array[j] & 0xFF;

				if (b < 0x10) {
					sb.append('0');
				}

				sb.append(Integer.toHexString(b));
			}

			valueAfterMD5 = sb.toString();
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
	}

	public String toString() {
		String raw = valueAfterMD5.toUpperCase();
		StringBuffer sb = new StringBuffer();
		sb.append(raw.substring(0, 8));
		sb.append("-");
		sb.append(raw.substring(8, 12));
		sb.append("-");
		sb.append(raw.substring(12, 16));
		sb.append("-");
		sb.append(raw.substring(16, 20));
		sb.append("-");
		sb.append(raw.substring(20));

		return sb.toString();
	}

	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	/**
	 * md5加密
	 * 
	 * @param strObj
	 * @return
	 */
	public static String getMD5(String strObj) {
		String md5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			md5 = byteToString(md.digest(strObj.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return md5;
	}

	public static void main(String[] args) {
		RandomGUID myGUID = new RandomGUID();
		System.out.println(myGUID.valueBeforeMD5);
		System.out.println(new RandomGUID().valueAfterMD5);
		System.out.println(new RandomGUID().valueAfterMD5);
		System.out.println(new RandomGUID().valueAfterMD5);
		System.out.println(new RandomGUID().valueAfterMD5);
		System.out.println(new RandomGUID().valueAfterMD5);
	}
}
