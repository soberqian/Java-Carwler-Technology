package com.qian.jsoupconnect;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
public class JsoupConnectBodySize1 {
	public static void main(String[] args) throws IOException {
		String url = "https://www-us.apache.org/dist//httpd/httpd-2.4.37.tar.gz";
		//超时时间设置长一些，下载大文件
		Response response = Jsoup.connect(url).timeout(10*60*1000)
				.maxBodySize(Integer.MAX_VALUE)
				.method(Method.GET).ignoreContentType(true).execute();
		//如果响应成功，则执行下面的操作
		if (response.statusCode() ==200) {
			//响应转化成输出流
			BufferedInputStream bufferedInputStream = response.bodyStream();
			//保存图片
			saveFile(bufferedInputStream,"image/httpd-2.4.37.tar.gz");
		}
	}
	/**
     * 保存文件
     * @param  输入流
     * @param  保存的文件目录
	 * @throws IOException
     */
	static void saveFile(BufferedInputStream inputStream, String savePath) throws IOException  {
		
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
