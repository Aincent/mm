package com.pay.httpSender;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import com.zc.httpSender.RequestAndHandler;
import com.zc.httpSender.ResponseHandlers;

public class GetRequestUtil implements RequestAndHandler<String>{

	private String url;
	public GetRequestUtil(String url ){
		this.url = url;
	}
	@Override
	public HttpUriRequest getRequest() {
		HttpGet get = new HttpGet(url);
		return get;
	}

	@Override
	public ResponseHandler<String> getResponseHandler() {
		return ResponseHandlers.getStringResponseHandler();
	}

}
