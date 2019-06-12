package com.crawler.picture;

import java.net.URLEncoder;
import java.util.HashSet;
import org.apache.http.message.BasicHeader;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
public class PictureCrawlerController {
	public static void main(String[] args) throws Exception {
		//爬虫状态存储文件夹
		String crawlStorageFolder = "F:/program_work/java_work/CSDNCourse"
				+ "/Crawler4j/data/craw/root";
		String storageFolder = "F:/picture/";
		int numberOfCrawlers = 5;  //线程数
		CrawlConfig config = new CrawlConfig();
		config.setMaxDepthOfCrawling(3);   //只采集第三层页面
		config.setFollowRedirects(false);  //是否允许重定向
		config.setCrawlStorageFolder(crawlStorageFolder);
		//设置头信息
		HashSet<BasicHeader> collections = new HashSet<BasicHeader>();
		collections.add(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; "
				+ "Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) "
				+ "Chrome/63.0.3239.108 Safari/537.36"));
		collections.add(new BasicHeader("Accept","text/html,application/xhtml+xml,"
				+ "application/xml;"
				+ "q=0.9,image/webp,image/apng,*/*;q=0.8"));
		collections.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
		collections.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.9"));
		collections.add(new BasicHeader("Connection", "keep-alive"));
		config.setDefaultHeaders(collections);
		config.setPolitenessDelay(2000); //礼貌采集
		//是否运行采集二进制文件
		config.setIncludeBinaryContentInCrawling(true); 
		config.setSocketTimeout(10000); //超时设置
		config.setConnectionTimeout(10000); //超时设置
		// 配置信息
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); //robots协议
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config,
				pageFetcher, robotstxtServer);
		//添加种子URL
		controller.addSeed("https://pixabay.com/zh/images/search/" 
				+ URLEncoder.encode("深林", "utf-8") + "/");
		controller.addSeed("https://pixabay.com/zh/images/search/" 
				+ URLEncoder.encode("汽车", "utf-8") + "/");
		controller.addSeed("https://pixabay.com/zh/images/search/" 
				+ URLEncoder.encode("动物", "utf-8") + "/");
		controller.addSeed("https://pixabay.com/zh/images/search/" 
				+ URLEncoder.encode("文字", "utf-8") + "/");
		PictureCrawler.configure(storageFolder); // 配置存储位置
		//运行网络爬虫
		controller.start(PictureCrawler.class, numberOfCrawlers);
	}
}