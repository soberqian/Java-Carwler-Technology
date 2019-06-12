package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class PoiExcelRead {
	public static void main(String[] args) throws IOException  {
		//文件名称
		File file = new File("data/c.xls");
//		File file = new File("data/c.xlsx");
		//根据文件名称获取操作工作簿
	    Workbook workbook = getWorkBook(file);
	    //获取读取的工作表，这里有两种方式
	    Sheet sheet = workbook.getSheet("Sheet1");
//	    Sheet sheet=workbook.getSheetAt(0);
	    int allRow = sheet.getLastRowNum();//获取行数
	    //按行读取数据
	    for (int i = 0; i <= allRow; i++) {
	        Row row = sheet.getRow(i);
	        //获取列数
	        short lastCellNum = row.getLastCellNum();
	        for (int j = 0; j < lastCellNum; j++) {
	            String cellValue = row.getCell(j).getStringCellValue();
	            System.out.print(cellValue + "\t");
	        }
	        System.out.println();
	    }
	    workbook.close();
	}
	/**
	 * 判断Excel的版本,初始化不同的Workbook
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkBook(File file) throws IOException{
		//输入流
		InputStream in = new FileInputStream(file);
		Workbook workbook = null;
		//Excel 2003
		if(file.getName().endsWith("xls")){     
			workbook = new HSSFWorkbook(in);
		// Excel 2007以上版本
		}else if(file.getName().endsWith("xlsx")){    
			workbook = new XSSFWorkbook(in);
		}
		in.close();
		return workbook;
	}
}
