package com.qian.test;

public class RegexTest {

	public static void main(String[] args) {
		String url = "//i.autohome.com.cn/75975500";   //使用Jsoup解析得到的url片段
		String user_id = url.replaceAll("\\D", "");  //取代所有的非数字字符
		System.out.println(user_id);  //输出的结果即为75975500
		String content = "正则表达式-CSDN博客";
		String[] contentRegex = content.split("\\p{Punct}"); //以标点符号进行切割
		System.out.println(contentRegex[0]); //输出结果为"正则表达式"
		
	}
}
