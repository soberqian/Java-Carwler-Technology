package com.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class MySQLConnections {
	private String driver = "";
	private String dbURL = "";
	private String user = "";
	private String password = "";
	private static MySQLConnections connection = null;
	private MySQLConnections() throws Exception {
		driver = "com.mysql.jdbc.Driver";  //数据库驱动
		dbURL = "jdbc:mysql://127.0.0.1:3306/crawler"; //JDBC的URL
		user = "root";  //数据库用户名
		password = "112233";  //数据库密码
		System.out.println("dbURL:" + dbURL);
	}
	public static Connection getConnection() {
		Connection conn = null;
		if (connection == null) {
			try {
				connection = new MySQLConnections();  //初始化连接
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
		Connection con = getConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();//创建Statement对象
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		System.out.println("成功连接到数据库！");
		//防止数据库中有数据,先删除表中数据
		stmt.execute("delete from carsales");
		//执行数据插入操作，忽略主键重复
		stmt.execute("insert ignore into carsales(month, sales) values ('2017-09-01', '500')");
		stmt.execute("insert ignore into carsales(month, sales) values ('2017-10-01', '100')");
		String sql = "select * from carsales";    //要执行的SQL
		//查询操作
		ResultSet rs = stmt.executeQuery(sql);
		//结果集
		while (rs.next()){
			//输出1,2两列
			System.out.print(rs.getString(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.println();
		}
		stmt.addBatch("update carsales set sales = '1000' where month = '2017-10-01' ");
		stmt.addBatch("update carsales set sales = '20' where month = '2017-09-01'");
		int number[] = stmt.executeBatch();  //批处理
		System.out.println("执行的sql语句数目为:" + number.length);
		ResultSet rs1 = stmt.executeQuery("select * from carsales");
		System.out.println("更新后的而结果为：");
		while (rs1.next()){
			//输出1,2两列
			System.out.print(rs1.getString(1) + "\t");
			System.out.print(rs1.getString(2) + "\t");
			System.out.println();
		}
		con.close();  //关闭连接
	}
}
