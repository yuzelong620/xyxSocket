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
public class DailyTask_Json{
	/** 编号::*/
	private Integer	id;
	/** 题目id(题目表的id)::*/
	private List<StringIntTuple>	rewardList;

	/** 编号::*/
	public Integer getId(){
		return this.id;
	}
	/** 题目id(题目表的id)::*/
	public List<StringIntTuple> getRewardList(){
		return this.rewardList;
	}
	/**编号::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**题目id(题目表的id)::*/
	public void setRewardList(List<StringIntTuple> rewardList){
		this.rewardList = rewardList;
	}
}