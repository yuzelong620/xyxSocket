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
public class SignIn_Json{
	/** ID::*/
	private Integer	id;
	/** 奖励列表::*/
	private List<StringIntTuple>	rewardList;

	/** ID::*/
	public Integer getId(){
		return this.id;
	}
	/** 奖励列表::*/
	public List<StringIntTuple> getRewardList(){
		return this.rewardList;
	}
	/**ID::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**奖励列表::*/
	public void setRewardList(List<StringIntTuple> rewardList){
		this.rewardList = rewardList;
	}
}