package com.crxl.xllxj.module.room.bean;

import java.util.List;

import com.globalgame.auto.json.Answer_Json;

public class QuestionAnswerInfoBean {
	int number;
	VideoInfoBean video;
	String questionDesc;
    List<ItemAnswerBean> leftItemAnswers;
    List<ItemAnswerBean> rightItemAnswers;
    List<Answer_Json> answers;
    
	public QuestionAnswerInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public QuestionAnswerInfoBean(int number,VideoInfoBean video, List<ItemAnswerBean> leftItemAnswers,
			List<ItemAnswerBean> rightItemAnswers,String questionDesc,List<Answer_Json> answers) {
		this.video = video;
		this.number=number;
		this.rightItemAnswers = rightItemAnswers;
		this.leftItemAnswers = leftItemAnswers;	
		this.questionDesc=questionDesc;
		this.answers=answers;
	}

	
	public List<Answer_Json> getAnswers() {
		return answers;
	}


	public void setAnswers(List<Answer_Json> answers) {
		this.answers = answers;
	}


	public String getQuestionDesc() {
		return questionDesc;
	}


	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public VideoInfoBean getVideo() {
		return video;
	}


	public void setVideo(VideoInfoBean video) {
		this.video = video;
	}


	public List<ItemAnswerBean> getLeftItemAnswers() {
		return leftItemAnswers;
	}


	public void setLeftItemAnswers(List<ItemAnswerBean> leftItemAnswers) {
		this.leftItemAnswers = leftItemAnswers;
	}


	public List<ItemAnswerBean> getRightItemAnswers() {
		return rightItemAnswers;
	}


	public void setRightItemAnswers(List<ItemAnswerBean> rightItemAnswers) {
		this.rightItemAnswers = rightItemAnswers;
	}
}
