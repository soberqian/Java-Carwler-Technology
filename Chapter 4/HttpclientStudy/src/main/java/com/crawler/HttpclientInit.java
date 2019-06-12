package com.crawler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

public class HttpclientInit {
	public static void main(String[] args) throws  ClientProtocolException, IOException, URISyntaxException  {
		/*HttpClient实例化方法
		*HttpClients.custom()返回值HttpClientBuilder.create()
		*HttpClients.createDefault()返回值 HttpClients.custom().build()
		*具体可阅读HttpClients类
		**/
		HttpClient httpClient1 = new DefaultHttpClient();  
		HttpClient httpClient2 = HttpClients.custom().build(); 
		HttpClient httpClient3 = HttpClientBuilder.create().build();
		CloseableHttpClient httpClient4 = HttpClients.createDefault(); 
		HttpClient httpClient5 = HttpClients.createSystem();
		HttpClient httpClient6 = HttpClients.createMinimal();
		URI uri = new URIBuilder("http://www.w3school.com.cn/b.asp").build();  //创建URI
		HttpGet getMethod = new HttpGet();  //  get方法请求
		getMethod.setURI(uri);  //设置
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
				HttpStatus.SC_OK, "OK");                        //初始化HTTP响应        
		response = httpClient6.execute(getMethod);                   //执行响应
		System.out.println("response:" + response);
		String status = response.getStatusLine().toString();    //响应状态
		System.out.println("status:" + status);   
		int StatusCode = response.getStatusLine().getStatusCode(); //获取响应状态码
		System.out.println("StatusCode:" + StatusCode);
		ProtocolVersion protocolVersion = response.getProtocolVersion(); //协议的版本号
		System.out.println("protocolVersion" + protocolVersion);
		String phrase = response.getStatusLine().getReasonPhrase(); //是否ok
		System.out.println("phrase:" + phrase);
		System.out.println(response);
		if(StatusCode == 200){                          //状态码200表示响应成功
			//获取实体内容
			String entity = EntityUtils.toString (response.getEntity(),"gbk");
			//输出实体内容
			System.out.println(entity);
			EntityUtils.consume(response.getEntity());       //消耗实体
		}else {
			//关闭HttpEntity的流实体
			EntityUtils.consume(response.getEntity());        //消耗实体
		}
	}
}

