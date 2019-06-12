package com.crawler.htmlparser;

import java.io.IOException;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HTMLParserTest2 {

	public static void main(String[] args) throws IOException, ParserException {
		//实例化Parser，用网页的 url 作为参数
		Parser parser = new Parser("http://www.w3school.com.cn/b.asp");
		//设置网页的编码(GBK)
		parser.setEncoding("gbk");
		//过滤页面中的标签
		NodeFilter filtertag= new TagNameFilter("ul");
		//父节点包含ul
		NodeFilter filterParent = new HasParentFilter(filtertag); 
		//包含li标签,并且li节点中包含id属性
		NodeFilter filtername = new TagNameFilter("li");  
		NodeFilter filterId= new HasAttributeFilter("id");
		//过滤器的并操作
		NodeFilter filter = new AndFilter(filterParent,filtername); 
		NodeFilter filterfinal = new AndFilter(filter,filterId); 
		//选择匹配到的内容
		NodeList list = parser.extractAllNodesThatMatch(filterfinal);  
		//循环遍历
		for(int i=0; i<list.size();i++){
			//获取li的第一个子节点
			Node node = (Node)list.elementAt(i).getFirstChild(); 
			System.out.println( "链接为：" + ((LinkTag) node).getLink() 
					+"\t标题为:" + node.toPlainTextString() );
		}
	}
}
