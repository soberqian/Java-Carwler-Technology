package com.bytestream.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputStreamAndFileOutputStreamTest {

	public static void main(String[] args) throws IOException {
		//FileInputStream inputStream = new FileInputStream("data/1.txt");
		FileInputStream inputStream = new FileInputStream(new File("data/1.txt"));
		FileOutputStream outputStream = new FileOutputStream("data/out.txt");
//		FileOutputStream outputStream = new FileOutputStream(new File("data/out.txt"));
		int temp;
		while ((temp = inputStream.read()) != -1) {
			System.out.print((char)temp);
			outputStream.write(temp);
		}
		outputStream.close();
		inputStream.close();
	}

}
