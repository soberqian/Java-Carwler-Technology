package com.main;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.db.MYSQLControl;
import com.model.ParameterModel;
import com.model.ProductModel;
import com.parse.Parse;
/**
 * WebCollector数据入库案例
 * 采集的网站为http://top.zol.com.cn/compositor/57/manu_attention.html
 * 
 */
public class ZolCrawler extends BreadthCrawler {
	public static List<ProductModel> listPro = new ArrayList<ProductModel>();
	public static List<ParameterModel> listPar = new ArrayList<ParameterModel>();
	public ZolCrawler(String crawlPath) {
		super(crawlPath, true);
		String url = "http://top.zol.com.cn/compositor/57/manu_attention.html";
		CrawlDatum datum = new CrawlDatum(url)
				.type("firstLayer"); //第一层
		this.addSeed(datum);
	}
	public void visit(Page page, CrawlDatums next) {
		//第一层次的页面
		if (page.matchType("firstLayer")) {
			//解析第一层页面
			Elements results = page.select("div[class=rank-list brand-rank-list]")
					.select("div[class=rank-list__item clearfix]");
			for (int rank = 0; rank < results.size(); rank++) {
				Element result = results.get(rank);
				String url_next = result.select("div.brand_logo")
						.select("p > a").attr("href");
				//添加第二层次需要访问的URL
				next.addAndReturn(url_next).type("SecondLayer");  
			}
		}
		if (page.matchType("SecondLayer")) {
			//解析第二层次页面,并添加第三层次的页面URL
			Parse.getProductData(page, listPro, next);
		}
		if (page.matchType("ThirdLayer")) {
			//解析第三层次页面
			Parse.getParData(page, listPar);
		}
	}

	public static void main(String[] args) throws Exception {
		ZolCrawler crawler = new ZolCrawler("crawl_zol");
//		crawler.setResumable(true);
		//执行间隔
		crawler.getConf().setExecuteInterval(1000);
		crawler.getConf().setConnectTimeout(10000); //连接超时
		crawler.getConf().setReadTimeout(10000);//读取数据超时
		crawler.setThreads(70);  //访问的线程数目
		crawler.start(5);  //执行的深度
		/**
		 * 注意:如果数据量较大,可以在visit()方法中每一条数据执行一次操作,以保证线程安全
		 * 如果在visit()方法中直接执行集合插入操作(List or Vector线程并不安全),然后清空
		 * 会导致数据插入不全
		 * 
		 * 针对少量的数据,可以在这里执行一次插入操作
		 */
		MYSQLControl.executeInsertPro(listPro);
		MYSQLControl.executeInsertPar(listPar);
	}
}
