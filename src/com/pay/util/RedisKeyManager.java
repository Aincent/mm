package com.pay.util;


/**
 * redis 键管理器（为了避免key之间类型错用，属性名和值必须一致）
 * @author M-D
 *
 */
public class RedisKeyManager {

	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓String类型的键↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	/**
	 * 用户请求限制
	 */
	public final static String PREFIX_SMSPAY_REQUESTLIMIT	= "PREFIX_SMSPAY_REQUESTLIMIT";
	/**
	 * 用户通道日月限前缀
	 */
	public final static String PREFIX_SMSPAY_PASSLIMIT	= "PREFIX_SMSPAY_PASSLIMIT";
	/**
	 * 用户请求支付限制键前缀
	 */
	public final static String PREFIX_SMSPAY_PAYLIMIT	= "PREFIX_SMSPAY_PAYLIMIT";
	/**
	 * 用户使用天翼阅读短信指令键前缀
	 */
	public final static String PREFIX_SMSPAY_TIANYIYUEDU	= "PREFIX_SMSPAY_TIANYIYUEDU";
	/**
	 * CP订单前缀
	 */
	public final static String PREFIX_CPSYNC = "PREFIX_CPSYNC";
}
