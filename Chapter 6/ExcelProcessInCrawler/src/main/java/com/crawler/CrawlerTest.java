package com.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CrawlerTest {

	public static void main(String[] args) 
			throws IOException, RowsExceededException, WriteException {
		//待爬URL列表
		List<String> urlList = new ArrayList<String>();
		urlList.add("http://baa.bitauto.com/cs55/index-all-all-1-0.html");
		urlList.add("http://baa.bitauto.com/cs55/index-all-all-2-0.html");
		urlList.add("http://baa.bitauto.com/cs55/index-all-all-3-0.html");
		List<PostModel> datalist = new ArrayList<PostModel>();
		//获取数据
		for (int i = 0; i < urlList.size(); i++) {
			datalist.addAll(crawerData("http://baa.bitauto.com/CS55/"));
		}
		//存储数据
		DataToExcelByJxl.writeInfoListToExcel("data/post.xls","sheet1",datalist);
		DataToExcelByPoi.writeInfoListToExcel("data/post1.xlsx","sheet1",datalist);
	}
	//解析数据
	static List<PostModel> crawerData(String url) throws IOException{
		//所爬数据封装于集合中
		List<PostModel> datalist = new ArrayList<PostModel>();
		//获取URL对应的HTML内容
		Document doc = Jsoup.connect(url).timeout(30000).get();
		//定位需要采集的每个帖子
		Elements elements = doc.select("div[class=line-bg]")
				.select("div[class=postslist_xh]"); 
		//遍历每一个帖子
		for (Element ele : elements) {
			String post_id = ele.select("li.bt").select("a").attr("href")
					.split("-")[1].replaceAll("\\D", "");
			String post_title = ele.select("li.bt").select("a").text();
			//创建对象和封装数据
			PostModel model = new PostModel();
			model.setPost_id(post_id);
			model.setPost_title(post_title);
			datalist.add(model);
		}
		return datalist;
	}

}
