package com.crawler.image;

import java.io.IOException;
import java.util.List;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
public class ImageProcessor implements PageProcessor {
	/**
	 * 网络爬虫相关配置
	 * 这里设置了重试次数,时间间隔
	 */

	private Site site = Site.me().setTimeOut(3000);
	public Site getSite() {
		return site;
	}
	public void process(Page page) {
		//获取图片对应的URL
		List<String> url = page.getHtml().$("div[id=content]")
				.xpath("//div/a/img/@src").all();
		//下载每一张图片
		for (int i = 0; i < url.size(); i++) {
			String inputUrl = "https://www.socwall.com" + url.get(i);
			ImageDownloaderUtil.downLoadImage(inputUrl,
					"image/" + inputUrl.split("/")[5] );
		}
	}
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis(); 
		Spider spider = Spider.create(new ImageProcessor());
		//通过URL拼接的方式,采集多页
		for (int i = 1; i < 3; i++) {
			spider.addUrl("https://www.socwall.com/wallpapers/page:" + i + "/");
		}
		spider.thread(5);  //开启5个线程抓取
		spider.run();  //启动爬虫
		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间为: " + (endTime - startTime) + "ms");
	}
}
