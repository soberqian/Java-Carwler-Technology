package com.qian.test;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
	/**
	 * 队列操作
	 */
	public static void main(String[] args) {
		/*队列操作
		*add()和remove()方法在失败的时候会抛出异常(不推荐)
		*/
		Queue<String> urlQueue = new LinkedList<String>();
		//添加元素
		urlQueue.offer("https://www.douban.com/people/46077896/likes/topic/");
		urlQueue.offer("https://www.douban.com/people/1475408/likes/topic");
		urlQueue.offer("https://www.douban.com/people/3853295/likes/topic/");
		for(String url : urlQueue){
			System.out.println(url);
		}
		System.out.println("=====================");
		System.out.println("第一个url为:" + urlQueue.poll()); //返回第对头元素，并在队列中删除
		for(String url : urlQueue){
			System.out.println(url);
		}
		System.out.println("=====================");
		System.out.println("第一个url为:" + urlQueue.element()); //获取对头元素但不移除队头元素
		for(String url : urlQueue){
			System.out.println(url);
		}
		System.out.println("=====================");
		System.out.println("第一个url为:" + urlQueue.peek()); //获取对头元素但不移除队头元素
		for(String url : urlQueue){
			System.out.println(url);
		}
		if( urlQueue.isEmpty() ){  //判读队列是否为空
			System.out.println("对列为空！");
		}else {
			System.out.println("队列不为空,包含的元素个数为:" + urlQueue.size());
		}
	}
}
