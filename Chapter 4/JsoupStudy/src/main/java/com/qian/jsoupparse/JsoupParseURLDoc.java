package com.qian.jsoupparse;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class JsoupParseURLDoc {
	public static void main(String[] args) throws IOException {
		//获取URL对应的Document
		Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get();
		//基于CSS选择器获取元素,这里换了一种方式
		Element element = doc.select("div#w3school").get(0); 
		System.out.println("输出解析的元素内容为:");
		System.out.println(element);
		//从Element提取内容(抽取一个Node对应的信息)
		String text1 = element.select("h1").text(); 
		//从Element提取内容(抽取一个Node对应的信息)
		String text2 = element.select("p").text(); 
		System.out.println("抽取的文本信息为:");
		System.out.println(text1 + "\t" + text2);
	}
}
