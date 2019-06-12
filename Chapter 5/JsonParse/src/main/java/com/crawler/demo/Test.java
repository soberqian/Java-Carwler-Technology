package com.crawler.demo;

public class Test {

	public static void main(String[] args) {
		//拼接JSON串
		String json = "jQuery18305886476962892728_1531402823026({\"id\":\"07\","
				+ "\"language\": \"C++\",\"edition\": \"second\","
				+ "\"author\": \"E.Balagurusamy\"})";
		//掐头去尾操作
		String arr = json.split("\\(")[1];
		System.out.println(arr.substring(0,arr.length() - 1));

	}

}
