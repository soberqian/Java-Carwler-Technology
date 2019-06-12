package com.model;

import java.util.List;

public class BookSummaryModel {
	private String goodRateShow;
	private String poorRateShow;
	private String poorCountStr;
	private List<BookModel> book;
	public String getGoodRateShow() {
		return goodRateShow;
	}
	public String getPoorRateShow() {
		return poorRateShow;
	}
	public String getPoorCountStr() {
		return poorCountStr;
	}
	public List<BookModel> getBook() {
		return book;
	}
}
