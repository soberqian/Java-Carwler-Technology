package com.file;

import java.io.File;
public class FileTest {
	public static void main(String[] args) {
		File root = new File("data/");
		//判断文件是否问一个目录
		Boolean is_directory = root.isDirectory();
		System.out.println("文件是否为目录:" + is_directory);
		//判断文件是否存在
		System.out.println("文件是否存在:" + root.exists());
		//判断文件是否是一个普通文件
		System.out.println("文件是否为普通文件:" + root.isFile());
		//判断是否为绝对路径
		System.out.println("文件是否为绝对路径:" + root.isAbsolute());
		//如果是一个目录
		if (is_directory) {
			//获取目录下所有文件和目录的绝对路径，得到的是File数组
			File[] files = root.listFiles(); 
			//循环数组
			for ( File file : files ){
				System.out.println("============");
				System.out.println("文件名称为:" + file.getName());
				System.out.println("文件可读否:" + file.canRead());
				System.out.println("文件的父目录为:" + file.getParent());
				System.out.println("绝对路径:" + file.getAbsolutePath());
				System.out.println("相对路径:" + file.getPath());
			    System.out.println("文件的长度为:" + file.length());
			    System.out.println("============");
			}
		}
	}
}
