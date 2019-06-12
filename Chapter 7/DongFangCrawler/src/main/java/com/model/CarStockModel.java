package com.model;

public class CarStockModel {
	private String date;  //日期
	private String stock_id; //股票代码
	private String stock_name; //股票名称
	private float stock_price;  //最新价
	private float stock_change;  //涨跌额
	private float stock_range;  //涨跌幅
	private float stock_amplitude; //振幅
	private int stock_trading_number; //成交量
	private int stock_trading_value;  //成交额
	private float stock_yesterdayfinish_price; //昨收
	private float stock_todaystart_price;  //今开
	private float stock_max_price;  //最低
	private float stock_min_price; //最高
	private String stock_time; //数据时间
	private String craw_time;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getStock_id() {
		return stock_id;
	}
	public void setStock_id(String stock_id) {
		this.stock_id = stock_id;
	}
	public String getStock_name() {
		return stock_name;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}
	public float getStock_price() {
		return stock_price;
	}
	public void setStock_price(float stock_price) {
		this.stock_price = stock_price;
	}
	public float getStock_change() {
		return stock_change;
	}
	public void setStock_change(float stock_change) {
		this.stock_change = stock_change;
	}
	public float getStock_range() {
		return stock_range;
	}
	public void setStock_range(float stock_range) {
		this.stock_range = stock_range;
	}
	public float getStock_amplitude() {
		return stock_amplitude;
	}
	public void setStock_amplitude(float stock_amplitude) {
		this.stock_amplitude = stock_amplitude;
	}
	
	public int getStock_trading_number() {
		return stock_trading_number;
	}
	public void setStock_trading_number(int stock_trading_number) {
		this.stock_trading_number = stock_trading_number;
	}
	public int getStock_trading_value() {
		return stock_trading_value;
	}
	public void setStock_trading_value(int stock_trading_value) {
		this.stock_trading_value = stock_trading_value;
	}
	public float getStock_yesterdayfinish_price() {
		return stock_yesterdayfinish_price;
	}
	public void setStock_yesterdayfinish_price(float stock_yesterdayfinish_price) {
		this.stock_yesterdayfinish_price = stock_yesterdayfinish_price;
	}
	public float getStock_todaystart_price() {
		return stock_todaystart_price;
	}
	public void setStock_todaystart_price(float stock_todaystart_price) {
		this.stock_todaystart_price = stock_todaystart_price;
	}
	public float getStock_max_price() {
		return stock_max_price;
	}
	public void setStock_max_price(float stock_max_price) {
		this.stock_max_price = stock_max_price;
	}
	public float getStock_min_price() {
		return stock_min_price;
	}
	public void setStock_min_price(float stock_min_price) {
		this.stock_min_price = stock_min_price;
	}
	
	public String getStock_time() {
		return stock_time;
	}
	public void setStock_time(String stock_time) {
		this.stock_time = stock_time;
	}
	public String getCraw_time() {
		return craw_time;
	}
	public void setCraw_time(String craw_time) {
		this.craw_time = craw_time;
	}
}
