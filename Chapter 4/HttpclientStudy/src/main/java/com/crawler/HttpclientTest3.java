package com.crawler;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpclientTest3 {
	public static void main(String[] args) throws ParseException, IOException  {
		CloseableHttpClient httpClient = HttpClients.createDefault(); 
		HttpGet httpGet=new HttpGet("http://www.w3school.com.cn/b.asp");
		CloseableHttpResponse response = null;//请求响应
		try {
			response = httpClient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String entity = EntityUtils.toString (response.getEntity(),"gbk");
		System.out.println(entity);
		response.close();
	}
}

