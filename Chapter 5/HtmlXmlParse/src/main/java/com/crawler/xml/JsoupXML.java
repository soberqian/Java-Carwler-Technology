package com.crawler.xml;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class JsoupXML {
	public static void main(String[] args) throws IOException {
		//获取URL对应的HTML内容
		String url = "http://db.auto.sohu.com/cxdata/xml/sales/model/model1001sales.xml";
		Document doc = Jsoup.connect(url).timeout(5000).get();
		//Jsoup选择器解析
		Elements sales_ele = doc.select("sales");
		for (Element elem:sales_ele) {
			int salesnum=Integer.valueOf(elem.attr("salesnum"));
        	String date = elem.attr("date");
        	System.out.println("月份:" + date + "\t销量:" + salesnum);
        	
		}
	}
}
