package com.qian.test;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
public class SinaNewsRolling {

	public static void main(String[] args) throws IOException, InterruptedException {
		//geckodriver配置
		System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		//声明使用的是火狐浏览器
		FirefoxDriver driver = new FirefoxDriver(); 
		//使用火狐浏览器打开任意某一新闻页
		driver.get("http://k.sina.com.cn/article_6436034945_17f9e198100100krct.html?from=home");
		// 执行JS操作
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		try {
			JS.executeScript("scrollTo(0, 5000)");
			System.out.println("1");
			Thread.sleep(5000);             //调整休眠时间可以获取更多的内容
			JS.executeScript("scrollTo(5000, 10000)");
			System.out.println("2");
			Thread.sleep(5000);
			JS.executeScript("scrollTo(10000, 30000)"); // 继续下拉
			System.out.println("3");
			Thread.sleep(5000);
			JS.executeScript("scrollTo(10000, 50000)"); //继续下拉
			System.out.println("4");
		} catch (Exception e) {
			System.out.println("Error at loading the page ...");
			driver.quit();
		}
		String html = driver.getPageSource();
//		System.out.println(html);
		//解析数据
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("[id=tycard_list]")
				.select("div[class=ty-card ty-card-type1 clearfix]");
		for (Element ele : elements) {
			String newsTitle = ele.select("h3[class=ty-card-tt]").select("a").text();
			String newsUrl = ele.select("h3[class=ty-card-tt]").select("a").attr("href");
			System.out.println(newsTitle + "\t" + newsUrl);
		}
		driver.quit();  // 关闭浏览器
	}
}
