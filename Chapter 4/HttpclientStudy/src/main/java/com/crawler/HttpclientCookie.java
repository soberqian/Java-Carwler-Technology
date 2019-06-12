package com.crawler;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
public class HttpclientCookie {

	public static void main(String[] args) {
		RequestConfig globalConfig = RequestConfig.custom()
		        .setCookieSpec(CookieSpecs.DEFAULT)
		        .build();
		CloseableHttpClient httpclient = HttpClients.custom()
		        .setDefaultRequestConfig(globalConfig)
		        .build();
		RequestConfig localConfig = RequestConfig.copy(globalConfig)
		        .setCookieSpec(CookieSpecs.STANDARD_STRICT)
		        .build();
		HttpGet httpGet = new HttpGet("/");
		httpGet.setConfig(localConfig);
	}

}
