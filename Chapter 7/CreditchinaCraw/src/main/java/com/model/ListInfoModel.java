package com.model;
//用于封装守信名单
public class ListInfoModel {
	private String name;//企业名称
	private String idCardOrOrgCode; //组织机构代码
	private String goodCount;  //守信记录次数
	private String badCount;  //重点关注记录次数
	private String dishonestyCount;//黑名单记录次数
	private String encryStr; //用于拼接url
	public String getEncryStr() {
		return encryStr;
	}
	public void setEncryStr(String encryStr) {
		this.encryStr = encryStr;
	}
	public String getDishonestyCount() {
		return dishonestyCount;
	}
	public void setDishonestyCount(String dishonestyCount) {
		this.dishonestyCount = dishonestyCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCardOrOrgCode() {
		return idCardOrOrgCode;
	}
	public void setIdCardOrOrgCode(String idCardOrOrgCode) {
		this.idCardOrOrgCode = idCardOrOrgCode;
	}
	public String getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(String goodCount) {
		this.goodCount = goodCount;
	}
	public String getBadCount() {
		return badCount;
	}
	public void setBadCount(String badCount) {
		this.badCount = badCount;
	}
}
