package com.httpclient.thread;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		//添加连接参数
		ConnectionConfig connectionConfig = ConnectionConfig.custom()
				.setMalformedInputAction(CodingErrorAction.IGNORE)
				.setUnmappableInputAction(CodingErrorAction.IGNORE)
				.setCharset(Consts.UTF_8)
				.build();
		//添加socket参数
		SocketConfig socketConfig = SocketConfig.custom()
				.setTcpNoDelay(true)
				.build();
		//配置连接池管理器
		PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager();
		// 设置最大连接数
		pcm.setMaxTotal(100);
		// 设置每个连接的路由数
		pcm.setDefaultMaxPerRoute(10);
		//设置连接信息
		pcm.setDefaultConnectionConfig(connectionConfig);
		//设置socket信息
		pcm.setDefaultSocketConfig(socketConfig);
		//设置全局请求配置,包括Cookie规范,HTTP认证,超时
		RequestConfig defaultConfig = RequestConfig.custom()
				.setCookieSpec(CookieSpecs.STANDARD_STRICT)
				.setExpectContinueEnabled(true)
				.setTargetPreferredAuthSchemes(Arrays
						.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
				.setConnectionRequestTimeout(30*1000)
				.setConnectTimeout(30*1000)
				.setSocketTimeout(30*1000)
				.build();
		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(pcm)
				.setDefaultRequestConfig(defaultConfig)
				.build();
		// 请求的URL
		String[] urlArr = {
				"http://www.w3school.com.cn/html/index.asp",
				"http://www.w3school.com.cn/html/html_basic.asp",
				"http://www.w3school.com.cn/html/html_elements.asp",
				"http://www.w3school.com.cn/html/html_attributes.asp",
				"http://www.w3school.com.cn/html/html_formatting.asp"
		};
		//创建固定大小的线程池
		ExecutorService exec = Executors.newFixedThreadPool(3);
		for(int i = 0; i< urlArr.length;i++){
			String filename = urlArr[i].split("html/")[1]; //HTML需要输出的文件名
			//创建HTML文件输出目录
			OutputStream out = new FileOutputStream("file/" + filename);
			HttpGet httpget = new HttpGet(urlArr[i]);
			//启动线程执行请求
			exec.execute(new DownHtmlFileThread(httpClient, httpget, out));
		}
		//关闭线程
		exec.shutdown();
	}
	static class DownHtmlFileThread extends Thread {
		private final CloseableHttpClient httpClient;
		private final HttpContext context;
		private final HttpGet httpget;
		private final OutputStream out;
		//输入的参数
		public DownHtmlFileThread(CloseableHttpClient httpClient, 
				HttpGet httpget, OutputStream out) {
			this.httpClient = httpClient;
			this.context = HttpClientContext.create();
			this.httpget = httpget;
			this.out = out;
		}
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + 
					"线程请求的URL为:" + httpget.getURI());
			try {
				CloseableHttpResponse response = httpClient.execute(
						httpget, context);  //执行请求
				try {
					//HTML文件写入文档
					out.write(EntityUtils.toString(response.getEntity(),"gbk")
							.getBytes());
					out.close();
					//消耗实体
					EntityUtils.consume(response.getEntity());
				} finally{
					response.close(); //关闭响应
				}
			} catch (ClientProtocolException ex) {
				ex.printStackTrace(); // 处理 protocol错误
			} catch (IOException ex) {
				ex.printStackTrace(); // 处理I/O错误
			}
		}
	}
}
