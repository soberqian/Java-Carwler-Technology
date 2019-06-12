package com.qian.jxl;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
public class JxlExcelRead2 {
	public static void main(String[] args) throws IOException, RowsExceededException, WriteException, BiffException {
        // 声明工作簿
        Workbook book  = Workbook.getWorkbook(new File("data/a.xls"));
        //获取名称为sheet1的表格，也可使用getSheet(0)获取第一个工作表
        Sheet sheet = book.getSheet("sheet1");
        //获取工作表中的总行数及总列数
        int rows = sheet.getRows();
        int columns = sheet.getColumns();
        //行列循环获取数据
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
            	//使用getCell方法读取数据，第一个是指定第几列，第二个参数才是指定第几行
                Cell cell= sheet.getCell(j,i);
                System.out.print(cell.getContents() + "\t");
            }
            System.out.println();
        }
	}
}
