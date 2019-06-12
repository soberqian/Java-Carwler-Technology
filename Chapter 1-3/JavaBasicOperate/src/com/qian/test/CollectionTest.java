package com.qian.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
public class CollectionTest {
	public static void main(String[] args)  {
		//List集合的创建
		List<String> urllist = new ArrayList<String>();
		//集合元素的添加
		urllist.add("https://movie.douban.com/subject/27608425");
		urllist.add("https://movie.douban.com/subject/26968024");
		//第一种方式遍历集合
		for( String url : urllist ){
		    System.out.println(url);
		}
		//第二种方式遍历集合
		for( int i=0; i<urllist.size(); i++ ){
		    System.out.println(i+":"+urllist.get(i));
		}
		//第三种方式遍历集合
		Iterator<String> it = urllist.iterator();
		while ( it.hasNext() ){
		    System.out.println(it.next());
		}

		//Set集合的初始化
		Set<String> set = new HashSet<String>();
	    set.add("https://movie.douban.com/subject/27608425");
	    set.add("https://movie.douban.com/subject/27608425");
	    set.add("https://movie.douban.com/subject/26968024");
	    //Set集合的遍历
	    Iterator<String> setIt = set.iterator();
	    while ( setIt.hasNext() ){
	      System.out.println(setIt.next());
	    }

	    //Map集合的初始化
	    Map<String,Integer> map = new HashMap<String,Integer>();
	    //值的添加，这里假设是爬虫中的产品id以及每个产品id对应的销售量
	    map.put("jd1515", 100);
	    map.put("jd1516", 300);
	    map.put("jd1515", 100);
	    map.put("jd1517", 200);
	    map.put("jd1518", 100);
	    //第一种方法遍历map集合
	    for (String key : map.keySet()) {  
	        Integer value = map.get(key);  
	        System.out.println("Key = " + key + ", Value = " + value);  
	    }  
	    //第二种方法遍历map结合
	    Iterator<Entry<String, Integer>> entries = map.entrySet().iterator();  
	    while (entries.hasNext()) {  
	        Entry<String, Integer> entry = entries.next();  
	        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
	    }  
	    //第三种方法遍历map集合
	    for (Entry<String, Integer> entry : map.entrySet()) {  
	        System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());  
	    }  
		
	}
}
