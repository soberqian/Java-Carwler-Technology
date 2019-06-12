package com.qian.jsoupconnect;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
public class JsoupConnectInputstream {
	public static void main(String[] args) throws IOException {
		String imageUrl = "http://i-4.yxdown.com/2018/6/11/KDE5Mngp/ae0c2d4d-04fb-4066-872c-a8c7a7c4ea4f.jpg";
		Connection connect = Jsoup.connect(imageUrl);
		Response response = connect.method(Method.GET).ignoreContentType(true).execute();  
		System.out.println("文件类型为:" + response.contentType());
		//如果响应成功，则执行下面的操作
		if (response.statusCode() ==200) {
			//响应转化成输出流
			BufferedInputStream bufferedInputStream = response.bodyStream();
			//保存图片
			saveImage(bufferedInputStream,"image/1.jpg");
		}
	}
	/**
     * 保存图片操作
     * @param  输入流
     * @param  保存的文件目录
	 * @throws IOException
     */
	static void saveImage(BufferedInputStream inputStream, String savePath) throws IOException  {
		
		byte[] buffer = new byte[1024];
		int len = 0;
		//创建缓冲流
		FileOutputStream fileOutStream = new FileOutputStream(new File(savePath));
		BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOutStream);
		//图片写入
		while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
			bufferedOut.write(buffer, 0, len);
		}
		//缓冲流释放与关闭
		bufferedOut.flush();
		bufferedOut.close();
	}
}
