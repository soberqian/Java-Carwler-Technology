package com.qian.jsoupconnect;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupConnectParameter2 {

	public static void main(String[] args) throws IOException {
		Connection connect = Jsoup.connect("http://www.kd185.com/ems.php");
		//需要提交的参数
		Map<String, String> data = new HashMap<String, String>();  
	    data.put("wen", "EH629625211CS");  
	    data.put("action", "ajax");  
	    //获取响应
	    Response response = connect.data(data).method(Method.GET).ignoreContentType(true).execute();  
	    //获取数据,处理成HTML
	    Document document = response.parse();
	    System.out.println(document);
	}

}
