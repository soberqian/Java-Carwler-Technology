package com.main;


import java.util.List;
import com.db.MYSQLControl;
import com.model.SinaNewsModel;
import com.parse.Parse;
import com.util.HttpRequestUtil;
public class CrawlerMain {
	static HttpRequestUtil httpRequest = new HttpRequestUtil();
	public static void main(String[] args) throws Exception {
		//调用HttpRequest中的方法获取网页内容
		for(int i = 0; i < 10; i++){
			int page = i;  //爬取的页数
			//拼接url,实现翻页操作
			String  everypageurl = "https://feed.mix.sina.com.cn/api/roll/get?"
					+ "pageid=153&lid=2509&k=&num=50&page=" + page ;
			//调用HttpRequest中的方法获取网页内容
			String html = httpRequest.getContentByHttpGetMethod(everypageurl,"utf-8");
			//针对每页的HTML,调用HtmlParse类中的方法进行解析
			List<SinaNewsModel> datalist = Parse.getData(html);
			//针对已获取的数据，调用MYSQLControl中的方法插入数据
			MYSQLControl.executeInsert(datalist);
		}
	}
}
