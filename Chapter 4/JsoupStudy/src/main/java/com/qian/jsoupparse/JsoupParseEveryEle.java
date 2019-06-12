package com.qian.jsoupparse;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class JsoupParseEveryEle {
	public static void main(String[] args) throws IOException {
		//获取URL对应的Document
		Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp")
				.timeout(5000).get();
		//层层定位到要解析的内容，可以发现包含多个li元素
		Elements elements = doc.select("div#course").select("li"); 
		//遍历每一个li节点
		for (Element ele : elements) {
			//.text()为解析标签中的文本内容
			String title = ele.select("a").text(); 
			//.attr(String)表示获取标签内某一属性的内容
			String course_url = ele.select("a").attr("href");  
			System.out.println("标题为:" + title + "\tURL为:" + course_url);
		}
	}
}
