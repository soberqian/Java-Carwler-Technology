package com.model;

public class SinaAutoNewsModel {
	private int docid; //Crawler4j自动编号的id
	private String url;  //新闻
	private String time;  //新闻发表的时间
	private String title;  //新闻的标题
	private String content;  //新闻的内容
	public int getDocid() {
		return docid;
	}
	public void setDocid(int docid) {
		this.docid = docid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
