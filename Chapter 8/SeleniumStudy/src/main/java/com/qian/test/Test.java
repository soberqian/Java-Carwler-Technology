package com.qian.test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
public class Test {

	public static void main(String[] args) {
		//geckodriver配置
		System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		//声明使用的是火狐浏览器
		FirefoxDriver driver = new FirefoxDriver(); 
		//使用火狐浏览器打开百度
		driver.get("http://www.baidu.com");
		//元素定位
		driver.findElement(By.id("kw")).sendKeys("Java 网络爬虫");
		driver.findElementById("kw").sendKeys("Java 网络爬虫");
		driver.findElementById("su").click();
		//给出一定的响应时间
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.getPageSource();
		//使用xpath解析数据
		List<WebElement> titleList = 
				driver.findElements(By.xpath("//*[@class='result c-container ']/h3/a")); 
		//输出新闻标题
		for(WebElement e : titleList){
			System.out.println("标题为：" + e.getText() + "\t" + "url为:" 
					+ e.getAttribute("href"));
		}
		driver.quit();  // 关闭浏览器
	}

}
