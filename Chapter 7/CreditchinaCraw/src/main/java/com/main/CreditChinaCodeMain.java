package com.main;

import java.io.IOException;
import java.util.List;
import com.db.MYSQLControl;
import com.model.ListInfoModel;
import com.parse.Parse;
import com.util.HttpRequestUtil;
public class CreditChinaCodeMain {
	public static HttpRequestUtil request = new HttpRequestUtil();
	public static void main(String[] args) throws IOException {
		//初始化安全协议，防止不让访问
		request.initSSLClient();
		//请求守信红名单数据，需要设置页数,这里共5页
		for (int i = 1; i < 6; i++) {
			String json = request.getHTMLContentByHttpGetMethod("https://www.creditchina.gov.cn/api"
					+ "/credit_info_search?objectType=2&page="+i+"&pageSize=10"
							+ "&creditType=2&_=1523273080466","UTF-8");
			//解析红名单数据
			List<ListInfoModel> list = Parse.getCodeData(json);
			//存储数据
			MYSQLControl.executeInsertCodeInfo(list);
		}
	}
}
