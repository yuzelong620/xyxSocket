package com.crxl.xllxj.module.task.entity;

import org.springframework.data.annotation.Id;

public class TaskEntity {

	@Id
	String id;
	String userId; //用户ID
	int taskId; //任务编号
	int value; //用于判断是否达到完成条件
	int taskClassIfy; //任务分类
	int taskType; //任务类型
    int rewardState;//领取状态
    long nowDate = 0; //当前时间 
    boolean messageSendState;//消息发送状态

	public TaskEntity() {
		
	}

	public TaskEntity(String id, String userId, int taskId, int value, int taskClassIfy, int taskType, int rewardState,
			long nowDate,boolean messageSendState) {
		super();
		this.id = id;
		this.userId = userId;
		this.taskId = taskId;
		this.value = value;
		this.taskClassIfy = taskClassIfy;
		this.taskType = taskType;
		this.rewardState = rewardState;
		this.nowDate = nowDate;
		this.messageSendState = messageSendState;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getTaskClassIfy() {
		return taskClassIfy;
	}

	public void setTaskClassIfy(int taskClassIfy) {
		this.taskClassIfy = taskClassIfy;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public int getRewardState() {
		return rewardState;
	}

	public void setRewardState(int rewardState) {
		this.rewardState = rewardState;
	}

	public long getNowDate() {
		return nowDate;
	}

	public void setNowDate(long nowDate) {
		this.nowDate = nowDate;
	}

	public boolean isMessageSendState() {
		return messageSendState;
	}

	public void setMessageSendState(boolean messageSendState) {
		this.messageSendState = messageSendState;
	}

	
	
}
