package com.crawler.hfutnews;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.ram.RamCrawler;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.jsoup.nodes.Element;
/**
 *    
 * 这里使用RamCrawler
 * RamCrawler不需要依赖文件系统或数据库，适合一次性的爬取任务
 * 也可将该程序改写为BreadthCrawler爬虫
 */
public class HFUTNewsCrawler extends RamCrawler {
	String fileFirstLayerOutPut = "data/hfut_newsUrl.txt";
	String contentOutPut = "data/hfut_newsContent.txt";
	String code = "utf-8";
	StringBuilder sb_first = new StringBuilder();
	StringBuilder sb_content = new StringBuilder();
	public HFUTNewsCrawler(int pageNum) throws Exception {
		//添加多页
		for (int pageIndex = 1; pageIndex <= pageNum; pageIndex++) {
			String url = "http://news.hfut.edu.cn/list-1-" + pageIndex + ".html";
			CrawlDatum datum = new CrawlDatum(url)
					.type("firstLayer") //第一层
					.meta("pageIndex", pageIndex) //页面保存
					.meta("depth", 1); //深度为第一层
			this.addSeed(datum);
		}
	}
	public void visit(Page page, CrawlDatums next) {

		int pageIndex = page.metaAsInt("pageIndex");
		int depth = page.metaAsInt("depth");
		if (page.matchType("firstLayer")) {
			//解析新闻标题页
			Elements results = page.select("div.col-lg-8 > ul").select("li");
			for (int rank = 0; rank < results.size(); rank++) {
				Element result = results.get(rank);
				String href = "http://news.hfut.edu.cn" + 
						result.select("a").attr("href");
				String title = result.select("a").text();
				String time = result.select("span[class=rt]").text();
				if (title.length() != 0) {
					//输出第一层信息
					sb_first.append("url:" + href + "\ttitle:" + title + 
							"\ttime:" + time + "\n");
					try {
						writeFile(fileFirstLayerOutPut, sb_first.toString(), code);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*
					 * 添加需要访问的新闻连接,类型为content
					 * 
					 * 用于爬取新闻的详细内容
					 */
					next.addAndReturn(href)  //将该URL添加到CrawlDatum作为要采集的URL
					.type("content")  //内容页面
					.meta("pageIndex", pageIndex)  //第几页的新闻
					.meta("rank", rank); //这条新闻的序号
				}
			}
		} 
		/*
		 * 页面的深度加1
		 * 
		 * 新闻的详情页
		 */
		next.meta("depth", depth + 1);
		//新闻详情页
		if (page.matchType("content")) {
			//输出结果
			String url = page.url();
			int Index = page.metaAsInt("pageIndex"); //新闻在第几页
			int rank = page.metaAsInt("rank"); //新闻在页面的序号
			String content = page.select("div[id=artibody]").text(); //新闻内容
			//输出第二层信息
			sb_content.append("url:" + url + "\tIndex:" + Index + "\trank:" + rank + 
					"\tcontent:" + content + "\n");
			try {
				writeFile(contentOutPut, sb_content.toString(), code);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		//添加爬取的页面数
		HFUTNewsCrawler crawler = new HFUTNewsCrawler(3);
		//添加线程数
		crawler.setThreads(10);
		//启动采集程序
		crawler.start();
	}
}
