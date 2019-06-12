package com.qian.test;

import java.util.List;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
public class JsoupXpathTest1 {
	public static void main(String[] args) {
		//基于URL创建JXDocument
		JXDocument jxd = JXDocument.createByUrl("http://www.w3school.com.cn/b.asp");
		//Xpath语句
		String str = "//*[@id='course']/ul/li/a";  
		//获取节点集合
		List<JXNode> list = jxd.selN(str);
		//遍历节点
		for (int i = 0; i < list.size(); i++) {
			JXNode node = list.get(i);
			System.out.println("标题为:" + node.asElement().text() + 
					"\tURL为:" + node.asElement().attr("href"));
		}
	}
}
