package com.db;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import com.model.AppModel;
public class MYSQLControl{
	//根据自己的数据库地址修改
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/"
    		+ "crawler?useUnicode=true&characterEncoding=UTF8");
    static QueryRunner qr = new QueryRunner(ds);
    public static void executeInsertAPP(AppModel data)  {
        Object[][] params = new Object[1][7];  //数据的维度
        params[0][0] = data.getId();
        params[0][1] = data.getName();
        params[0][2] = data.getUser_downloads();
        params[0][3] = data.getUpdate_time();
        params[0][4] = data.getDeveloper();
        params[0][5] = data.getSize();
        params[0][6] = data.getTag();
        //使用batch方法批量插入
        try {
			qr.batch("insert into wandoujiaapp(id,name,user_downloads,"
					+ "update_time,developer,size,tag) "
			        + "values (?,?,?,?,?,?,?)", params);
			System.out.println("成功插入一条数据！");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}