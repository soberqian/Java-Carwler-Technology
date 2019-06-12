package com.crawler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
public class HttpclientDownloadFile {
	public static void main(String[] args) throws IOException {
		String url = "https://www-us.apache.org/dist//httpd/httpd-2.4.37.tar.gz";
		HttpClient httpClient = HttpClients.custom().build(); //初始化httpclient
		HttpGet httpGet = new HttpGet(url);
		//获取结果
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//非常简单的下载文件的方法
		OutputStream out = new FileOutputStream("file/httpd-2.4.37.tar.gz");
		httpResponse.getEntity().writeTo(out);
		EntityUtils.consume(httpResponse.getEntity()); //消耗实体
	}

}
