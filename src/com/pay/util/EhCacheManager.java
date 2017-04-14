//package com.pay.util;
//
//import com.pay.SpringContext;
//
//
//public class EhCacheManager {
//	
//	private static CacheManager cacheManager = null;
//	
//	public static CacheManager getInstance(){
//		if(cacheManager == null){
//			cacheManager = (CacheManager) SpringContext.getContext()
//            .getBean("cacheManager");
//		}
//		return cacheManager;
//	}
//	
//    /**
//     * 缓存对象
//     * */
//    public static void add(String key, Object value)
//    {
//    	CacheManager manager = getInstance();
//    	Cache myCache = new Cache("baseCache", 1, true, false, 0, 0); // Cache上设置为永不过期
//    	manager.addCache(myCache);
//    	Element elementPut = new Element(key, value, 2, 0); // timeToIdleSeconds为2秒
//    	myCache.put(elementPut);// 放入缓存
//    }
//    /**
//     * 获取对象
//     * */
//    public static Object get(String key)
//    {
//    	CacheManager manager = getInstance();
//    	Cache myCache = new Cache("baseCache", 1, true, false, 0, 0); // Cache上设置为永不过期
//    	manager.addCache(myCache);
//        return myCache.get(key);
//    }
//
//    /**
//     * 删除对象
//     * */
//    public static boolean delete(String key)
//    {
//    	CacheManager manager = getInstance();
//    	Cache myCache = new Cache("baseCache", 1, true, false, 0, 0); // Cache上设置为永不过期
//    	manager.addCache(myCache);
//        return myCache.remove(key);
//    }
//	
//
//}
