package com.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiExcelWriteXls {

	public static void main(String[] args) throws IOException  {
		//创建文件输出流
		File file = new File("data/b.xls");
		OutputStream outputStream = new FileOutputStream(file);
		//创建工作簿及工作表
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		//添加表头
		HSSFRow row = sheet.createRow(0); //常见某行
		row.createCell(0).setCellValue("post_id");
		row.createCell(1).setCellValue("post_title");
		//添加内容
		for(int i = 0; i < 2; i++){
			HSSFRow everyRow = sheet.createRow(i + 1);
			everyRow.createCell(0).setCellValue("帖子id为：0" + i);
			everyRow.createCell(1).setCellValue("帖子内容为：" + i);
		}
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
