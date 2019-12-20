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
public class Shop_Json{
	/** 编号::*/
	private Integer	id;
	/** 物品ID::*/
	private Integer	articleId;
	/** 购买消耗::*/
	private List<StringIntTuple>	buyExpend;
	/** 购买奖励::*/
	private List<StringIntTuple>	buyAward;
	/** 允许获得的数量::*/
	private Integer	obtainCount;
	/** 限制条件::*/
	private List<StringIntTuple>	condition;

	/** 编号::*/
	public Integer getId(){
		return this.id;
	}
	/** 物品ID::*/
	public Integer getArticleId(){
		return this.articleId;
	}
	/** 购买消耗::*/
	public List<StringIntTuple> getBuyExpend(){
		return this.buyExpend;
	}
	/** 购买奖励::*/
	public List<StringIntTuple> getBuyAward(){
		return this.buyAward;
	}
	/** 允许获得的数量::*/
	public Integer getObtainCount(){
		return this.obtainCount;
	}
	/** 限制条件::*/
	public List<StringIntTuple> getCondition(){
		return this.condition;
	}
	/**编号::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**物品ID::*/
	public void setArticleId(Integer articleId){
		this.articleId = articleId;
	}
	/**购买消耗::*/
	public void setBuyExpend(List<StringIntTuple> buyExpend){
		this.buyExpend = buyExpend;
	}
	/**购买奖励::*/
	public void setBuyAward(List<StringIntTuple> buyAward){
		this.buyAward = buyAward;
	}
	/**允许获得的数量::*/
	public void setObtainCount(Integer obtainCount){
		this.obtainCount = obtainCount;
	}
	/**限制条件::*/
	public void setCondition(List<StringIntTuple> condition){
		this.condition = condition;
	}
}