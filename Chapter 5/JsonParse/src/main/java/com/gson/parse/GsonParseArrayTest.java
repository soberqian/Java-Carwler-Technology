package com.gson.parse;


import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.BookModel;
public class GsonParseArrayTest {
	public static void main(String[] args) {
		//json数组
		String json = "[{\"id\":\"01\",\"language\": \"Java\",\"edition\": \"third\",\"author\": \"Herbert Schildt\"},{\"id\":\"07\", \"language\": \"C++\",\"edition\": \"second\",\"author\": \"E.Balagurusamy\"}]";
		Gson gson = new Gson();  //实例化操作
		Type listType = new TypeToken<List<BookModel>>(){}.getType();  //TypeToken操作
		List<BookModel> listmodel = gson.fromJson(json, listType); //转化成集合
		//输出数据
		for (BookModel model : listmodel) {
			System.out.println(model.getId() + "\t" + model.getLanguage() + "\t" + model.getEdition());
		}
	}
}
