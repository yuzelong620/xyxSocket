package com.globalgame.auto.json;
import java.util.List;
import com.mind.core.util.StringIntTuple;
import com.mind.core.util.IntDoubleTuple;
import com.mind.core.util.IntTuple;
import com.mind.core.util.ThreeTuple;
import com.mind.core.util.StringFloatTuple;

/**
*自动生成类
*/
public class Answer_Json{
	/** 编号::*/
	private Integer	id;
	/** 题目id(题目表的id)::*/
	private Integer	questionId;
	/** 答案内容::*/
	private String	content;
	/** 是否为正确答案::*/
	private Integer	isCorrect;

	/** 编号::*/
	public Integer getId(){
		return this.id;
	}
	/** 题目id(题目表的id)::*/
	public Integer getQuestionId(){
		return this.questionId;
	}
	/** 答案内容::*/
	public String getContent(){
		return this.content;
	}
	/** 是否为正确答案::*/
	public Integer getIsCorrect(){
		return this.isCorrect;
	}
	/**编号::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**题目id(题目表的id)::*/
	public void setQuestionId(Integer questionId){
		this.questionId = questionId;
	}
	/**答案内容::*/
	public void setContent(String content){
		this.content = content;
	}
	/**是否为正确答案::*/
	public void setIsCorrect(Integer isCorrect){
		this.isCorrect = isCorrect;
	}
}