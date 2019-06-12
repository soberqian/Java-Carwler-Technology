package com.crawler.htmlcleaner;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class HtmlcleanerTest2 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, XPatherException {
		URL url=new URL("http://www.w3school.com.cn/b.asp");
		//实例化HtmlCleaner
		HtmlCleaner cleaner = new HtmlCleaner(); 
		//转化成TagNode
		TagNode node = cleaner.clean(url,"gbk"); 
		//遍历获取课程名以及课程地址
		Object[]  ns2 = node.evaluateXPath("//*[@id='course']/ul//a");  //这里使用//a表示不考虑位置,如果使用/a获取不到内容
		for(Object on : ns2) {  
			TagNode n = (TagNode) on;  
			System.out.println("课程名为:\t" + n.getText() + "\t地址为:\t" + n.getAttributeByName("href"));  
		} 
		TagNode n = (TagNode) ns2[0]; 
		TagNode tagNode_pa = n.getParent();
		List<TagNode> tagNode_chi = tagNode_pa.getChildTagList();

	}
}
