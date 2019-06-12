package com.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionTest1 {

	public static void main(String[] args) throws IOException {
		//使用URLConnection请求数据
		URL url = new URL("http://www.w3school.com.cn/b.asp");
		URLConnection conn = url.openConnection();
		//获取流数据
		InputStream in=conn.getInputStream();
		// 定义BufferedReader输入流来读取URL的响应  
		BufferedReader reader = new BufferedReader(  
				new InputStreamReader(in));  
		String line; 
		String html = "";
		while ((line = reader.readLine()) != null) {  
			html +=  line;  
		}  
		System.out.println(html);
		reader.close();
	}
}
