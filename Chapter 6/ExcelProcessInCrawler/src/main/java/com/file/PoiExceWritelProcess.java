package com.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiExceWritelProcess {

	public static void main(String[] args) throws IOException {
		//文件名称
		File file = new File("data/c.xls");
		//File file = new File("data/c.xlsx");
		OutputStream outputStream = new FileOutputStream(file);
		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.createSheet("Sheet1");
		//添加表头
		Row row = sheet.createRow(0); //常见某行
		row.createCell(0).setCellValue("post_id");
		row.createCell(1).setCellValue("post_title");
		//添加内容
		for(int i = 0; i < 2; i++){
			Row everyRow = sheet.createRow(i + 1);
			everyRow.createCell(0).setCellValue("帖子id为：0" + i);
			everyRow.createCell(1).setCellValue("帖子内容为：" + i);
		}
		workbook.write(outputStream);
		//释放资源
		workbook.close();
		outputStream.close();
	}
	
	/**
	 * 判断Excel的版本,初始化不同的Workbook
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkBook(File file) throws IOException{
		Workbook workbook = null;
		//Excel 2003
		if(file.getName().endsWith("xls")){     
			workbook = new HSSFWorkbook();
		// Excel 2007以上版本
		}else if(file.getName().endsWith("xlsx")){    
			workbook = new XSSFWorkbook();
		}
		return workbook;
	}
}
