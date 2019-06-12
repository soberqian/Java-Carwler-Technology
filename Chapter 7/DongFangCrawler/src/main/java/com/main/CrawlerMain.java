package com.main;

import java.util.List;
import com.db.MYSQLControl;
import com.model.CarStockModel;
import com.parse.Parse;
import com.util.HttpRequestUtil;
public class CrawlerMain {
	static HttpRequestUtil httpRequest = new HttpRequestUtil();
	public static void main(String[] args) throws Exception {
		for (int i = 1; i < 9; i++) {
			//拼接url
			String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication"
					+ "/JS.aspx?type=CT&token=4f1862fc3b5e77c150a2b985b12db0fd&sty="
					+ "FCOIATC&js=(%7Bdata%3A%5B(x)%5D%2CrecordsFiltered%3A(tot)%7D)&cmd="
					+ "C.BK04811&st=(ChangePercent)&sr=-1&p=" + i + "&ps=20&_=1551750725008";
			//请求拼接后的url
			String content = httpRequest.getContentByHttpGetMethod(url,"utf-8");
			//解析url
			List<CarStockModel> carstocks = Parse.getData(content);
			//存储数据
			MYSQLControl.insertCarStocks(carstocks);
		}
	}
}
