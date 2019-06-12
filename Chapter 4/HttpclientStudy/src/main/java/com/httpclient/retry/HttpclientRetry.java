package com.httpclient.retry;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpclientRetry {

	public static void main(String[] args) throws ParseException, IOException {
		//配置信息
		RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
				.setExpectContinueEnabled(true)
				.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
				.setConnectionRequestTimeout(10*1000)
				.setConnectTimeout(5*1000)
				.setSocketTimeout(5*1000)
				.build();
		HttpClient  httpClient = HttpClients.custom()
				.setDefaultRequestConfig(defaultConfig)
				.setRetryHandler(new DefaultHttpRequestRetryHandler())
				.build();
		//自定义设置重试次数
		/*HttpClient  httpClient = HttpClients.custom()
				.setDefaultRequestConfig(defaultConfig)
				.setRetryHandler(new DefaultHttpRequestRetryHandler(5, true))
				.build();*/
		HttpGet httpGet = new HttpGet("https://twitter.com/?lang=zh-cn");
		HttpResponse response = null;  
		try { 
			response = httpClient.execute(httpGet);  //执行请求
		}catch (Exception e){  
			e.printStackTrace();  
		} 
		String result = EntityUtils.toString(response.getEntity(),"gbk");  //获取结果，html
		System.out.println(result);   //输出结果
		EntityUtils.consume(response.getEntity());        //消耗实体
	}

}
