package com.main;

import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import java.util.regex.Pattern;
import com.db.MYSQLControl;
import com.parse.Parse;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;
public class SinaAutoNewsCrawler extends WebCrawler {
	//设置正则规则
	private final static Pattern URLPattern = Pattern
			.compile(".*(\\.shtml|html)$");
	//数据封装
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		return URLPattern.matcher(href).matches()&&(
				href.startsWith("http://tj.auto.sina.com.cn/")||
				href.startsWith("http://sh.auto.sina.com.cn/")||
				href.startsWith("http://hz.auto.sina.com.cn/")||
				href.startsWith("http://nb.auto.sina.com.cn/"));
	}
	/**
	 * 处理URL对应的页面
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL(); // 获取url
		if (URLPattern.matcher(url).matches() && page.getParseData() instanceof HtmlParseData) {
			MYSQLControl.executeInsert(Parse.getData(page));   
		}
	}
}