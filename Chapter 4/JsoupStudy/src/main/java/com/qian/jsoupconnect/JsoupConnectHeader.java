package com.qian.jsoupconnect;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class JsoupConnectHeader {
	public static void main(String[] args) throws IOException {
		Connection connect = Jsoup.connect("http://www.w3school.com.cn/b.asp");
		//设置单个请求头
		Connection conheader = connect.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
		Document document = conheader.get();
		System.out.println(document);
	}
}
