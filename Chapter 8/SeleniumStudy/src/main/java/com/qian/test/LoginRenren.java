package com.qian.test;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginRenren {

	public static void main(String[] args) throws IOException, InterruptedException {
		//geckodriver配置
		System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		//声明使用的是火狐浏览器
		FirefoxDriver driver = new FirefoxDriver(); 
		//使用火狐浏览器打开人人网
		driver.get("http://sns.renren.com/");
		//元素定位，提交用户名以及密码
		driver.findElementByName("email").clear();  //清空后输入
		driver.findElementByName("email").sendKeys("*******");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElementById("password").clear(); //清空后输入
		driver.findElementById("password").sendKeys("*******");
		//元素定位，点击登陆按钮
		driver.findElementById("login").click();
		Thread.sleep(10*1000);  //休息一段时间,使得网页充分加载。注意这里非常有必要
		Set<Cookie> cookies = driver.manage().getCookies();
		//获取登陆的cookies
		String cookieStr = "";
		for (Cookie cookie : cookies) {
			cookieStr += cookie.getName() + "=" + cookie.getValue() + "; ";
		}
		System.out.println(cookieStr);
		//基于Jsoup,使用cookies请求个人信息页面
		Response orderResp = Jsoup   //添加一些header信息
				.connect("http://www.renren.com/427727657/profile?v=info_timeline")
				.header("Host", "www.renren.com")
				.header("Connection", "keep-alive")
				.header("Cache-Control", "max-age=0")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*;q=0.8")
				.header("Origin", "http://www.renren.com")
				.header("Referer", "http://www.renren.com/SysHome.do")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("Accept-Encoding", "gzip, deflate, br")
				.header("Upgrade-Insecure-Requests", "1")
				.cookie("Cookie", cookieStr)
				.execute();
		//解析数据
		Document doc = orderResp.parse();
//		System.out.println(doc);
		org.jsoup.select.Elements elements = doc.select("div[class=info-section-info]")
				.select("dl[class=info]");
		for (Element element : elements) {
			if (element.select("dt").text().contains("大学")) {
				System.out.println(element.text());
			}
		}
		driver.quit();  // 关闭浏览器
	}
}
