package com.qian.jsoupconnect;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class JsoupConnectHeaderList {
	public static void main(String[] args) throws IOException {
		Connection connect = Jsoup.connect("http://www.w3school.com.cn/b.asp");
		//实例化静态类
		Builder builder = new Builder();
		//请求网页不同添加host,也可以不设置
		builder.host = "www.w3school.com.cn";
		//builder中的信息添加到Map集合中
		Map<String, String> header = new HashMap<String, String>();
		header.put("Host", builder.host);
		header.put("User-Agent", 
				builder.userAgentList.get(new Random().nextInt(builder.userAgentSize)) );
		header.put("Accept", builder.accept);
		header.put("Referer", builder.refererList.get(new Random().nextInt(builder.refererSize)));
		header.put("Accept-Language", builder.acceptLanguage);
		header.put("Accept-Encoding", builder.acceptEncoding);
		//设置头
		Connection conheader = connect.headers(header);
		Document document = conheader.get();  //发送GET请求
		System.out.println(document);  //输出HTML
	}
	/**
	 * 封装请求头信息的静态类
	 */
	static class Builder{
		//设置userAgent库;读者根据需求添加更多userAgent
		String[] userAgentStrs = {"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
				"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
				"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0",
				"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; InfoPath.3; rv:11.0) like Gecko",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)",
		"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)"};
		List<String> userAgentList = Arrays.asList(userAgentStrs);
		int userAgentSize = userAgentList.size();
		//设置referer库;读者根据需求添加更多referer
		String[] refererStrs = {"https://www.baidu.com/",
				"https://www.sogou.com/",
				"http://www.bing.com",
		"https://www.so.com/"};
		List<String> refererList = Arrays.asList(refererStrs);
		int refererSize = refererList.size();
		//设置Accept、Accept-Language以及Accept-Encoding
		String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
		String acceptLanguage = "zh-cn,zh;q=0.5";
		String acceptEncoding = "gzip, deflate";
		String host;
	}
}
