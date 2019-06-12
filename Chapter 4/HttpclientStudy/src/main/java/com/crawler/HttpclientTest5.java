package com.crawler;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
@SuppressWarnings("deprecation")
public class HttpclientTest5 {
	public static void main(String[] args) throws IOException, URISyntaxException {
		@SuppressWarnings("resource")
		HttpClient client = new DefaultHttpClient();   //初始化httpclient
		//第一种方式
		String personalUrl = "http://www.w3school.com.cn/b.asp";
		URI uri = new URIBuilder(personalUrl).build();  //创建URI
		HttpGet getMethod = new HttpGet();  //  get方法请求
		getMethod.setURI(uri);  //设置
		System.out.println("Method:" + getMethod);
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
				HttpStatus.SC_OK, "OK");                        //初始化HTTP响应        
		response = client.execute(getMethod);                   //执行响应
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
		
		HttpHead HttpHead = new org.apache.http.client.methods.HttpHead("");
		HttpPut HttpPut = new HttpPut("");
	}
}

