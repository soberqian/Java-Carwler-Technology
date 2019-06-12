package com.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class Test {

	public static void main(String[] args) throws IOException {
		List<Header> headerList = new ArrayList<Header>(); //通过集合封装头信息
//		headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"));
		headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36"));
//		headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
//		headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, "max-age=0"));
//		headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
//		headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9"));
//		headerList.add(new BasicHeader(HttpHeaders.HOST, "i.autohome.com.cn"));
		headerList.add(new BasicHeader(HttpHeaders.REFERER, "https://i.autohome.com.cn/21544198"));
		//构造自定义的HttpClient对象
		HttpClient httpClient = HttpClients.custom()
				.setDefaultHeaders(headerList).build(); 
		HttpGet httpget = new HttpGet("https://i.autohome.com.cn/ajax/home/OtherHomeAppsData?appname=Car&r=0.4603669533409208&TuserId=21544198"); //使用的请求方法
		
		//获取结果
		HttpResponse response = httpClient.execute(httpget);  //发出get请求
		//获取响应状态码
		int code = response.getStatusLine().getStatusCode();  
		HttpEntity httpEntity = response.getEntity();  //获取网页内容流
		String entity = EntityUtils.toString(httpEntity, "gbk");     //以字符串的形式(需设置编码)
		System.out.println(code + "\n" + entity); //输出所获得的的内容
		EntityUtils.consume(httpEntity);     //关闭内容流           
	}

}
