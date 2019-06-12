package com.crawler;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
public class HttpclientTest {
	public static void main(String[] args) throws ParseException, IOException  {
		//初始化HttpContext
		HttpContext localContext = new BasicHttpContext();
		String url = "http://www.w3school.com.cn/b.asp";
		//初始化httpclient
		HttpClient httpClient = HttpClients.custom().build(); 
		HttpGet httpGet = new HttpGet(url);
		//执行请求获取HttpResponse
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet,localContext);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//获取具体响应信息
		System.out.println("response:" + httpResponse );
		String status = httpResponse .getStatusLine().toString();    //响应状态
		System.out.println("status:" + status);   
		int StatusCode = httpResponse .getStatusLine().getStatusCode(); //获取响应状态码
		System.out.println("StatusCode:" + StatusCode);
		ProtocolVersion protocolVersion = httpResponse .getProtocolVersion(); //协议的版本号
		System.out.println("protocolVersion:" + protocolVersion);
		String phrase = httpResponse .getStatusLine().getReasonPhrase(); //是否ok
		System.out.println("phrase:" + phrase);
		Header[] headers = httpResponse.getAllHeaders();
		System.out.println("输出头信息为：");
		for (int i = 0; i < headers.length; i++) {
			System.out.println(headers[i]);
		}
		System.out.println("头信息输出结束");
		if(StatusCode == HttpStatus.SC_OK){                          //状态码200表示响应成功
			//获取实体内容
			HttpEntity entity = httpResponse.getEntity();
			String entityString = EntityUtils.toString (entity,"gbk"); //注意设置编码
			//输出实体内容
			System.out.println(entityString);
			EntityUtils.consume(httpResponse.getEntity());       //消耗实体
		}else {
			//关闭HttpEntity的流实体
			EntityUtils.consume(httpResponse.getEntity());        //消耗实体
		}
	}
}

