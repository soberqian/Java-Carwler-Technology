package com.crawler.image;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.log4j.Logger;
public class ImageDownloaderUtil {
	/**
	 * 该方法使用 HttpClient下载图片、PDF、压缩文件等
	 */
	private static Logger logger = Logger.getLogger(ImageDownloaderUtil.class);
	public static synchronized void downLoadImage(String url,String fileName){
		HttpClient httpClient = HttpClients.custom().build(); //初始化httpclient
		HttpGet httpGet = new HttpGet(url);
		//获取结果
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//非常简单的下载文件的方法
		try {
			OutputStream out = new FileOutputStream(fileName);
			httpResponse.getEntity().writeTo(out);
		} catch (Exception e) {
			logger.warn("write file error", e);
		}
		try {
			EntityUtils.consume(httpResponse.getEntity()); //消耗实体
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
