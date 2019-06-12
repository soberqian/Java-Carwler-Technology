package com.mysql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class XMLMySQLConnections {

	private String driver = "";
	private String dbURL = "";
	private String user = "";
	private String password = "";
	private static XMLMySQLConnections connection = null;

	private XMLMySQLConnections(String node) throws Exception {
		//读XML文件
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("db.xml"));
		//取得jdbc相关的配置
		Element driverNameEle  = (Element)doc.selectObject("/config/connectionInfo/" + node + "/driver-name");
		Element urlEle = (Element) doc.selectObject("/config/connectionInfo/" + node + "/url");
		Element usenameEle  = (Element)doc.selectObject("/config/connectionInfo/" + node + "/username");
		Element passwordEle  = (Element) doc.selectObject("/config/connectionInfo/" + node + "/password");
		driver = driverNameEle.getStringValue();  //数据库驱动
		dbURL = urlEle.getStringValue(); //JDBC的URL
		user = usenameEle.getStringValue();  //数据库用户名
		password = passwordEle.getStringValue();  //数据库密码
	}
	public static Connection getConnection(String node) {
		Connection conn = null;
		if (connection == null) {
			try {
				connection = new XMLMySQLConnections(node);  //初始化
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		try {
			Class.forName(connection.driver);     //调用Class.forName()方法加载驱动程序
			conn = DriverManager.getConnection(connection.dbURL,
					connection.user, connection.password);  //建立数据库连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) throws SQLException {
		Connection con = getConnection("node1");  //需要连接的节点数据库
		Statement stmt = con.createStatement(); //创建Statement对象
		System.out.println("成功连接到数据库！");
		String sql = "select * from carsales";    //要执行的SQL
		ResultSet rs = stmt.executeQuery(sql);//结果集
		while (rs.next()){
			//输出1,2两列
			System.out.print(rs.getString(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.println();
		}
		con.close();  //关闭连接
	}
}
