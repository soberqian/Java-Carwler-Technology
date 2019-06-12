package com.qian.encoded;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
public class UrlEncoded {
	public static void main(String[] args) {
		String keyword = "金融";  //需要编码的关键词
		try {
			//输入编码方式gbk或utf-8
			String keywordEncoded = URLEncoder.encode(keyword, "gbk");
			System.out.println(keywordEncoded);  //输出结果
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
