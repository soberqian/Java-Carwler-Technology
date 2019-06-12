package com.db;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import com.model.SinaAutoNewsModel;
public class MYSQLControl {
	//根据自己的数据库地址修改
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/"
    		+ "crawler?useUnicode=true&characterEncoding=UTF8");
    static QueryRunner qr = new QueryRunner(ds);
    public static void executeInsert(SinaAutoNewsModel model)  {
        Object[][] params = new Object[1][5];  //数据的维度
        params[0][0] = model.getDocid();
        params[0][1] = model.getUrl();
        params[0][2] = model.getTime();
        params[0][3] = model.getTitle();
        params[0][4] = model.getContent();
        //使用batch方法插入
        try {
			qr.batch("insert into  sinaautonews(docid,url,time,"
					+ "title,content) "
			        + "values (?,?,?,?,?)", params);
			System.out.println("执行数据库完毕！" + "成功插入数据 1条");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}