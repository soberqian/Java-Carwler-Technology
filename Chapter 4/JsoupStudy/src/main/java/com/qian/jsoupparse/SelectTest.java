package com.qian.jsoupparse;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SelectTest {

	public static void main(String[] args) throws IOException {
		//获取URL对应的HTML内容
		Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get();
		//[attr=value]: 利用属性值来查找元素,例如[id=course]; 通过tagname: 通过标签查找元素，比如：a
		System.out.println(doc.select("[id=course]").select("a").get(0).text());
		//fb[[attr=value]:利用标签属性联合查找
		System.out.println(doc.select("div[id=course]").select("a").get(0).text());
		//#id: 通过ID查找元素,例如，#course
		System.out.println(doc.select("#course").select("a").get(0).text());
		//通过属性属性查找元素，比如：[href]
		System.out.println(doc.select("#course").select("[href]").get(0).text());
		//.class通过class名称查找元素
		System.out.println(doc.select(".browserscripting").text());
		//[attr^=value], [attr$=value], [attr*=value]利用匹配属性值开头、结尾或包含属性值来查找元素(很常用的方法)
		System.out.println(doc.select("#course").select("[href$=index.asp]").text());
		//[attr~=regex]: 利用属性值匹配正则表达式来查找元素,*指匹配所有元素
		System.out.println(doc.select("#course").select("[href~=/*]").text());
		
	}

}
