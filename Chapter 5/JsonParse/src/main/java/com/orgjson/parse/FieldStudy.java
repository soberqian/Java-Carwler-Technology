package com.orgjson.parse;

import java.lang.reflect.Field;

import com.model.BookModel1;


public class FieldStudy {

	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		BookModel1 book = new BookModel1();
		book.setId("07");
		book.setLanguage("Java");
		book.setEdition("third");
		book.setAuthor("Herbert Schildt");
		//获取 Class对象
		Class cls = book.getClass();
		System.out.println(cls);
		/*返回一个 Field对象,反映此 Class对象所表示的类或接口的指定公共成员字段。
		 * 注意这里如果使用getField("author")则会产生报错,原因是author是私有变量
		 * */
		Field field1 = cls.getField("id");
        System.out.println("Public field found: " + field1.toString());
        //共有变量和私有变量皆可行
        Field field2 = cls.getDeclaredField("author"); 
        System.out.println("Public and Private field found: " + field2.toString());
        //getDeclaredFields()
        System.out.println("getDeclaredFields()结果为:");
        Field[] fieldarr1 = cls.getDeclaredFields(); 
        for (int i = 0; i < fieldarr1.length; i++) {
        	System.out.println("Public and Private field found: " + fieldarr1[i].toString());
		}
        //getFields()
        System.out.println("getFields()结果为:");
        Field[] fieldarr2 = cls.getFields(); 
        for (int i = 0; i < fieldarr2.length; i++) {
        	System.out.println("Public field found: " + fieldarr2[i].toString());
		}
        /*
         * 演示主要方法
		 * */
        //获取属性值
       Object object =  field1.get(book);
       System.out.println(object);
       //获取类型对象
       Class<?> class_test = field1.getType();
       System.out.println(class_test);
	}

}
