package com.crawler.picture;

import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;
import com.google.common.io.Files;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;
public class PictureCrawler extends WebCrawler {
	//设置过滤规则
	private final static Pattern FILTERS =  Pattern
			.compile(".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" + 
					"|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	/*
	 * 匹配图片规则
	 * jpg/jpeg/png格式
	 */
	private static final Pattern imgPatterns = Pattern
			.compile(".*(\\.(bmp|gif|jpe?g|png|tiff?))$");

	private  static  File storageFolder; // 爬取的图片本地存储地址
	/**
	 * 配置本地存储文件
	 * @param storageFolderName
	 */
	public static void configure(String storageFolderName) {
		storageFolder = new File(storageFolderName); //实例化
		if (!storageFolder.exists()) { // 假如文件不存在
			storageFolder.mkdirs(); // 创建一个
		}
	}

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		if (FILTERS.matcher(href).matches()) {
			return false;
		}

		if (imgPatterns.matcher(href).matches()) {
			return true;
		}
		if (href.startsWith("https://pixabay.com/zh/photos")) {
			return true;
		}
		return false;
	}

	/**
	 * 处理URL,存储图片
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL(); // 获取url
		//满足条件输出图片
		if (imgPatterns.matcher(url).matches() && 
				page.getParseData() instanceof BinaryParseData &&
				page.getContentData().length > (5 * 1024)) {
			// 获取图片后缀
			String extension = url.substring(url.lastIndexOf('.'));
			// 通过uuid拼接成唯一图片名称
			String hashedName = UUID.randomUUID() + extension; 
			// 存储图片
			String filename = storageFolder.getAbsolutePath() + "/" + hashedName;
			try {
				// 把爬取到的文件存储到指定文件
				Files.write(page.getContentData(), new File(filename)); 
				System.out.println("stored url:"+url);
			} catch (IOException iox) {
				iox.printStackTrace();
			}
		}
	}
}