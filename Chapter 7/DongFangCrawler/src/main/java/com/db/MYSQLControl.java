package com.db;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

import com.model.CarStockModel;
public class MYSQLControl {
	//根据自己的数据库地址修改
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/"
    		+ "crawler?useUnicode=true&characterEncoding=UTF8");
    static QueryRunner qr = new QueryRunner(ds);
	//将采集的数据插入数据库
    public static void insertCarStocks ( List<CarStockModel> carstocks ) {
		Object[][] params = new Object[carstocks.size()][15];
		for ( int i = 0; i < carstocks.size(); i++ ){
			params[i][0] = carstocks.get(i).getDate();
			params[i][1] = carstocks.get(i).getStock_id();
			params[i][2] = carstocks.get(i).getStock_name();
			params[i][3] = carstocks.get(i).getStock_price();
			params[i][4] = carstocks.get(i).getStock_change();
			params[i][5] = carstocks.get(i).getStock_range();
			params[i][6] = carstocks.get(i).getStock_amplitude();
			params[i][7] = carstocks.get(i).getStock_trading_number();
			params[i][8] = carstocks.get(i).getStock_trading_value();
			params[i][9] = carstocks.get(i).getStock_yesterdayfinish_price();
			params[i][10] = carstocks.get(i).getStock_todaystart_price();
			params[i][11] = carstocks.get(i).getStock_max_price();
			params[i][12] = carstocks.get(i).getStock_min_price();
			params[i][13] = carstocks.get(i).getStock_time();
			params[i][14] = carstocks.get(i).getCraw_time();
		}
		QueryRunner qr = new QueryRunner(ds);
		try {
			qr.batch("INSERT INTO `dongfang_car_stock` VALUES (?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?)", params);
			System.out.println("执行数据库完毕！" + "成功插入数据：" + carstocks.size() + "条");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}