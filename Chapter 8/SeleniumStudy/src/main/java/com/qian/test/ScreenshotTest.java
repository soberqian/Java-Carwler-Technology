package com.qian.test;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
public class ScreenshotTest {

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
				driver.get("http://weixin.sogou.com/antispider/?"
						+ "from=%2fweixin%3Ftype%3d2%26query"
						+ "%3dcomputer+%26ie%3dutf8%26s_from%"
						+ "3dinput%26_sug_%3dy%26_sug_type_%3d");
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
		WebElement webEle = driver.findElement(By.id("seccodeImage"));
		// Get entire page screenshot
		java.io.File screenshot = ((TakesScreenshot)driver)
				.getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);
		Point point = webEle.getLocation();
		int eleWidth = webEle.getSize().getWidth();
		int eleHeight = webEle.getSize().getHeight();
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(),
				eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", new File("E:/钱洋个人/IdentifyingCode/test.png"));
		System.out.println("请输入验证码：");
		BufferedReader buff=new BufferedReader(new InputStreamReader(System.in));
		String captcha_solution="";
		try {
			captcha_solution = buff.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.findElement(By.name("c")).sendKeys(captcha_solution);
		driver.findElementById("submit").click();
		Thread.sleep(10*1000);  //休息一段时间,使得网页充分加载。注意这里非常有必要
		String html = driver.getPageSource();
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("div[class=txt-box]");
		for (Element ele : elements) {
			String newsTitle = ele.select("h3").select("a").text();
			String newsUrl = ele.select("h3").select("a").attr("href");
			System.out.println(newsTitle + "\t" + newsUrl);
		}
		driver.quit();  // 关闭浏览器
	}
}
