package com.qian.jsoupconnect;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class JsoupConnectHeaderMap {
	public static void main(String[] args) throws IOException {
		Connection connect = Jsoup.connect("http://www.w3school.com.cn/b.asp");
		//设置多个请求头。头信息保存到Map集合中
		Map<String, String> header = new HashMap<String, String>();
		header.put("Host", "www.w3school.com.cn");
		header.put("User-Agent", " Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
		header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		header.put("Accept-Language", "zh-cn,zh;q=0.5");
		header.put("Accept-Encoding", "gzip, deflate");
		header.put("Cache-Control", "max-age=0");
		header.put("Connection", "keep-alive");
		Connection conheader = connect.headers(header);
		Document document = conheader.get();
		System.out.println(document);
	}
}
