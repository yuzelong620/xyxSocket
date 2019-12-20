package com.crxl.xllxj.module.room.event;
 
import com.crxl.xllxj.module.room.entity.RoomEntity;
import com.crxl.xllxj.module.room.service.RoomService; 

public class GameStartEvent extends RoomEvent { 
	public GameStartEvent(RoomEntity room) {
		super(room); 
	}

	@Override
	public void execute() {
		RoomService roomService=new RoomService();
//		roomService.noticeRoomInfo(room, CommandId.room_notice_game_start);
		//延迟播放视频
	}

}
