package com.mysql;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLReadTest {

	public static void main(String[] args) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("db.xml"));
		//取得jdbc相关的配置
		Element driverNameEle  = (Element)doc.selectObject("/config/connectionInfo/node1/driver-name");
		//设置jdbc相关的配置
		System.out.println(driverNameEle .getStringValue());
	}

}
