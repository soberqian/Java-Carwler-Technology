package com.qian.jsoupparse;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElementTest {

	public static void main(String[] args) throws IOException {
		//获取URL对应的Document
		Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get();
		//基于id获取元素
		Element element_id = doc.getElementById("course");
		//基于标签名称获取元素集合
		Elements element_tag = doc.getElementById("course").getElementsByTag("a");
		//基于属性获取元素集合
		Elements element_A = doc.getElementById("course").getElementsByAttribute("href");
		//通过类名获取元素集合
		Elements elements = doc.getElementsByClass("browserscripting"); 
		//基于属性前缀获取元素集合
		Elements element_As = doc.getElementsByAttributeStarting("hre"); 
		//基于属性与属性值获取元素
		Elements element_Av = doc.getElementsByAttributeValue("id","tools"); 
		//获取兄弟元素集合
		Elements element_Se = doc.getElementById("navfirst").siblingElements(); 
		//获取下一个兄弟元素
		Element element_Ns = doc.getElementById("navfirst").nextElementSibling();
		//获取上一个兄弟元素
		Element element_Ps = doc.getElementById("navfirst").previousElementSibling();
	}

}
