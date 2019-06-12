package com.crawler;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpclientProxy1 {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// 设置代理HttpHost
		HttpHost proxy = new HttpHost("171.221.239.11",808, "http");
		// 设置要访问的HttpHost,即是目标站点的HttpHost
		HttpHost target = new HttpHost("baidu.com", 80);
		// 设置认证
		CredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials("username", "password"));

		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultCredentialsProvider(provider).build();

		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

		HttpGet httpGet = new HttpGet("/");

		httpGet.setConfig(config);

		CloseableHttpResponse resp = httpClient.execute(target, httpGet);

		if (resp.getStatusLine().getStatusCode() == 200)
		{
			System.out.println("OK");
		}
	}
}
