package com.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
public class URLConnectionSSL {
	public static void main(String[] args) throws IOException {
		initUnSecureTSL();
		//使用URLConnection请求数据
		URL url = new URL("https://www.creditchina.gov.cn/xinyongfuwu/?navPage=5");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				int statusCode = conn.getResponseCode(); //获取响应状态码
		String responseBody = null;
		//如果响应状态码为200
		if (HttpURLConnection.HTTP_OK == statusCode) {  
			// 定义BufferedReader输入流来读取URL的响应 ,这里设置编码
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), "utf-8"));  
			//读取内容
			String readLine = null;
			StringBuffer response = new StringBuffer();
			while (null != (readLine = bufferedReader.readLine())) {
				response.append(readLine);
			}

			bufferedReader.close();
			responseBody = response.toString();
		}
		System.out.println(responseBody);
	}
	private static void initUnSecureTSL()  {
		// 创建信任管理器(不验证证书)
		final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
			//检查客户端证书
			public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
				//do nothing 接受任意客户端证书
			}
			//检查服务器端证书  
			public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
				//do nothing  接受任意服务端证书
			}
			//返回受信任的X509证书
			public X509Certificate[] getAcceptedIssuers() {
				return null; //或者return new X509Certificate[0];
			}
		}};
		try {
			// 创建SSLContext对象,并使用指定的信任管理器初始化
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			////基于信任管理器，创建套接字工厂 (ssl socket factory)
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			//给HttpsURLConnection配置SSLSocketFactory
			HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			//正常访问Https协议网站
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
