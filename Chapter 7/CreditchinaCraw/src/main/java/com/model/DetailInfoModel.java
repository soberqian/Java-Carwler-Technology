package com.model;

public class DetailInfoModel {
	private String id; //对应的id
	private String entName;//对应的企业名称
	private String legalPerson; //法人代表
	private String regno; //工商注册号
	private String creditCode; //组织机构代码
	private String enttype; //企业类型
	private String dom; //企业地址
	private String regorg; //登记机关
	private String esdate; //成立日期
	private String goodCount; //守信红名单次数
	private String badCount; //黑名单次数
	private String dishonestyCount;//重点关注名单次数
	private String xkCount;//行政许可
	private String cfCount; //行政处罚
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getEnttype() {
		return enttype;
	}
	public void setEnttype(String enttype) {
		this.enttype = enttype;
	}
	public String getDom() {
		return dom;
	}
	public void setDom(String dom) {
		this.dom = dom;
	}
	public String getRegorg() {
		return regorg;
	}
	public void setRegorg(String regorg) {
		this.regorg = regorg;
	}
	public String getEsdate() {
		return esdate;
	}
	public void setEsdate(String esdate) {
		this.esdate = esdate;
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
	public String getDishonestyCount() {
		return dishonestyCount;
	}
	public void setDishonestyCount(String dishonestyCount) {
		this.dishonestyCount = dishonestyCount;
	}
	public String getXkCount() {
		return xkCount;
	}
	public void setXkCount(String xkCount) {
		this.xkCount = xkCount;
	}
	public String getCfCount() {
		return cfCount;
	}
	public void setCfCount(String cfCount) {
		this.cfCount = cfCount;
	}
}
