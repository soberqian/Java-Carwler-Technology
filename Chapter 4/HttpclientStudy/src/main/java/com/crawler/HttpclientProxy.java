package com.crawler;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpclientProxy {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setProxy(new HttpHost("171.221.239.11",808, null))
				.build();   //添加代理
		HttpGet httpGet = new HttpGet("https://blog.csdn.net/qy20115549?t=1&orderby=UpdateTime");  //设置请求的方式及网页
		HttpClient httpClient = HttpClients.custom().
				setDefaultRequestConfig(defaultRequestConfig).build();  //配置httpClient
		HttpResponse httpResponse = httpClient.execute(httpGet);  //执行请求
		if (httpResponse.getStatusLine().getStatusCode() == 200){
			String result1 = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf-8"));  //获取结果，html
			System.out.println(result1);   //输出结果
		}
	}
}
