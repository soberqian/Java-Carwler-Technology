package com.crawler.htmlparser;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HTMLParserTest4 {

	public static void main(String[] args) throws IOException, ParserException {
		//使用URLConnection请求数据
		URL url = new URL("http://www.w3school.com.cn/b.asp");
		URLConnection conn = url.openConnection();
		Parser parser = new Parser(conn);
		NodeFilter filtername = new TagNameFilter("li");  //选择的节点为每个li
		NodeFilter filter = new HasChildFilter(filtername);
		NodeList nodes = parser.extractAllNodesThatMatch(filter);
		//循环遍历
		for(int i=0; i<nodes.size();i++){
			//获取li的第一个子节点
			Node node = (Node)nodes.elementAt(i).getFirstChild(); 
			System.out.println( node.toPlainTextString() );
		}
	}
}
