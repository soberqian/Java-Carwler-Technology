package com.crawler.sina;

import java.util.HashSet;

import org.apache.http.message.BasicHeader;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class SinaNewsController {

	public static void main(String[] args) throws Exception {
		//爬虫状态存储文件夹
        String crawlStorageFolder = "F:/program_work/java_work/CSDNCourse"
        		+ "/Crawler4j/data/craw/root";
        int numberOfCrawlers = 5;  //线程数
        CrawlConfig config = new CrawlConfig();
        config.setMaxDepthOfCrawling(1);   //只采集第一层页面
        config.setMaxPagesToFetch(10);  //最多采集10个页面
        config.setFollowRedirects(false);  //是否允许重定向
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
        		+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
        HashSet<BasicHeader> collections = new HashSet<BasicHeader>();
        collections.add(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
        		+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36"));
        collections.add(new BasicHeader("Accept","text/html,application/xhtml+xml,application/xml;"
        		+ "q=0.9,image/webp,image/apng,*/*;q=0.8"));
        collections.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
        collections.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.9"));
        collections.add(new BasicHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8"));
        collections.add(new BasicHeader("Connection", "keep-alive"));
        config.setDefaultHeaders(collections);
        config.setPolitenessDelay(1000);
        config.setIncludeBinaryContentInCrawling(true);
        config.setSocketTimeout(10000);
        config.setConnectionTimeout(10000);
//        config.setProxyHost("47.52.140.92");
//        config.setProxyPort(808);
       // 配置信息
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); //robots协议
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config,
        		pageFetcher, robotstxtServer);
        //添加种子URL
        controller.addSeed("https://news.sina.com.cn/");
        //运行网络爬虫
        controller.start(SinaNewsCrawler.class, numberOfCrawlers);
    }
}