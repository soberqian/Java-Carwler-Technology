package com.crawler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawPicture {
	private static  HttpClient httpClient = HttpClients.custom().build();
	public static void main(String[] args) throws IOException{
		String url = "http://pic.yxdown.com/list/2_0_4.html";
		HttpEntity entity = getEntityByHttpGetMethod(url);
		//获取所有图片链接
		String html = EntityUtils.toString(entity);
		Elements elements = Jsoup.parse(html).select("div.cbmiddle > a.proimg > img");
		for (Element ele : elements) {
			String pictureUrl = ele.attr("src");
			saveImage(pictureUrl,"image/" + pictureUrl.split("/")[7] );
		}
		//测试程序
		saveImage1("http://i-4.yxdown.com/2018/6/11/KDE5Mngp/ae0c2d4d-04fb-4066-872c-a8c7a7c4ea4f.jpg","image/1.jpg");
	}
	//请求某一个URL,获得请求到的内容
	public static HttpEntity getEntityByHttpGetMethod(String url){
		HttpGet httpGet = new HttpGet(url);
		//获取结果
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity = httpResponse.getEntity();
		return entity;
	}
	//任意输入地址便可以下载图片
	static void saveImage(String url, String savePath) throws IOException{
		//图片下载保存地址
		File file=new File(savePath);
		//如果文件存在则删除
		if(file.exists()){
			file.delete();
		}
		//缓冲流
		BufferedOutputStream bw = new BufferedOutputStream(
				new FileOutputStream(savePath)); 
		//请求图片数据
		try {
			HttpEntity entity = getEntityByHttpGetMethod(url);
			//以字节的方式写入
			byte[] byt= EntityUtils.toByteArray(entity); 
			bw.write(byt);
			System.out.println("图片下载成功！");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//关闭缓冲流
		bw.close();
	}

	//另外，一种操作方式
	static void saveImage1(String url, String savePath) 
			throws UnsupportedOperationException, IOException {
		//获取图片信息,作为输入流
		InputStream in = getEntityByHttpGetMethod(url).getContent();
		//每次最多读取1KB的内容
		byte[] buffer = new byte[1024];
		BufferedInputStream bufferedIn = new BufferedInputStream(in);
		int len = 0;
		//创建缓冲流
		FileOutputStream fileOutStream = new FileOutputStream(new File(savePath));
		BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOutStream);
		//图片写入
		while ((len = bufferedIn.read(buffer, 0, 1024)) != -1) {
			bufferedOut.write(buffer, 0, len);
		}
		//缓冲流释放与关闭
		bufferedOut.flush();
		bufferedOut.close();
	}
}
