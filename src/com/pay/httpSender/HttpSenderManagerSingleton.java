package com.pay.httpSender;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import com.zc.httpSender.HttpSenderManager;
import com.zc.httpSender.executor.HttpSenderThreadFactory;


public enum HttpSenderManagerSingleton {

	INSTANCE;
	private final HttpSenderManager httpSenderManager ;
	
	private HttpSenderManagerSingleton(){
		httpSenderManager = new HttpSenderManager(createHttpClient(),createExecutor());
	}
	
	private CloseableHttpClient createHttpClient(){
		SocketConfig socketConfig = SocketConfig.custom()
                .setTcpNoDelay(true)
                .build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(300);	
		cm.setDefaultSocketConfig(socketConfig);
		RequestConfig requestConfig = RequestConfig.custom()
           	.setSocketTimeout(10000)
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(0)
            .build();
		CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionManager(cm)
            .setDefaultRequestConfig(requestConfig)
            .build();
		return httpClient;
	}
	
	private ThreadPoolExecutor createExecutor(){
		ThreadPoolExecutor ThreadPoolExecutor 
								= new ThreadPoolExecutor(6,6,
											2L, TimeUnit.MINUTES,
											new LinkedBlockingQueue<Runnable>(),new HttpSenderThreadFactory());
		ThreadPoolExecutor.allowCoreThreadTimeOut(true);
		return ThreadPoolExecutor;
	}
	
	public HttpSenderManager getManager(){
		return httpSenderManager;
	}
	
}
