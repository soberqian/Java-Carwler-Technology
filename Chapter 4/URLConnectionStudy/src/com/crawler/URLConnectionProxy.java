package com.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionProxy {
	public static void main(String[] args) throws IOException {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("171.97.67.160", 3128));  //设置代理
		URL url = new URL("http://www.w3school.com.cn/b.asp");  
		URLConnection conn = url.openConnection(proxy);  //以代理的方式建立连接
		conn.connect(); //建立实体连接
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), "gbk"));  // 定义BufferedReader输入流来读取URL的响应 ,这里设置编码 
		String line; 
		String html = "";
		while ((line = bufferedReader.readLine()) != null) {  
			html +=  line;  
		}  
		System.out.println(html);
	}
}

