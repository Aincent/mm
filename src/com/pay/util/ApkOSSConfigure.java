package com.pay.util;

import java.io.IOException;

public class ApkOSSConfigure {

	private String endpoint;
	private String accessKeyId;
	private String accessKeySecret;
	private String bucketName;
	private String accessUrl;

	public ApkOSSConfigure() throws IOException {
		// //通过配置文件.properties文件获取，这几项内容。
		// Properties prop = new Properties();
		// prop.load(this.getClass().getClassLoader()
		// .getResourceAsStream("conf/" + storageConfName));
		//
		// endpoint = prop.getProperty("endpoint").trim();
		// accessKeyId = prop.getProperty("accessKeyId").trim();
		// accessKeySecret = prop.getProperty("accessKeySecret").trim();
		// bucketName = prop.getProperty("bucketName").trim();
		// accessUrl = prop.getProperty("accessUrl").trim();
		// OSS的请求节点
		// endpoint = "oss-cn-hangzhou.aliyuncs.com";
		// // 阿里云“类似”用户名
		// accessKeyId = "myyvpp7jQGskHphf";
		// // 阿里云“类似”密码
		// accessKeySecret = "J3qMGkgj8pguIdVTDCQIeyBiPL56oc";
		// // bucket文件夹名称
		// bucketName = "baidumv";
		// // 访问URL
		// accessUrl = "http://apk.damaokeji.net";
		// 91you
		endpoint = "oss-cn-hangzhou.aliyuncs.com";
    	//阿里云“类似”用户名
    	accessKeyId = "myyvpp7jQGskHphf";
    	//阿里云“类似”密码
    	accessKeySecret = "J3qMGkgj8pguIdVTDCQIeyBiPL56oc";
    	//bucket文件夹名称
    	bucketName = "damaooss";
    	//访问URL
    	accessUrl = "http://image.damaokeji.net";
	}

	public ApkOSSConfigure(String endpoint, String accessKeyId, String accessKeySecret, String bucketName,
			String accessUrl) {

		this.endpoint = endpoint;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.bucketName = bucketName;
		this.accessUrl = accessUrl;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

}