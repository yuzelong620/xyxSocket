package com.crxl.xllxj.module.room.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.crxl.xllxj.module.room.bean.RoomItemBean;
@Document
public class RoomEntity {

	public RoomEntity() {
	}
	public RoomEntity(String id,  long startTime, int state,List<RoomItemBean> left,List<RoomItemBean> right,Map<String,RoomItemBean> items,List<Integer> questionIds,Map<Integer,Integer> questionNumber) {
		this.id = id; 
		this.startTime = startTime; 
		this.state = state;
		this.right=right;
		this.left=left;
		//存入  map作为映射
		this.items=items;
		this.questionIds=questionIds; 
		this.questionNumber=questionNumber;
	}
	@Id
	private String id;
	long startTime;
	long validTime; 
	int state;
	
	int  currentVideoId;
	int  currentQuestionId;//当前题id。
	long currentQuestionStartTime;//问题开始时间
	Map<String,Integer> currentPlayerAnswers=new HashMap<>();//当前玩家回答的答案
	List<Integer> questionIds=new ArrayList<>();//所有题id	
	Map<Integer,Integer> questionNumber=new HashMap<>();//
	Map<String, RoomItemBean> items = new HashMap<>();	
	List<RoomItemBean> left=new ArrayList<RoomItemBean>();
	List<RoomItemBean> right=new ArrayList<RoomItemBean>(); 
    
	
	public Map<Integer, Integer> getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(Map<Integer, Integer> questionNumber) {
		this.questionNumber = questionNumber;
	}
	public Map<String, Integer> getCurrentPlayerAnswers() {
		return currentPlayerAnswers;
	}
	public void setCurrentPlayerAnswers(Map<String, Integer> currentPlayerAnswers) {
		this.currentPlayerAnswers = currentPlayerAnswers;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getValidTime() {
		return validTime;
	}
	public void setValidTime(long validTime) {
		this.validTime = validTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getCurrentVideoId() {
		return currentVideoId;
	}
	public void setCurrentVideoId(int currentVideoId) {
		this.currentVideoId = currentVideoId;
	}
	public int getCurrentQuestionId() {
		return currentQuestionId;
	}
	public void setCurrentQuestionId(int currentQuestionId) {
		this.currentQuestionId = currentQuestionId;
	}
	public long getCurrentQuestionStartTime() {
		return currentQuestionStartTime;
	}
	public void setCurrentQuestionStartTime(long currentQuestionStartTime) {
		this.currentQuestionStartTime = currentQuestionStartTime;
	}
	public List<Integer> getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(List<Integer> questionIds) {
		this.questionIds = questionIds;
	}
	public Map<String, RoomItemBean> getItems() {
		return items;
	}
	public void setItems(Map<String, RoomItemBean> items) {
		this.items = items;
	}
	public List<RoomItemBean> getLeft() {
		return left;
	}
	public void setLeft(List<RoomItemBean> left) {
		this.left = left;
	}
	public List<RoomItemBean> getRight() {
		return right;
	}
	public void setRight(List<RoomItemBean> right) {
		this.right = right;
	}
	
}
