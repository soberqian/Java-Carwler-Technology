package com.qian.test;

public class StringTypeConversion {
	public static void main(String[] args) {
		String sumPage = "30"; //例如，某论坛的帖子总页数为30
		int sumPageParse = Integer.parseInt(sumPage);
		String price = "1299.8";  //例如，某产品的价格为1299.8
		double priceParse = Double.parseDouble(price);
		System.out.println(sumPageParse + "\t" + priceParse);
	}
}
