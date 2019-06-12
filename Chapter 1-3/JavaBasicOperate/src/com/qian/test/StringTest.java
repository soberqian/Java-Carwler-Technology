package com.qian.test;

public class StringTest {
	public static void main(String[] args) {
		String url = " https://www.baidu.com/ ";
		String urlTrim = url.trim(); //去除空格字符
		System.out.println(urlTrim + "\t" + urlTrim.length());   //获取字符串长度
		System.out.println("toUpperCase:" + urlTrim.toUpperCase()); //转化成大写
		boolean bEqual = urlTrim.equals("www"); //判断字符串是否相同
		boolean bContain = urlTrim.contains("www"); //判断是否包含
		System.out.println("bEqual:" + bEqual + "\t" + "bContain:" + bContain);
		String urlConcat = urlTrim.toLowerCase().concat("crawler");  //也可采用+的形式
		System.out.println("urlConcat:" + urlConcat);
		String urlSubstring = urlTrim.substring(2, urlTrim.length()); //从第二个字符串截取到最后
		System.out.println("urlSubstring:" + urlSubstring);
		int urlIndexOf = urlTrim.indexOf("t"); //寻找某字符的位置
		System.out.println("urlIndexOf:" + urlIndexOf);
		boolean urlStartsWith = urlTrim.startsWith("https"); //是否以某字符为前缀
		boolean urlEndsWith = urlTrim.endsWith("com/"); //是否以某字符为后缀
		System.out.println("urlStartsWith:" + urlStartsWith + "\t" + "urlEndsWith:" + urlEndsWith);
	}
}
