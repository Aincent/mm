package com.pay.util;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.pay.SpringContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MqRedisDb {
	private static JedisConnectionFactory redisConnFactory = null;
	// private static Jedis redisClient = null;
	private static JedisPool pool = null;

	private static Jedis getInstance() {
		if (pool == null) {
			redisConnFactory = (JedisConnectionFactory) SpringContext.getContext().getBean("redisConnectionFactory");
			pool = new JedisPool(redisConnFactory.getPoolConfig(), redisConnFactory.getHostName(),
					redisConnFactory.getPort());
			// redisClient = redisConnFactory.getShardInfo().createResource();
		}
		return pool.getResource();
	}

	/**
	 * 将java对象转换成json字符串 存入消息队列
	 *
	 * @param bean
	 * @return
	 */
	public static Long add(String redisKey,String value) {
		if(value == null){
			return null;
		}
		return NgsteamRedisFactory.rpush(redisKey, value);
	}

	/**
	 * 返回指定列表范围内的元素
	 * 
	 * @param key
	 *            列表名
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public static String lpop(String redisKey) {
		Jedis redis = null;
		try {
			redis = getInstance();
			return redis.lpop(redisKey);
		} catch (Exception e) {
			pool.returnBrokenResource(redis);
			e.printStackTrace();
		} finally {
			pool.returnResource(redis);
		}
		return null;
	}

}
