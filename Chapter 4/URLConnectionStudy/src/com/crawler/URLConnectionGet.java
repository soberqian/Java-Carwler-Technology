package com.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnectionGet {

	public static void main(String[] args) throws IOException {
		//初始化URL
		URL url = new URL("http://www.w3school.com.cn/b.asp");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
		//允许Input
		conn.setDoInput(true);  
		conn.setRequestMethod("GET");  //设置请求的方法Get
//		conn.setRequestMethod("POST"); //注意该网页只能使用GET请求
		conn.connect();  //链接操作
		int statusCode = conn.getResponseCode(); //获取响应状态码
		String responseBody = null;
		//如果响应状态码为200
		if (HttpURLConnection.HTTP_OK == statusCode) {  
			// 定义BufferedReader输入流来读取URL的响应 ,这里设置编码
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), "GBK"));  
			//读取内容
			String readLine = null;
			StringBuffer response = new StringBuffer();
			while (null != (readLine = bufferedReader.readLine())) {
				response.append(readLine);
			}

			bufferedReader.close();
			responseBody = response.toString();
		}
		System.out.println(responseBody);
	}
}
