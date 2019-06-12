package com.qian.jsoupconnect;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupConnectURL1 {

	public static void main(String[] args) throws IOException {
		//创建连接
		Connection connect = Jsoup.connect("http://www.w3school.com.cn/b.asp");
		//请求网页
		Document document = connect.get();
		//输出HTML
		System.out.println(document.html());
	}

}
