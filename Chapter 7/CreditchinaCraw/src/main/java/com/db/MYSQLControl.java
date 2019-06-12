package com.db;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.model.DetailInfoModel;
import com.model.ListInfoModel;

/*
 * author qianyang 1563178220@qq.com
 * Mysql操作的QueryRunner方法
 * 一个数据库操作类，别的程序直接调用即可
 */
public class MYSQLControl {
    //根据自己的数据库地址修改
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/"
    		+ "crawler?useUnicode=true&characterEncoding=UTF8");
    static QueryRunner qr = new QueryRunner(ds);
  	public static <T> List<T> getListInfoBySQL (String sql, Class<T> type ){
  		List<T> list = null;
  		try {
  			list = qr.query(sql,new BeanListHandler<T>(type));
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
  		return list;
  	}
   
    public static void executeInsertCodeInfo(List<ListInfoModel> jsonlist) {
        
        /* * 定义一个Object数组，行列
         * 6表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * */
         
        Object[][] params = new Object[jsonlist.size()][6];
        for ( int i=0; i<params.length; i++ ){
        	params[i][0] = jsonlist.get(i).getName();
            params[i][1] = jsonlist.get(i).getIdCardOrOrgCode();
            params[i][2] = jsonlist.get(i).getGoodCount();
            params[i][3] = jsonlist.get(i).getBadCount();
            params[i][4] = jsonlist.get(i).getDishonestyCount();
            params[i][5] = jsonlist.get(i).getEncryStr().replaceAll("\n", "").replace("=", "");
        }
        try {
        	qr.batch("insert into companycode (name,idCardOrOrgCode,"
        			+ "goodCount,badCount,dishonestyCount,encryStr)"
			        + "values (?,?,?,?,?,?)", params);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("执行数据库完毕！"+"成功插入数据："+jsonlist.size()+"条");
    }
   
    public static void executeInsertDetailInfo(List<DetailInfoModel> jsonlist) {
        
        /* * 定义一个Object数组，行列
         * 14表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * */
         
        Object[][] params = new Object[jsonlist.size()][14];
        for ( int i=0; i<params.length; i++ ){
        	params[i][0] = jsonlist.get(i).getId();
            params[i][1] = jsonlist.get(i).getEntName();
            params[i][2] = jsonlist.get(i).getLegalPerson();
            params[i][3] = jsonlist.get(i).getRegno();
            params[i][4] = jsonlist.get(i).getCreditCode();
            params[i][5] = jsonlist.get(i).getEnttype();
            params[i][6] = jsonlist.get(i).getDom();
            params[i][7] = jsonlist.get(i).getRegorg();
            params[i][8] = jsonlist.get(i).getEsdate();
            params[i][9] = jsonlist.get(i).getGoodCount();
            params[i][10] = jsonlist.get(i).getBadCount();
            params[i][11] = jsonlist.get(i).getDishonestyCount();
            params[i][12] = jsonlist.get(i).getXkCount();
            params[i][13] = jsonlist.get(i).getCfCount();
        }
        try {
        	qr.batch("insert into detailinfo "
			        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("执行数据库完毕！"+"成功插入数据："+jsonlist.size()+"条");
    }
}