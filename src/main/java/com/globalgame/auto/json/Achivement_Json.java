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
public class Achivement_Json{
	/** 编号::*/
	private Integer	id;
	/** 题目id(题目表的id)::*/
	private String	name;
	/** 答案内容::*/
	private String	des;
	/** 是否为正确答案::*/
	private List<StringIntTuple>	reward;

	/** 编号::*/
	public Integer getId(){
		return this.id;
	}
	/** 题目id(题目表的id)::*/
	public String getName(){
		return this.name;
	}
	/** 答案内容::*/
	public String getDes(){
		return this.des;
	}
	/** 是否为正确答案::*/
	public List<StringIntTuple> getReward(){
		return this.reward;
	}
	/**编号::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**题目id(题目表的id)::*/
	public void setName(String name){
		this.name = name;
	}
	/**答案内容::*/
	public void setDes(String des){
		this.des = des;
	}
	/**是否为正确答案::*/
	public void setReward(List<StringIntTuple> reward){
		this.reward = reward;
	}
}