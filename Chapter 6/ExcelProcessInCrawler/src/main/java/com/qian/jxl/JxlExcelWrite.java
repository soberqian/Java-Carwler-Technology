package com.qian.jxl;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class JxlExcelWrite {

	public static void main(String[] args) throws IOException, RowsExceededException, WriteException {
		File xlsFile = new File("data/a.xls");
		// 创建一个工作簿
		WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
		// 创建一个工作表
		WritableSheet sheet = workbook.createSheet("sheet1", 0);
		//添加表头
		sheet.addCell(new Label(0, 0, "post_id"));
		sheet.addCell(new Label(1, 0, "post_title"));
		//添加内容
		for(int i = 0; i < 2; i++){
			sheet.addCell(new Label(0, i+1, "0" + i));
			sheet.addCell(new Label(1, i+1, "内容" + i));
		}
		//执行写入操作
		workbook.write();
		//关闭资源，释放内存
		workbook.close();
	}

}
