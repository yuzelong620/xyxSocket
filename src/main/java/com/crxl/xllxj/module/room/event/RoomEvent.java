package com.crxl.xllxj.module.room.event;

import com.crxl.xllxj.module.room.entity.RoomEntity;
import com.crxl.xllxj.module.timer.event.MarkTask;

public abstract  class RoomEvent extends MarkTask{
	
	protected RoomEvent(RoomEntity room) { 
		this.room = room;
	}

	protected RoomEntity room;
	@Override
	public final String getMark() { 
		return room.getId();
	}
}
