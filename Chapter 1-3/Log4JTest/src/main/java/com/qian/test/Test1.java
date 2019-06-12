package com.qian.test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
//循环遍历,然后对每个循环的外层进行排序，找绝对值最小,保存到map集合，输出结果
public class Test1 {
	public static void main(String[] args) {
		int an[] = new int[3];
		an[0] = 1;
		an[1] = 5;
		an[2] = 3;
		output(an,3);

	}
	public static void output (int[] an, int n ){
		//i的遍历
		for (int i = 0; i <n; i++) {
			//存储map集合
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			//j的遍历
			for (int j = 0; j < i; j++) {
				int c = absoluteValue(an[j] - an[i]);
				map.put(j, c);
				map = sortByValueDescending(map);
			}
			//判断第一和第二
	        loop:for (Entry<Integer, Integer> entry : map.entrySet()) {
	            System.out.println(entry.getValue() + " " + (entry.getKey()+1));
	            break loop;
	        }
		}
	}
	//取绝对值
	public static int absoluteValue (int a){
		if (a>=0) {
			return a;
		}else {
			return -a;
		}
	}
	 //降序排序
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map)
    {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return -compare;
            }
        });
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

	
}
