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
public class DropGroup_Json{
	/** 编号::*/
	private Integer	id;
	/** 几率::*/
	private Integer	odds;
	/** 奖励::*/
	private StringIntTuple	rewards;
	/** 组号::*/
	private Integer	groupId;

	/** 编号::*/
	public Integer getId(){
		return this.id;
	}
	/** 几率::*/
	public Integer getOdds(){
		return this.odds;
	}
	/** 奖励::*/
	public StringIntTuple getRewards(){
		return this.rewards;
	}
	/** 组号::*/
	public Integer getGroupId(){
		return this.groupId;
	}
	/**编号::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**几率::*/
	public void setOdds(Integer odds){
		this.odds = odds;
	}
	/**奖励::*/
	public void setRewards(StringIntTuple rewards){
		this.rewards = rewards;
	}
	/**组号::*/
	public void setGroupId(Integer groupId){
		this.groupId = groupId;
	}
}