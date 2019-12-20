package com.crxl.xllxj.module.room.bean;

public class RoomNextStepReq {
	int currentStageId;//当前 阶段id 用于并发 阶段状态判断。

	public int getCurrentStageId() {
		return currentStageId;
	}

	public void setCurrentStageId(int currentStageId) {
		this.currentStageId = currentStageId;
	}
	 
}
