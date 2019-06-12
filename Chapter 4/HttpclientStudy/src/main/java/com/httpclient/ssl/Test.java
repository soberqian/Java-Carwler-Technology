package com.httpclient.ssl;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
public class Test {
	public static void main(String[] args) throws ParseException, IOException {
		String url = "https://www.creditchina.gov.cn/xinyongfuwu/?navPage=5";
		SSLClient sslClient = new SSLClient();   //实例化
		HttpClient httpClientSSL = sslClient.initSSLClient("TLS");
		HttpGet httpGet = new HttpGet(url);
		//获取结果
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClientSSL.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(httpResponse .getStatusLine().getStatusCode() == HttpStatus.SC_OK){ //状态码200表示响应成功
			//获取实体内容
			String entity = EntityUtils.toString (httpResponse.getEntity(),"UTF-8");
			//输出实体内容
			System.out.println(entity);
			EntityUtils.consume(httpResponse.getEntity());       //消耗实体
		}else {
			//关闭HttpEntity的流实体
			EntityUtils.consume(httpResponse.getEntity());        //消耗实体
		}
	}
}
