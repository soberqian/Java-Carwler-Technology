package com.orgjson.parse;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.model.BookModel;
import com.model.BookModel2;

public class OrgJsonBeanToObject {

	public static void main(String[] args) {
		JSONObject beanToJson= beanToJson();
		System.out.println(beanToJson);
		JSONObject mapToJson = mapToJson();
		System.out.println(mapToJson);
		JSONObject jSONTokenerToJson = jSONTokenerToJson();
		System.out.println(jSONTokenerToJson);
		JSONObject namesToJson = namesToJson();
		System.out.println(namesToJson);
		JSONObject accumulateToJson = accumulateToJson();
		System.out.println(accumulateToJson);
		JSONObject appendToJson = appendToJson();
		System.out.println(appendToJson);
	}
	/***
	 * 使用JSONObject(Object bean)构造方法将Bean对象转化成JSONObject
	 */
	public static JSONObject beanToJson (){
		BookModel book = new BookModel();
		book.setId("07");
		book.setLanguage("Java");
		book.setEdition("third");
		book.setAuthor("Herbert Schildt");
		//使用JSONObject(Object bean)构造方法
		return new JSONObject(book);
	}
	/***
	 * 使用JSONObject(Map<?, ?> m)构造方法将Map集合数据转出成JSONObject
	 */
	public static JSONObject mapToJson (){
		Map<String,String> bookmap = new HashMap<String, String>();
		bookmap.put("id", "07");
		bookmap.put("author", "Herbert Schildt");
		bookmap.put("edition", "third");
		bookmap.put("language", "Java");
		//使用JSONObject(Object bean)构造方法
		return new JSONObject(bookmap);
	}

	/***
	 * 使用JSONObject(JSONTokener x)构造方法将Map集合数据转出成JSONObject
	 */
	public static JSONObject jSONTokenerToJson (){
		/***
		 * JSONTokener类中的构造方法有:
		 * 1. JSONTokener(Reader reader)
		 * 2. JSONTokener(InputStream inputStream)
		 * 3. JSONTokener(String s):这个案例中使用的是这种
		 */
		String jsonStr = "{\"id\":\"07\",\"language\": \"C++\",\"edition\": \"second\",\"author\": \"E.Balagurusamy\"}";
		JSONTokener jsonTokener = new JSONTokener(jsonStr);
		return new JSONObject(jsonTokener);
	}
	/***
	 * 使用JSONObject(Object object, String names[])构造方法转出成JSONObject
	 */
	public static JSONObject namesToJson (){
		BookModel2 book = new BookModel2();
		book.setId("07");
		book.setLanguage("Java");
		book.setEdition("third");
		book.setAuthor("Herbert Schildt");
		String names[] = {"id","language","edition","author"};
		return new JSONObject(book,names);
	}
	/***
	 * 使用JSONObject(String source)构造方法转出成JSONObject
	 */
	public static JSONObject stringToJson (){
		//json字符串
		String json = "{\"id\":\"07\",\"language\": \"C++\",\"edition\": \"second\",\"author\": \"E.Balagurusamy\"}";
		return new JSONObject(json);
	}
	/***
	 * JSONObject accumulate(String key, Object value)方法的使用
	 */
	public static JSONObject accumulateToJson (){
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("id", "07");
		jsonObject.accumulate("language", "C++");
		jsonObject.accumulate("edition", "second");
		jsonObject.accumulate("author", "E.Balagurusamy");
		return jsonObject;
	}
	/***
	 * JSONObject append(String key, Object value)方法的使用
	 */
	public static JSONObject appendToJson (){
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("id", "07");
		jsonObject.append("language", "C++");
		jsonObject.append("edition", "second");
		jsonObject.append("author", "E.Balagurusamy");
		return jsonObject;
	}
}
