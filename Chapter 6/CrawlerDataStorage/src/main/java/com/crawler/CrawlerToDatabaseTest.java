package com.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.mysql.QueryRunnerTest;
import model.CarSaleModel;

public class CrawlerToDatabaseTest {
	public static void main(String[] args) throws IOException {
		//获取URL对应的XML内容
		Document doc = Jsoup.connect("http://db.auto.sohu.com/cxdata/xml/sales/model/model1001sales.xml")
				.timeout(5000).get();
		//数据存储到集合中
		List<CarSaleModel> datalist = new ArrayList<CarSaleModel>();
		//Jsoup选择器解析
		Elements sales_ele = doc.select("sales");
		for (Element elem:sales_ele) {
			String salesnum=elem.attr("salesnum");
			String date = elem.attr("date");
			//封装对象
			CarSaleModel model = new CarSaleModel();
			model.setMonth(date);
			model.setSales(salesnum);
			//添加到集合中
			datalist.add(model);
			System.out.println("月份:" + date + "\t销量:" + salesnum);
		}
		//将所爬数据插入数据库
		QueryRunnerTest.insertData(datalist);
	}
}
