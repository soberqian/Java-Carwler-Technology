package com.model;
public class CommentModel {
	private String CommentId; //评论的id
	private String ItemId; //评论的菜品
	private String Content; //评论的内容
	private String CreateTime; //评论的时间
	private String OpenUserName;//评论作者的名称
	public String getCommentId() {
		return CommentId;
	}
	public void setCommentId(String commentId) {
		CommentId = commentId;
	}
	public String getItemId() {
		return ItemId;
	}
	public void setItemId(String itemId) {
		ItemId = itemId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getOpenUserName() {
		return OpenUserName;
	}
	public void setOpenUserName(String openUserName) {
		OpenUserName = openUserName;
	}
}
