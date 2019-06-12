package com.crawler.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
public class HFUTNewsProcessor implements PageProcessor {
	/**
	 * 网络爬虫相关配置
	 * 这里设置了重试次数,时间间隔
	 */

	private Site site = Site.me().setTimeOut(3000);
	public Site getSite() {
		return site;
	}

	/**
	 * 针对每个URL对应的页面进行操作
	 */
	public void process(Page page) {
		//如果不是以http://news.hfut.edu.cn/show为前缀的URL,进行下列操作
		if(!page.getUrl().regex("http://news.hfut.edu.cn/show.*").match()){
			//获取新闻URL
			List<String> urls = page.getHtml()
					.xpath("//ul[@class='content list pushlist lh30']").links().all();
			//如果存在分页的情况,将每页URL添加到待采集的列表中
			List<String> url2 = page.getHtml()
					.xpath("//*[@id='pages']//a[13]").links().all();
			urls.addAll(url2);
			page.addTargetRequests(urls);
		}else {  //使用Xpath语法解析数据
			String url = page.getUrl().toString();
			String title = page.getHtml().xpath("//*[@id='Article']/h2/text()").get();
			String content = page.getHtml().xpath("//*[@id='artibody']/allText()").get();
			String time = page.getHtml().xpath("//*[@id='Article']/h2/span/allText()")
					.get();
			//存储结果
			page.putField("url",url);
			page.putField("title",title);
			page.putField("content",content);
			page.putField("time", time);
		}
	}

	public static void main(String[] args) throws IOException {
		String url = "http://news.hfut.edu.cn/index.php?m=content&c=index&a=lists&catid=1";
		// 合工大新闻网首页作为种子节点
		BufferedWriter writer = new BufferedWriter( new OutputStreamWriter
				( new FileOutputStream( 
						new File("outputfile/hfutnews.txt")),"utf-8"));
		Spider.create(new HFUTNewsProcessor())
		.addUrl(url).setScheduler(new QueueScheduler()
				.setDuplicateRemover(new BloomFilterDuplicateRemover(1000)))
		.addPipeline(new JsonFilePipeline("outputfile/")) //按照Json的形式存储
		.addPipeline(new HFUTNewsOutPutFilePipeline(writer)) //存储到一个文件中
		.thread(5)  //开启5个线程抓取
		.run();  //启动爬虫
	}
}
