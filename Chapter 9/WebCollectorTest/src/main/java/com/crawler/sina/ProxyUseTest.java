package com.crawler.sina;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.Proxies;
import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import okhttp3.OkHttpClient;
import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据WebCollector作者的案例改编
 *
 */

public class ProxyUseTest extends BreadthCrawler {
	/**
	 * 自定义的请求插件
	 * 添加多个代理
	 * 使用代理选择器，实现随机代理切换
	 */
	public static class MyRequester extends OkHttpRequester {
		Proxies proxies;
		public MyRequester() {
			proxies = new Proxies();
			proxies.addSocksProxy("127.0.0.1", 1080); //本机
			proxies.addSocksProxy("111.177.181.229", 9999);
			proxies.addSocksProxy("61.131.146.250", 9999);
			// 直接连接,不使用代理
			proxies.add(null);
		}
		@Override
		public OkHttpClient.Builder createOkHttpClientBuilder() {
			return super.createOkHttpClientBuilder()
					// 设置一个代理选择器
					.proxySelector(new ProxySelector() {
						@Override
						public List<Proxy> select(URI uri) {
							//随机选择1个代理
							Proxy randomProxy = proxies.randomProxy();
							//返回值类型需要是List
							List<Proxy> randomProxies = new ArrayList<Proxy>();
							//如果随机到null，即不需要代理，返回空的List即可
							if(randomProxy != null) {
								randomProxies.add(randomProxy);
							}
							System.out.println("使用的代理为:" + randomProxies);
							return randomProxies;
						}
						@Override
						public void connectFailed(URI uri, SocketAddress sa, 
								IOException ioe) {

						}
					});
		}
	}
	public ProxyUseTest(String crawlPath) {
		super(crawlPath, true);
		// 设置请求插件
		setRequester(new MyRequester());
		// 采集新浪新闻
		this.addSeed("https://news.sina.com.cn/");
		this.addRegex("^(https://news.sina.com.cn/c/).*(\\.shtml)$");

	}

	public void visit(Page page, CrawlDatums crawlDatums) {
		if (page.matchUrl("^(https://news.sina.com.cn/c/).*(\\.shtml)$")){
			String title = page.select("h1[class=main-title]").text();
			System.out.println("标题为:" + title);
		}

	}

	public static void main(String[] args) throws Exception {
		ProxyUseTest crawler = new ProxyUseTest("crawl_proxy");
		//设置线程数目
		crawler.setThreads(3);
		//防止有些代理不可用,下次启动可以使用其他代理继续请求
		crawler.setResumable(true);
		//设置每一层最多采集的页面数
		crawler.getConf().setTopN(100);
		crawler.start(3);
	}
}