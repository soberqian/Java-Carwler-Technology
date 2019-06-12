package com.gson.parse;


import com.google.gson.Gson;
import com.model.BookModel;
public class GsonParseObjectTest {
	public static void main(String[] args) {
		//json对象
		String json = "{\"id\":\"07\",\"language\": \"C++\",\"edition\": \"second\",\"author\": \"E.Balagurusamy\"}";
		Gson gson = new Gson();  //初始化操作
		BookModel model = gson.fromJson(json, BookModel.class); //转化成Java对象
		//输出数据
		System.out.println(model.getId() + "\t" + model.getLanguage() + "\t" + model.getEdition());
	}
}

