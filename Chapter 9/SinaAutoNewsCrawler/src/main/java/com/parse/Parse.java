package com.parse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.model.SinaAutoNewsModel;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
public class Parse {
	  /**
     * 解析新闻HTML数据
     * 
     * @param Page
     * @return SinaAutoNews
     */
	public static SinaAutoNewsModel getData (Page page) {
		HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
		String html = htmlParseData.getHtml();
//		System.out.println(html);
		//使用Jsoup解析汽车新闻
		Document doc = Jsoup.parse(html);
		//新闻id
		int docid = page.getWebURL().getDocid();
		//新闻url
		String url = page.getWebURL().getURL();
		//新闻标题
//		System.out.println(url);
		String title = doc.select("div[class=main]")
				.select("h1").text();
		//新闻时间
		String time = doc.select("div[class=main]")
				.select("span[class=pub_date]").text();
		//新闻内容
		String content = doc.select("div[class=main]").text();
		//封装数据
		SinaAutoNewsModel model = new SinaAutoNewsModel();
		model.setDocid(docid);
		model.setUrl(url);
		model.setTitle(title);
		model.setTime(time);
		model.setContent(content);
		return model;
	}
  
}
