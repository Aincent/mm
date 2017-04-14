package com.pay.util;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
public class RedisUtil {

	private static JedisPool pool = null;

	public synchronized static JedisPool getPool() {
//		if (pool == null) {
//			JedisPoolConfig config = new JedisPoolConfig();
//			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
//			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//			config.setMaxTotal(10000);
//			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
//			config.setMaxIdle(5);
//			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
//			config.setMaxWaitMillis(1000 * 5); 
//			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//			config.setTestOnBorrow(true);
//			pool = new JedisPool(config, "121.42.165.167", 16379, 1000 * 5);
//		}
		return pool;
	}
	/**
	 * 
	 * @param orderPrefix 订单前缀
	 * @param orderNo 订单号
	 * @param orderInfo 订单信息&nbsp<font color="red">用jsonObject转成string）</font>
	 */
	public static String set(String orderPrefix,String orderNo,String orderInfo){
		String res = "FALSE";
		JedisPool pool = null;
		Jedis redis = null;
		try {
			pool = getPool();
			redis = pool.getResource();
			res = redis.set(orderPrefix+orderNo, orderInfo);
		} catch (Exception e) {
			//释放redis对象
			pool.returnBrokenResource(redis);
			e.printStackTrace();
		} finally{
			returnResource(pool, redis);
		}
		return res;
	}
	/**
	 * 为值设置有效期
	 * @param orderPrefix 订单前缀
	 * @param orderNo 订单号
	 * @param orderInfo 订单信息&nbsp<font color="red">用jsonObject转成string）</font>
	 * @param seconds 设置有效期（秒）
	 */
	public static String set(String orderPrefix,String orderNo,String orderInfo,int seconds){
		String res = "FALSE";
		JedisPool pool = null;
		Jedis redis = null;
		try {
			pool = getPool();
			redis = pool.getResource();
			res = redis.setex(orderPrefix+orderNo, seconds, orderInfo);
		} catch (Exception e) {
			//释放redis对象
			pool.returnBrokenResource(redis);
			e.printStackTrace();
		} finally{
			returnResource(pool, redis);
		}
		return res;
	}
	/**
	 * 获取指定订单，并返回json
	 * @param orderPrefix_orderNo 存入后的订单号
	 */
	public static JSONObject get(String orderPrefix_orderNo){
		JedisPool pool = null;
		Jedis redis = null;
		try {
			pool = getPool();
			redis = pool.getResource();
			String orderInfo = redis.get(orderPrefix_orderNo);
			if(orderInfo != null){
				return JSONObject.parseObject(orderInfo);
			}
		} catch (Exception e) {
			//释放redis对象
			pool.returnBrokenResource(redis);
			e.printStackTrace();
		} finally{
			returnResource(pool, redis);
		}
		return null;
	}
	
	/**
	 * 删除指定订单
	 * @param orderPrefix_orderNo 存入后的订单号
	 */
	public static long del(String orderPrefix_orderNo){
		JedisPool pool = null;
		Jedis redis = null;
		try {
			pool = getPool();
			redis = pool.getResource();
			return redis.del(orderPrefix_orderNo);
		} catch (Exception e) {
			//释放redis对象
			pool.returnBrokenResource(redis);
			e.printStackTrace();
		} finally{
			returnResource(pool, redis);
		}
		return 0;
	}
	/**
	 * 返回指定前缀的所有key集合
	 * @param orderPrefix 订单前缀
	 * @return
	 */
	public static Set<String> keys(String orderPrefix) {
		JedisPool pool = null;
		Jedis redis = null;
		try {
			pool = getPool();
			redis = pool.getResource();
			return redis.keys(orderPrefix+"*");//匹配key以order-key-开头的所有键
		} catch (Exception e) {
			//释放redis对象
			pool.returnBrokenResource(redis);
			e.printStackTrace();
		} finally{
			returnResource(pool, redis);
		}
		return new HashSet<String>();
	}
	/**
     * 返还到连接池
     * 
     * @param pool 
     * @param redis
     */
	private static void returnResource(JedisPool pool, Jedis redis) {
		if (redis != null) {
			pool.returnResource(redis);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(RedisUtil.get("foo"));
//		Jedis redis = getPool().getResource();
//		{"mobile":"4EEE57B3E619AE263D00B28A98EE428C","linkid":"4EEE57B3E619AE263D00B28A98EE428C20141105100359","spnumber":"1111111111","src":"mdo","sheng":"??????","opertype":"yd","feetype":"2","feecode":"200","channel":"611031","itemid":"247036","cmd":"yx,247036,1,9bf3,1800529,611031","tm":"20141105100359"}
	}

}