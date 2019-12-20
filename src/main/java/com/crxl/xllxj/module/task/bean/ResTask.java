package com.crxl.xllxj.module.task.bean;

public class ResTask {

	int taskId;
	int value;
	int rewardState;
	
	public ResTask() {
		
	}
	public ResTask(int taskId, int value, int rewardState) {
		this.taskId = taskId;
		this.value = value;
		this.rewardState = rewardState;
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
	public int getRewardState() {
		return rewardState;
	}
	public void setRewardState(int rewardState) {
		this.rewardState = rewardState;
	}
	
}
