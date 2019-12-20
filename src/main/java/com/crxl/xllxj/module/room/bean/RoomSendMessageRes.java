package com.crxl.xllxj.module.room.bean;
/**
 *返回聊天消息 
 *
 */
public class RoomSendMessageRes {
    public RoomSendMessageRes(){}
	public RoomSendMessageRes(String fromUserId, String content) {
		super();
		this.fromUserId = fromUserId;
		this.content = content;
	}
	String fromUserId;
	String content;
	
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	} 
	 
}
