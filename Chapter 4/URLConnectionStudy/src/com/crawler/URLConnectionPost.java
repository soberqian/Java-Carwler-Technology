package com.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnectionPost {

	public static void main(String[] args) throws IOException {
		//Post表单需要提交的参数
		String wen = "EH629625211CS";
		String action = "ajax";
		//初始化URL
		URL url = new URL("http://www.kd185.com/ems.php");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
		//允许Output
		conn.setDoOutput(true); 
		conn.setRequestMethod("POST"); //Post提交参数
		StringBuffer params = new StringBuffer();  
		// 表单参数拼接
		params.append("wen").append("=").append(wen).append("&")
		.append("action").append("=").append(action);
		byte[] bypes = params.toString().getBytes();
		conn.getOutputStream().write(bypes);// 在连接中添加参数
		// 定义BufferedReader输入流来读取URL的响应 ,这里设置编码 
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), "utf-8"));  
		String line; 
		String html = "";
		while ((line = bufferedReader.readLine()) != null) {  
			html +=  line;  
		}  
		System.out.println(html);
		bufferedReader.close();
	}
}
