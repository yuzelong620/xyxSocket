package com.crxl.xllxj.module.room.bean;

  
import java.util.List;

import org.springframework.data.annotation.Id; 
import com.crxl.xllxj.module.room.entity.RoomEntity;

public class RoomBean {
     
	public RoomBean() {
	}

	public RoomBean(RoomEntity entity) {
		this.id = entity.getId();  
		this.state = entity.getState(); 
		this.currentQuestionId=entity.getCurrentQuestionId();
		this.currentQuestionStartTime=entity.getCurrentQuestionStartTime();
		this.currentVideoId=entity.getCurrentVideoId();
		this.left=RoomBeanUtil.toMemberBean(entity.getLeft());
		this.right=RoomBeanUtil.toMemberBean(entity.getRight());
	}

	public RoomBean(String id, int currentQuestionId, long currentQuestionStartTime, int currentVideoId, int state,
			List<MemberBean> left, List<MemberBean> right) {
		super();
		this.id = id;
		this.currentQuestionId = currentQuestionId;
		this.currentQuestionStartTime = currentQuestionStartTime;
		this.currentVideoId = currentVideoId;
		this.state = state;
		this.left = left;
		this.right = right;
	}

	@Id
	private String id;
	int currentQuestionId ;
	long currentQuestionStartTime ;
	int currentVideoId;
	int state;  
	List<MemberBean> left;
	List<MemberBean> right; 
	
	
    
	public List<MemberBean> getLeft() {
		return left;
	}

	public void setLeft(List<MemberBean> left) {
		this.left = left;
	}

	public List<MemberBean> getRight() {
		return right;
	}

	public void setRight(List<MemberBean> right) {
		this.right = right;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getCurrentVideoId() {
		return currentVideoId;
	}

	public void setCurrentVideoId(int currentVideoId) {
		this.currentVideoId = currentVideoId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	} 
	
	
	 
}
