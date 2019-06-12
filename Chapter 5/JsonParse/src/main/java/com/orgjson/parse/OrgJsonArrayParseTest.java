package com.orgjson.parse;

import org.json.JSONArray;
import org.json.JSONObject;

public class OrgJsonArrayParseTest {
	
	public static void main(String[] args) {
		//json数组
		String json = "[{\"id\":\"01\",\"language\": \"Java\",\"edition\": \"third\",\"author\": \"Herbert Schildt\"},{\"id\":\"07\", \"language\": \"C++\",\"edition\": \"second\",\"author\": \"E.Balagurusamy\"}]";
		/*
		 * 转化成JSONArray对象
		 * 使用的是JSONArray(String source)构造方法
		 * */
		JSONArray jsonarray = new JSONArray(json); 
		for (int i = 0; i < jsonarray.length(); i++) {
			/*
			 * 获取指定json对象
			 * 使用的是JSONObject getJSONObject(int index)方法
			 * */
			JSONObject jsonobj = jsonarray.getJSONObject(i);   
			String id = jsonobj.getString("id");   
			String language = jsonobj.getString("language");  
			String edition = jsonobj.getString("edition"); 
			//输出解析的结果
			System.out.println(id + "\t" + language + "\t" + edition);
		}   
	}
}
