package com.qian.jsoupparse;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class JsoupParseStaticFile {
	public static void main(String[] args) throws IOException {
		//HTML静态文件
		String html = "<html><body><div id=\"w3school\"> <h1>浏览器脚本教程</h1> <p><strong>从左侧的菜单选择你需要的教程！</strong></p> </div>"
				+ "<div>  <div id=\"course\"> <ul> <li><a href=\"/js/index.asp\" title=\"JavaScript 教程\">JavaScript</a></li> </ul> </div> </body></html>";
		//转化成Document
		Document doc = Jsoup.parse(html); 
		//基于CSS选择器获取元素,也可写成[id=w3school]
		Element element = doc.select("div[id=w3school]").get(0);
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
