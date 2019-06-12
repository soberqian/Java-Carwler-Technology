package com.parse;
import java.util.ArrayList;
import java.util.List;
import com.model.CarStockModel;
import com.util.TimeUtil;
public class Parse {
	public static List<CarStockModel> getData(String content) throws Exception {
		List<CarStockModel> list = new ArrayList<CarStockModel>();
		//数据预处理
		content = content.split("data:")[1].split(",recordsFiltered")[0];
		String stocks[] = content.split("\",");
		List<String> stocklist = new ArrayList<String>();
		for (int i = 0; i < stocks.length; i++) {
			stocklist.add(stocks[i].replace("[\"", "").replace("\"", "").replace("]", ""));
		}
		//获取数据
		for (int i = 0; i < stocklist.size(); i++) {
			String date = TimeUtil.GetNowDate("yyyy-MM-dd");
			String stock_id = stocklist.get(i).split(",")[1];
			String stock_name = stocklist.get(i).split(",")[2];
			//股票价格
			float stock_price=Float.parseFloat(stocklist.get(i).split(",")[3]);
			//涨跌额
			float stock_change = Float.parseFloat(stocklist.get(i).split(",")[4]);
			//涨跌幅
			float stock_range = Float.parseFloat(stocklist.get(i).split(",")[5]);
			//成交量
			int stock_trading_number = Integer.parseInt(stocklist.get(i).split(",")[6]);
			//成交额
			int stock_trading_value = Integer.parseInt(stocklist.get(i).split(",")[7]);
			//振幅
			float stock_amplitude = Float.parseFloat(stocklist.get(i).split(",")[8]);
			//最高
			float stock_max_price = Float.parseFloat(stocklist.get(i).split(",")[9]);
			//最低
			float stock_min_price = Float.parseFloat(stocklist.get(i).split(",")[10]);
			//今开
			float stock_todaystart_price = Float.parseFloat(stocklist.get(i).split(",")[11]);
			//昨收
			float stock_yesterdayfinish_price = Float.parseFloat(stocklist.get(i).split(",")[12]);
			//股票数据更新时间
			String stock_time = stocklist.get(i).split(",")[24];
			String craw_time = TimeUtil.GetNowDate("yyyy-MM-dd HH:mm:ss");
			//封装数据
			CarStockModel model = new CarStockModel();
			model.setDate(date);
			model.setStock_id(stock_id);
			model.setStock_name(stock_name);
			model.setStock_price(stock_price);
			model.setStock_change(stock_change);
			model.setStock_range(stock_range);
			model.setStock_amplitude(stock_amplitude);
			model.setStock_trading_number(stock_trading_number);
			model.setStock_trading_value(stock_trading_value);
			model.setStock_yesterdayfinish_price(stock_yesterdayfinish_price);
			model.setStock_todaystart_price(stock_todaystart_price);
			model.setStock_max_price(stock_max_price);
			model.setStock_min_price(stock_min_price);
			model.setStock_time(stock_time);
			model.setCraw_time(craw_time);
			list.add(model);
		}
		return list; 
	}
}
