package com.file;

import java.io.File;
import java.net.URISyntaxException;

public class FileCreateTest {

	public static void main(String[] args) throws URISyntaxException {
		//创建File对象
		File file1 = new File("data","1.txt");
		System.out.println(file1.exists());  //判断文件是否存在
		//创建File对象
		File file2 = new File(new File("data"),"1.txt");
		System.out.println(file2.exists());
		//创建File对象
		File file3 = new File("data/1.txt");
		System.out.println(file3.exists());
	}
}
