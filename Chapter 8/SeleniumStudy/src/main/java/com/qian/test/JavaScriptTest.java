package com.qian.test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class JavaScriptTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		firefoxBinary.addCommandLineOptions("--headless");
		//设置路径
		System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setBinary(firefoxBinary);
		FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
		//直到加载该网页为止
		while (true){
			try{
				driver.get("http://k.sina.com.cn/article_"
						+ "6436034945_17f9e198100100krct.html?from=home");
			}
			catch (Exception e)
			{
				driver.quit();
				driver = new FirefoxDriver(firefoxOptions);
				driver.manage().timeouts()
					.pageLoadTimeout(10, TimeUnit.SECONDS);
				continue;
			}
			break;
		}
		//滚动条操作
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		// 执行JS操作,返回新闻的标题
		String title = (String)JS.executeScript("return document.title"); 
		System.out.println(title);
		driver.quit();  // 关闭浏览器
	}
}
