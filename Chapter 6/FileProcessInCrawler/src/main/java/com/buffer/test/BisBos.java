package com.buffer.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class BisBos {
	public static void main(String[] args) throws IOException {
		//创建File对象
		File file = new File("data/1.txt");
		//创建BufferedInputStream对象
		BufferedInputStream bin = new BufferedInputStream(
				new FileInputStream(file), 512);
		//创建BufferedOutputStream对象
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream("data/1bos.txt")); 
		byte[] b=new byte[5];  //代表一次最多读取5个字节的内容
		int length = 0; //代表实际读取的字节数
		while( (length = bin.read( b ) )!= -1 ){
			System.out.println(new String(b));
			//写入指定文件
			bos.write(b, 0, length);
			bos.write("\n".getBytes()); //写上换行符
		}
		//关闭流
		bos.close();
		bin.close();
	}
}
