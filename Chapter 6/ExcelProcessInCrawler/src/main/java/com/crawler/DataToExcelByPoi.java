package com.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class DataToExcelByPoi {
	public static void writeInfoListToExcel(String filePath,
			String sheetName,List<PostModel> datalist) throws IOException {
		//文件名称
		File file = new File(filePath);
		OutputStream outputStream = new FileOutputStream(file);
		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.createSheet(sheetName);
		//添加表头
		Row row = sheet.createRow(0); //常见某行
		row.createCell(0).setCellValue("post_id");
		row.createCell(1).setCellValue("post_title");
		//添加内容
		for(int i = 0; i < datalist.size(); i++){
			Row everyRow = sheet.createRow(i + 1);
			everyRow.createCell(0).setCellValue(datalist.get(i).getPost_id());
			everyRow.createCell(1).setCellValue(datalist.get(i).getPost_title());
		}
		for(int i = 0; i < 2; i++){
			
		}
		workbook.write(outputStream);
		//释放资源
		workbook.close();
		outputStream.close();
		System.out.println(">>>>>>>>>数据写入完成！<<<<<<<<<<<<<");
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
