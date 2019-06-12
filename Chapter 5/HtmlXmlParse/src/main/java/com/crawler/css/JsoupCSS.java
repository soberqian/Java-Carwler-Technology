package com.crawler.css;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class JsoupCSS {

	public static void main(String[] args) throws IOException {
		//这里笔者使用Jsoup获取html文件
		Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get(); 
		Elements elements = doc.select("[^titl]");
		Elements elements1 = doc.select("div#navsecond+div");
		System.out.println(elements1);
		//伪选择器---查找包含指定文本的元素
		Elements elements2 = doc.select("p:contains(JavaScript)");
		System.out.println(elements2);
		Elements elements3 = doc.select("div:only-child");
		System.out.println(elements3);
		Elements elements4 = doc.select("#maincontent > div:nth-child(5)");
		System.out.println(elements4);
	}

}
