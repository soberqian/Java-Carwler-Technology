package com.crawler.sina;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import okhttp3.Request;

/**
 * 根据WebCollector作者的案例改编
 */
public class HeaderAddTest extends BreadthCrawler {
	private static StringBuilder sb = new StringBuilder();
	private static String fileName;
	private static String code;
	// 自定义请求头
	public static class MyRequester extends OkHttpRequester {
		//每次发送请求前都会执行这个方法来构建请求
		@Override
		public Request.Builder createRequestBuilder(CrawlDatum crawlDatum) {
			//使用的是OkHttp中的Request.Builder
			return super.createRequestBuilder(crawlDatum)
					.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
							+ "AppleWebKit/537.36 (KHTML, like Gecko) "
							+ "Chrome/63.0.3239.108 Safari/537.36")
					.addHeader("Accept", "text/html,application/xhtml+xml,application"
							+ "/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.addHeader("Connection", "keep-alive");
		}
	}

	public HeaderAddTest(String crawlPath, boolean autoParse,String filename,String cod) {
		super(crawlPath, autoParse);
		 setRequester(new MyRequester());
		/**
		 * 添加种子URL
		 */
		this.addSeed("https://news.sina.com.cn/");
		this.addSeed("https://news.sina.com.cn/china/");
		/**
		 * URL访问规则添加 
		 * 
		 * 以https://news.sina.com.cn/c/为前缀
		 * 以.shtml为后缀
		 * 不匹配以.(jpg|png|gif)结尾的URL
		 */
		this.addRegex("^(https://news.sina.com.cn/c/).*(\\.shtml)$");
		this.addRegex("-.*\\.(jpg|png|gif|css|js|mid|mp4|wav|avi|mov|mpeg|ram|m4v|pdf)$");
		/**
		 * 输出文件配置
		 * 
		 * 文件名以及文件编码
		 */
		fileName = filename;
		code = cod;
	}
	public void visit(Page page, CrawlDatums crawlDatums) {
		String url = page.url();
		//种子URL,不符合条件,这里过滤掉
		if (page.matchUrl("^(https://news.sina.com.cn/c/).*(\\.shtml)$")){
			/**
			 * Jsoup解析数据
			 */
			String title = page.select("h1[class=main-title]").text();
			String content = page.select("div[class=article]").text();
			sb.append("URL:\t" + url  + "\n" + "title:\t" + title
					+ "\ncontent:\t" + content + "\n\n");
		}
		try {
			writeFile(fileName, sb.toString(), code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 数据写入指定文档
	 * 
	 * @param file(文件名)
	 * @param content(需要写入的内容)
	 * @param code(文件编码)
	 */
	public static void writeFile(String file, String content, String code) 
			throws IOException {
		File result = new File(file);
		OutputStream out = new FileOutputStream(result, false);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, code));
		bw.write(content);
		bw.close();
		out.close();
	}
	public static void main(String[] args) throws Exception {
		SinaNewsCrawler crawler = new SinaNewsCrawler("sinaNewsCrawler_head", true,
				"data/sinaNews.txt","utf-8");
		//设置线程数目
		crawler.setThreads(10);
		//设置每一层最多采集的页面数
		crawler.getConf().setTopN(400);
		//开始数据采集，设置采集的深度
		crawler.start(4);
	}
}
