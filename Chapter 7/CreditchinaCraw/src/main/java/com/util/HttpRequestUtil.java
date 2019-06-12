package com.util;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.*;
import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HttpRequestUtil {
	private HttpClient httpClient;
	private List<Header> headerList = new ArrayList<Header>();
	public HttpEntity getEntityByHttpGetMethod(String url){
		HttpGet httpget = new HttpGet(url); //使用的请求方法
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpget);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		HttpEntity httpEntity = response.getEntity();  //获取网页内容流
		return httpEntity;
	}
	//获取url对应的网页内容
	public String getHTMLContentByHttpGetMethod(String url,String code){
		try {
			return EntityUtils.toString(getEntityByHttpGetMethod(url),code);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 基于SSL配置httpClient
	 * @param  SSLProtocolVersion(SSL, SSLv3, TLS, TLSv1, TLSv1.1, TLSv1.2)
	 * @return httpClient
	 */
	public void initSSLClient(){
		RequestConfig defaultConfig = null;
		PoolingHttpClientConnectionManager pcm = null;
		try {
			X509TrustManager xtm = new SSL509TrustManager(); //创建信任管理
			//创建SSLContext对象,，并使用指定的信任管理器初始化
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new X509TrustManager[]{xtm}, null);
			//从SSLContext对象中得到SSLConnectionSocketFactory对象
			SSLConnectionSocketFactory sslConnectionSocketFactory = new 
					SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
			//设置全局请求配置
			defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
					.setExpectContinueEnabled(true)
					.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
					.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
			//注册http套接字工厂和https套接字工厂  
			Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", sslConnectionSocketFactory).build();
			//基于str创建连接管理器
			pcm = new PoolingHttpClientConnectionManager(sfr);
		}catch(NoSuchAlgorithmException | KeyManagementException e){
			e.printStackTrace();
		}
		initDefaultHeaders(); //头信息
		//基于连接管理器和配置,实例化httpclient
		httpClient = HttpClients.custom().
				setConnectionManager(pcm).
				setRetryHandler(new DefaultHttpRequestRetryHandler()). //默认重试次数
				setDefaultHeaders(headerList).  //添加头信息
				setDefaultRequestConfig(defaultConfig)
				.build();
	}
	//头信息设置
	private List<Header> initDefaultHeaders(){
		headerList.add(new BasicHeader(HttpHeaders.ACCEPT, 
				"text/html,application/xhtml+xml,application/xml;q=0.9," +
				"image/webp,image/apng,*/*;q=0.8"));
		headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, 
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
						"AppleWebKit/537.36 (KHTML, like Gecko)"
						+ " Chrome/60.0.3112.113 Safari/537.36"));
		headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, 
				"gzip, deflate"));
		headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, 
				"max-age=0"));
		headerList.add(new BasicHeader(HttpHeaders.CONNECTION, 
				"keep-alive"));
		headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, 
				"zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4,ja;q=0.2," +
				"de;q=0.2"));
		return headerList;
	}
}
