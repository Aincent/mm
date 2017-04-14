package com.pay.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

public class ApkOSSUpload {
	private InputStream fileContent;
	private long contentLength;
	private String fileName;
	private String remotePath;
	private ApkOSSConfigure configure;
	
	public static ApkOSSUpload custom(){
		return new ApkOSSUpload();
	}
	
	public ApkOSSUpload setFileContent(InputStream fileContent) {
		this.fileContent = fileContent;
		return this;
	}
	
	public ApkOSSUpload setContentLength(long contentLength) {
		this.contentLength = contentLength;
		return this;
	}
	
	public ApkOSSUpload setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
	
	public ApkOSSUpload setRemotePath(String remotePath) {
		this.remotePath = remotePath;
		return this;
	}
	
	public ApkOSSUpload setConfigure(ApkOSSConfigure configure) {
		this.configure = configure;
		return this;
	}

	public String upload(){
		OSSClient ossClient = new OSSClient(configure.getEndpoint(), configure.getAccessKeyId(),
				configure.getAccessKeySecret());

		// 创建上传Object的Metadata
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(contentLength);
		objectMetadata.setCacheControl("max-age=86400");
		objectMetadata.setHeader("Pragma", "max-age=86400");
		objectMetadata.setContentType(contentType(fileName.substring(fileName.lastIndexOf("."))));
		objectMetadata.setContentDisposition("inline;filename=" + fileName);

		// 执行 put 请求（上传文件），并且返回对象的MD5摘要
		ossClient.putObject(configure.getBucketName(), remotePath+fileName, fileContent, objectMetadata);
//		System.out.println(configure.getAccessUrl() + "/" + remotePath+fileName);
		return configure.getAccessUrl() + "/" + remotePath+fileName;
	}
	/** 
     * Description: 判断OSS服务文件上传时文件的contentType 
     * @Version1.0 
     * @param FilenameExtension 文件后缀 
     * @return String  
     */  
     public static String contentType(String FilenameExtension){ 
    	if(FilenameExtension.equalsIgnoreCase(".tif")){
    		return "image/tiff";
    	} else if(FilenameExtension.equalsIgnoreCase(".apk")){
    		return "application/vnd.android.package-archive";
    	} else if(FilenameExtension.equalsIgnoreCase(".gif")){
    		return "image/gif";
    	} else if(FilenameExtension.equalsIgnoreCase(".jpeg")){
    		return "image/jpeg";
    	} else if(FilenameExtension.equalsIgnoreCase(".jpg")){
    		return "image/jpeg";
    	} else if(FilenameExtension.equalsIgnoreCase(".png")){
    		return "image/png";
    	}
        return "application/vnd.android.package-archive";  
     }
	
	public static void upAliYun(String path, String value) {
		String outpath = path;
		String filename = value + ".apk";
		String apkpath = outpath + "\\" + filename;
		File apkFile = new File(apkpath);
		if (apkFile.exists()) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(apkFile);
			String cdnUrl = ApkOSSUpload.custom().setConfigure(new ApkOSSConfigure())
			.setContentLength(apkFile.length()).setFileContent(fis)
			.setFileName(filename).setRemotePath("images/").upload();
			System.out.println(cdnUrl);
			apkFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}
	
	public static String upAliYun( InputStream in, String fileName, String objectPath) {
		try {
			String cdnUrl = ApkOSSUpload.custom().setConfigure(new ApkOSSConfigure())
			.setContentLength(in.available()).setFileContent(in)
			.setFileName(fileName).setRemotePath(objectPath).upload();
			return cdnUrl;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
