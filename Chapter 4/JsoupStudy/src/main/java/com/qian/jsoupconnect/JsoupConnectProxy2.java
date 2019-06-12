package com.qian.jsoupconnect;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
public class JsoupConnectProxy2 {
	public static void main(String[] args) throws IOException {
		//第二种方式设置代理
		Connection connection = Jsoup.connect("http://www.w3school.com.cn/b.asp")
				.proxy("171.221.239.11",808);
		Response response = connection.method(Method.GET).timeout(10*1000).execute();
		//获取响应状态码
		int statusCode = response.statusCode();  
		System.out.println("响应状态码为:" + statusCode);  
	}
}
