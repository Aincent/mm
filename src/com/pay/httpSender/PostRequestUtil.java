package com.pay.httpSender;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

import com.zc.httpSender.RequestAndHandler;
import com.zc.httpSender.ResponseHandlers;

public class PostRequestUtil implements RequestAndHandler<String>{

	private String url;
	private HttpEntity entity;
	private Map<String, String> headers;
	public PostRequestUtil(String url,HttpEntity entity,Map<String, String> headers){
		this.url = url;
		this.entity = entity;
		this.headers = headers;
	}
	@Override
	public HttpUriRequest getRequest() {
		HttpPost post = new HttpPost(url);
		if (headers != null) {
			Set<String> keys = headers.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				post.addHeader(key, headers.get(key));
			}
		}
		post.setEntity(entity);
		return post;
	}

	@Override
	public ResponseHandler<String> getResponseHandler() {
		return ResponseHandlers.getStringResponseHandler();
	}

}
