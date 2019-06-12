package com.buffer.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
public class BufferedTest {
	public static void main(String[] args) throws IOException {
		/****** 文件读取第一种方式  ******/
		File file = new File("data/3.txt");
		//FileReader读取文件
		FileReader fileReader = new FileReader(file);
 		//根据FileReader创建缓冲流
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String s = null;
		//按行读取
		while ((s = bufferedReader.readLine())!=null) {
			System.out.println(s);
		}
		//流关闭
		bufferedReader.close();
		fileReader.close();
		/****** 文件读取第二种方式  ******/
		//这里简写了，已成了一行。可以添加字符编码
		BufferedReader reader = new BufferedReader( new InputStreamReader( 
				new FileInputStream( 
						new File( "data/3.txt")),"utf-8"));
		String s1=null;
		while ((s1 = reader.readLine())!=null) {
			System.out.println(s1);
		}
		//流关闭
		reader.close();
		/****** 文件写入第一种方式  ******/
		/*File file1 = new File("data/bufferedout.txt","gbk");
		FileOutputStream fileOutputStream = new FileOutputStream(file1);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
		BufferedWriter bufferedWriter1 = new BufferedWriter(outputStreamWriter);*/
		/****** 文件写入快捷方式******/
		BufferedWriter writer = new BufferedWriter( new OutputStreamWriter
				( new FileOutputStream( 
						new File("data/bufferedout.txt")),"gbk"));
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(0, "http://pic.yxdown.com/list/2_0_2.html");
		map.put(1, "http://pic.yxdown.com/list/2_0_3.html");
		map.put(2, "http://pic.yxdown.com/list/2_0_4.html");
		//map遍历数据 
		for( Integer key : map.keySet() ){
			writer.append("key:"+key+"\tvalue:"+map.get(key));
			writer.newLine(); //写入换行操作
		}
		//流关闭
		writer.close();
		
	}
}
