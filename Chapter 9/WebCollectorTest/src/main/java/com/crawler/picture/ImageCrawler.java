package com.crawler.picture;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.util.ExceptionUtils;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;
import cn.edu.hfut.dmic.webcollector.util.MD5Utils;
import java.io.File;
import java.io.UnsupportedEncodingException;
/**
 * 采集关键词相关的图片
 * 采集的网站为:https://picjumbo.com
 * 
 */
public class ImageCrawler extends BreadthCrawler {
	//文件保存的目录
	File baseDir = new File("images");
	public ImageCrawler(String crawlPath,String keyWord, int pageNum) throws UnsupportedEncodingException {
		super(crawlPath, true);
		//添加种子URL
		for (int i = 1; i <= pageNum; i++) {
			String url = "https://picjumbo.com/page/" + i + "/?s=macbook";
			this.addSeed(url);
		}
		//限定爬取范围
		this.addRegex("^(https://picjumbo.com/wp-content/uploads).*(\\.jpg)$");
	}
	public void visit(Page page, CrawlDatums next) {
		String contentType = page.contentType();
		//根据http头中的Content-Type信息来判断当前资源是网页还是图片
		if(contentType!=null && contentType.startsWith("image")){
			//从Content-Type中获取图片扩展名
			String extensionName = contentType.split("/")[1];
			try {
				byte[] image = page.content();
				//根据图片MD5生成文件名
				String fileName = String.format("%s.%s",
						MD5Utils.md5(image), extensionName);
				File imageFile = new File(baseDir, fileName);
				FileUtils.write(imageFile, image);
			} catch (Exception e) {
				ExceptionUtils.fail(e);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ImageCrawler imageCrawler = new ImageCrawler("crawl_image","macbook",4);
		//设置为断点爬取，否则每次开启爬虫都会重新爬取
//		imageCrawler.setResumable(true);
		//设置自动解析图片链接
		imageCrawler.getConf().setAutoDetectImg(true);
		/*使用默认的Requester,需要设置一下网页大小上限
		 * 否则可能会获得一个不完整的页面
		 * 如接收页面大小上限设置为10M
		 */
		imageCrawler.getConf().setMaxReceiveSize(1024 * 1024 * 10);
		imageCrawler.setThreads(30);
		imageCrawler.start(4);
	}
}
