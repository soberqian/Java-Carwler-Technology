package com.model;

public class AppModel {
	private String id; 
	private String name; //app名称
	private String user_downloads; //下载次数
	private String update_time; //更新时间
	private String developer;  //开发者
	private String size;  //app大小
	private String tag;  //app标签
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser_downloads() {
		return user_downloads;
	}
	public void setUser_downloads(String user_downloads) {
		this.user_downloads = user_downloads;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
