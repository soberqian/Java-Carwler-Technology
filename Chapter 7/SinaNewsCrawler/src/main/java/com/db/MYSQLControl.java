package com.db;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import com.model.SinaNewsModel;
import com.util.TimeUtil;
public class MYSQLControl {
	//根据自己的数据库地址修改
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/"
    		+ "crawler?useUnicode=true&characterEncoding=UTF8");
    static QueryRunner qr = new QueryRunner(ds);
    public static void executeInsert(List<SinaNewsModel> data)  {
        Object[][] params = new Object[data.size()][7];  //数据的维度
        for ( int i = 0; i < params.length; i++ ){
            params[i][0] = data.get(i).getDocid();
            params[i][1] = data.get(i).getUrl();
            params[i][2] = data.get(i).getTitle();
            params[i][3] = TimeUtil.TimeStampToDate(
            		data.get(i).getCtime(), "yyyy-MM-dd HH:mm:ss"); //时间标准化
            params[i][4] = data.get(i).getIntro();
            params[i][5] = data.get(i).getKeywords();
            params[i][6] = data.get(i).getMedia_name();
        }
        //使用batch方法批量插入
        try {
			qr.batch("insert into  sinanews(docid,url,title,"
					+ "ctime,intro,keywords,media_name)"
			        + "values (?,?,?,?,?,?,?)", params);
			System.out.println("执行数据库完毕！"+"成功插入数据："+data.size()+"条");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}