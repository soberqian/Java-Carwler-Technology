package com.charstream.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class IrOwTest {
	public static void main(String[] args) throws IOException {
		//读取文件的数据
		InputStream in = new FileInputStream("data/3.txt");
		//将字节流向字符流的转换
		InputStreamReader isr = new InputStreamReader(in);
		//写入新文件
		FileOutputStream fos = new FileOutputStream("data/3osw.txt"); 
		OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8"); 
		int temp;
		//读取与写入操作
		while((temp = isr.read()) != -1){
			System.out.print((char)temp);
			osw.write(temp); 
		}
		osw.write("\njava网络爬虫");
		osw.append("\n很有意思");
		//流的关闭
		osw.close();
		isr.close();
	}
}
