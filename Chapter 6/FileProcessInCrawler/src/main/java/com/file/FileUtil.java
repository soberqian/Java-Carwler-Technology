package com.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileUtil {

	public static void main(String[] args) throws IOException {
		System.out.println(getTotalLines("data/crawlerbitauto.txt"));
		System.out.println(readContentByFile("data/crawlerbitauto.txt",1));
	}
	/**
	 * 获取文件总行数
	 * @param  fileName  文件名
	 * @return  count  总行数
	 * @throws IOException
	 */
	static int getTotalLines(String fileName) throws IOException {   
		BufferedReader in = new BufferedReader(new InputStreamReader(   
				new FileInputStream(fileName)));   
		int count = 0; 
		//如果行内容不为空,则行数加1
		while ((in.readLine()) != null) {   
			count++;   
		}   
		in.close();   
		return count;   
	}   
	/**
	 * 读取指定行的文本内容
	 * @param  fileName  文件名
	 * @param  lineNumber  行数
	 * @return  content  该行的文本内容
	 * @throws IOException
	 */
	static String readContentByFile(String fileName, int lineNumber) throws IOException {   
		BufferedReader reader = new BufferedReader(new InputStreamReader(   
				new FileInputStream(fileName)));   
		String line = reader.readLine();   
		if (lineNumber < 0 || lineNumber > getTotalLines(fileName)) {   
			System.err.println("行数不在正在范围内！");   
		}   
		int num = 0; 
		String content = "";
		while (line != null) {   
			if (lineNumber == ++num) {  
				content = line;
			}   
			line = reader.readLine();   
		}   
		reader.close();  
		return content;
	} 
	/**
	 * 读取文本数据保存到集合中
	 * @param  fileName  文件名
	 * @param  code  文件编码，如gbk等
	 * @return  lines  需要返回的集合内容
	 * @throws IOException
	 */
	static ArrayList<String> readToList(String fileName, String code) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader reader = new BufferedReader( 
				new InputStreamReader( 
						new FileInputStream( new File(fileName)),code));
		String line = null;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		reader.close();
		return lines;
	}
	/**
	 * 集合数据写入指定文档
	 * @param  fileName  文件名
	 * @param  code  文件编码，如gbk等
	 * @param  contents  不确定类型的集合数据
	 * @param  append  是否在原文件后追加内容
	 * @throws IOException
	 */
	public static void writeListToFile(String file, String code, boolean append, ArrayList<?> contents) 
			throws IOException {
		BufferedWriter writer = new BufferedWriter( 
				new OutputStreamWriter( 
						new FileOutputStream( new File(file),append),code));
		for (int i = 0; i < contents.size(); i++) {
			writer.write(contents.get(i) + "\n");
		}
		writer.close();
	}
}
