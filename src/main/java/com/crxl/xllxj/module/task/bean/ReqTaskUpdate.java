package com.crxl.xllxj.module.task.bean;

public class ReqTaskUpdate {

	int taskType;
	int value;

	public ReqTaskUpdate() {

	}

	public ReqTaskUpdate(int taskType, int value) {
		this.taskType = taskType;
		this.value = value;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
