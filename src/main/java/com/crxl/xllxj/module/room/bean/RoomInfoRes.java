package com.crxl.xllxj.module.room.bean;
 
/**
 * 房间信息
 */
public class RoomInfoRes {
	
    String roomId;
    RoomBean room;
    
	public RoomInfoRes() { 
	}

	public RoomInfoRes(String roomId, RoomBean room ) {
		this.roomId = roomId;
		this.room = room; 
	}
	
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public RoomBean getRoom() {
		return room;
	}

	public void setRoom(RoomBean room) {
		this.room = room;
	}
	 
}
