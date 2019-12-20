package com.crxl.xllxj.module.room.bean;

import java.util.List;

import com.globalgame.auto.json.Answer_Json;

public class QuestionStartBean {
	
    public QuestionStartBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	int questionId;//题目id
    String questionDesc;//题目描述
    List<Answer_Json> answers;
	public QuestionStartBean(int questionId, String questionDesc, List<Answer_Json> answers) {
		this.questionId = questionId;
		this.questionDesc = questionDesc;
		this.answers = answers;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	public List<Answer_Json> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer_Json> answers) {
		this.answers = answers;
	}
    
    
}
