package com.qian.jsoupconnect;

import java.io.IOException;
import java.net.URL;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class JsoupConnectURL2 {
	public static void main(String[] args) throws IOException {
		//获取响应
		Response response = Jsoup.connect("http://www.w3school.com.cn/b.asp")
				.method(Method.GET).execute();
		URL url = response.url();   //查看请求的URL
		System.out.println("请求的URL为:" + url);
		int statusCode = response.statusCode();  //获取响应状态码
		System.out.println("响应状态码为:" + statusCode);  
		String contentType = response.contentType();
		System.out.println("响应类型为:" + contentType);  //获取响应数据类型
		String statusMessage = response.statusMessage();  //响应信息 200-OK
		System.out.println("响应信息为:" + statusMessage);
		//判断响应状态码是否为200
		if (statusCode == 200) {
			String html = new String(response.bodyAsBytes(),"gbk");  //通过这种方式可以获得响应的HTML文件
			Document document = response.parse();   //获取html内容,但对应的是Document类型
			System.out.println(html);  //这里html和document数据是一样的，但document是经过格式化的
		}
	}
}
