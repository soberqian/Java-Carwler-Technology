package com.qian.jsoupconnect;

import java.io.IOException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupConnectSSLInit {
	public static void main(String[] args) throws IOException {
		initUnSecureTSL();
		String url = "https://www.creditchina.gov.cn/xinyongfuwu/?navPage=5";
		//创建连接
		Connection connect = Jsoup.connect(url);
		//请求网页
		Document document = connect.get();
		//输出HTML
		System.out.println(document.html());
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
