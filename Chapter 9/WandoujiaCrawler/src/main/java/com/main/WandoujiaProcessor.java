package com.main;

import java.io.IOException;
import java.util.List;
import com.db.MYSQLControl;
import com.model.AppModel;
import com.parse.Parse;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.JsonPathSelector;
/**
 * HTML以及JSON操作
 */
public class WandoujiaProcessor implements PageProcessor {
	/**
	 * 网络爬虫相关配置
	 * 这里设置了重试次数,时间间隔
	 */

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	public Site getSite() {
		return site;
	}
	/**
	 * 针对每个URL对应进行判断操作
	 */
	public void process(Page page) {
		//第一页为html
		if(page.getUrl().regex("https://www.wandoujia.com/top*").match()){
			//获取app对应的URL
			List<String> urls = page.getHtml()
					.xpath("//*[@id='j-top-list']").links()
					.regex("https://www.wandoujia.com/apps/com.*").all();
			page.addTargetRequests(urls);
		} 
		//加载更多里的app对应的URL(JSON)
		if (page.getUrl().regex("https://www.wandoujia.com/wdjweb/api*").match()) {
			//解析JSON数据
			JsonPathSelector jsonPathSelector = new JsonPathSelector("$.data.content");
	        String select = jsonPathSelector.select(page.getRawText());
			List<String> urls = Html.create(select).links()
					.regex("https://www.wandoujia.com/apps/com.*").all();
			page.addTargetRequests(urls);
		}
		//app简介页面
		if (page.getUrl().regex("https://www.wandoujia.com/apps/com.*").match()) {
			AppModel model = new AppModel();
			Parse.getAppData(page, model);  //解析数据
			MYSQLControl.executeInsertAPP(model); //存储数据,每一条存储一次
		}
	}

	public static void main(String[] args) throws IOException {
		//首页
		String url = "https://www.wandoujia.com/top/app";
		Spider spider = Spider.create(new WandoujiaProcessor());
		// 添加第一个种子节点
		spider.addUrl(url);
		//添加加载更多的URL,i从2开始循环
		for (int i = 2; i < 10; i++) {
			spider.addUrl("https://www.wandoujia.com/wdjweb/"
					+ "api/top/more?resourceType=0&page=" + i);
		}
		spider.thread(5);  //开启5个线程抓取
		spider.run();  //启动爬虫
	}
}
