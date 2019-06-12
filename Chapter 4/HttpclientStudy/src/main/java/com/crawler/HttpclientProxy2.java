package com.crawler;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpclientProxy2 {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClients.custom()
				.build();  //实例化httpclient
		// 设置代理
		HttpHost proxy = new HttpHost("171.221.239.11",808, null);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		HttpGet httpGet = new HttpGet("http://www.w3school.com.cn/b.asp");
		httpGet.setConfig(config); //针对实例化的请求方法设置代理
		HttpResponse httpResponse = httpClient.execute(httpGet);
		if (httpResponse.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(httpResponse.getEntity(),"gbk"); 
			System.out.println(result);
		}
	}
}
