package com.main;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.alibaba.fastjson.JSON;
import com.db.MYSQLControl;
import com.model.DetailInfoModel;
import com.model.ListInfoModel;
import com.parse.Parse;
import com.util.HttpRequestUtil;
public class CreditChinaDetailInfoMain {
	public static HttpRequestUtil request = new HttpRequestUtil();
	public static void main(String[] args) throws InterruptedException {
		//初始化安全协议，防止不让访问
		request.initSSLClient();
		//从数据库取数据
		List<ListInfoModel> codelist = MYSQLControl.getListInfoBySQL("select encryStr from companycode"
				+ " where idCardOrOrgCode "
				+ "NOT IN (select regno from detailinfo)", ListInfoModel.class);
		//待爬的队列
		Queue<String> queue = new LinkedList<String>();
		for( int i = 0; i < codelist.size(); i++ ){
			String encryStr = codelist.get(i).getEncryStr();
			queue.offer(encryStr);
		}
		boolean t = true;
		int count = 1;
		while (t) {
			//如果队列为空,循环结束
			if( queue.isEmpty() ){
				t = false;
			}else {
				String encryStr = queue.poll();
				String json = getJson(encryStr);
				if (json.contains("访问受限")||(isJson(json)==false)) {
					queue.offer(encryStr); //如果访问受限,则继续添加都队列中，否则执行解析
					//设置休息时间
					Thread.sleep(10*1000); 
					System.out.println("休息");
				}else {
					//解析数据
					List<DetailInfoModel> infoModels = Parse.getBasicInfoData(json);
					//存储数据
					MYSQLControl.executeInsertDetailInfo(infoModels);
				}
				if (count%3 == 0) {
					//产生随机数
					int m = (int)(Math.random()*2) + 5;
					Thread.sleep(m*1000);
				}
				count++;
			}
		}
	}
	//获取json数据--这里的网页url有两种拼接方式
	static String getJson(String encryStr){
		String url = "https://www.creditchina.gov.cn/"
				+ "api/credit_info_detail?encryStr="+encryStr+"%0A"; 
		String json = request.getHTMLContentByHttpGetMethod(url,"utf-8");
		if (json.contains("接口失败")) {
			url = "https://www.creditchina.gov.cn/"
					+ "api/credit_info_detail?encryStr="+encryStr+"%3D%0A"; 
			json = request.getHTMLContentByHttpGetMethod(url,"utf-8");
		}
		return json;
	}
	//判断是否为json
	public static boolean isJson(String str) {
		boolean result = false;
		try {
			JSON.parse(str);
			result = true;
		} catch (Exception e) {
			result=false;
		}
		return result;
	}
}
