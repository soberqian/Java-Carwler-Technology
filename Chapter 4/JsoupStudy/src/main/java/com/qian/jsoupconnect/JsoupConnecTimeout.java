package com.qian.jsoupconnect;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
public class JsoupConnecTimeout {
	public static void main(String[] args) throws IOException {
		//基于timeout设置超时时间
		Response response = Jsoup.connect("https://twitter.com/")
				.method(Method.GET).timeout(3*1000).execute();
		Document document = Jsoup.connect("https://twitter.com/").timeout(10*1000)
				.header("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0")
		        .get();
	}
}
