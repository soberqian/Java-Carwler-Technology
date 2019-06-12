package com.fastjson.parse;


import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.model.BookModel;

public class FastjsonParseIArrayTest {

	public static void main(String[] args) {
		//json数组
		String json = "[{\"id\":\"01\",\"language\": \"Java\",\"edition\": \"third\",\"author\": \"Herbert Schildt\"},{\"id\":\"07\", \"language\": \"C++\",\"edition\": \"second\",\"author\": \"E.Balagurusamy\"}]";
		//使用fastjson解析Json数组
		List<BookModel> listmodel = JSON.parseObject(json, new TypeReference<List<BookModel>>(){}); //第一种方式
//		List<BookModel>  listmodel = JSON.parseArray(json, BookModel.class);  //第二种方式
		//输出数据
		for (BookModel model : listmodel) {
			System.out.println(model.getId() + "\t" + model.getLanguage() + 
					"\t" + model.getEdition());
		}
	}
}
