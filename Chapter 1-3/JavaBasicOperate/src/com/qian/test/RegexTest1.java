package com.qian.test;

public class RegexTest1 {

	public static void main(String[] args) {
		String str1 = "a1b2c3dAZ4";
		String strReplace1 = str1.replaceAll("[abc]", "");
		System.out.println("使用元字符[abc]匹配的结果为:" + strReplace1);
		String strReplace2 = str1.replaceAll("[^abc]", "");
		System.out.println("使用元字符[^abc]匹配的结果为:" + strReplace2);
		String strReplace3 = str1.replaceAll("[a-zA-Z]", "");
		System.out.println("使用元字符[a-zA-Z]匹配的结果为:" + strReplace3);
		String strReplace4 = str1.replaceAll("[1-9]", "");
		System.out.println("使用元字符[1-9]匹配的结果为:" + strReplace4);
		String strReplace5 = str1.replaceAll("[1-3]", "");
		System.out.println("使用元字符[1-3]匹配的结果为:" + strReplace5);
		String strReplace6 = str1.replaceAll("[a-d1-3]", "");
		System.out.println("使用元字符[a-d1-3]匹配的结果为:" + strReplace6);
	}
}
