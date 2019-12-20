package com.crxl.xllxj.module.task.bean;

public class TaskBean {

	int taskId; // 任务ID
	int taskScope; // 任务所属范围
	int taskType; // 任务类型
	int value; //进度

	public TaskBean() {

	}
	
	public TaskBean(int taskScope) {
		super();
		this.taskScope = taskScope;
	}

	public TaskBean(int taskId, int taskScope, int taskType) {
		super();
		this.taskId = taskId;
		this.taskScope = taskScope;
		this.taskType = taskType;
	}

	public TaskBean(int taskId, int taskScope, int taskType, int value) {
		super();
		this.taskId = taskId;
		this.taskScope = taskScope;
		this.taskType = taskType;
		this.value = value;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getTaskScope() {
		return taskScope;
	}

	public void setTaskScope(int taskScope) {
		this.taskScope = taskScope;
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
