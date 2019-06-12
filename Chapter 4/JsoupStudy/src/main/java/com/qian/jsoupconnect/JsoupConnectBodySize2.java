package com.qian.jsoupconnect;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

public class JsoupConnectBodySize2 {

	public static void main(String[] args) throws IOException {
		String url = "http://poi.mapbar.com/shanghai/F10";
		Response response = Jsoup.connect(url).timeout(10*10*1000).maxBodySize(Integer.MAX_VALUE)
				.method(Method.GET).ignoreContentType(true).execute();
		System.out.println(response.parse());
	}
	
}
