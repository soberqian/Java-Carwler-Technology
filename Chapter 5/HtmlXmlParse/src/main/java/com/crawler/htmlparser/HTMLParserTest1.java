package com.crawler.htmlparser;

import java.io.IOException;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class HTMLParserTest1 {
	public static void main(String[] args) throws IOException, ParserException {
		//使用Jsoup获取html文件
		Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp")
				.timeout(5000).get(); 
		//转化成String格式
		String html =doc.html();  
		//使用Lexer构造
		Lexer lexer = new Lexer(html);
		Parser parser = new Parser(lexer);
		//过滤页面中的链接标签
		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		//获取匹配到的节点
		NodeList list = parser.extractAllNodesThatMatch(filter);
		//遍历每一个节点,获取链接以及标题
		for(int i=0; i<list.size();i++){
			Node node = (Node)list.elementAt(i); 
			System.out.println("链接为：" + ((LinkTag) node).getLink() 
					+ "\t标题为:" + node.toPlainTextString() );
		}
	}
}
