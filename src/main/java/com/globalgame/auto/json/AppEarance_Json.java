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
public class AppEarance_Json{
	/** 物品ID::*/
	private Integer	id;
	/** 物品所属范围::*/
	private Integer	articleScope;
	/** 物品名称::*/
	private String	articleName;
	/** 限制条件::*/
	private List<StringIntTuple>	condition;
	/** 消耗::*/
	private List<StringIntTuple>	consume;
	/** 购买前描述::*/
	private String	buyFrontText;
	/** 增加奖励::*/
	private List<StringIntTuple>	addAward;
	/** 物品类型::*/
	private Integer	itemType;
	/** 购买后描述::*/
	private String	buyBackText;
	/** 排序列::*/
	private Integer	rank;
	/** 特殊物品类型::*/
	private Integer	specialItemType;

	/** 物品ID::*/
	public Integer getId(){
		return this.id;
	}
	/** 物品所属范围::*/
	public Integer getArticleScope(){
		return this.articleScope;
	}
	/** 物品名称::*/
	public String getArticleName(){
		return this.articleName;
	}
	/** 限制条件::*/
	public List<StringIntTuple> getCondition(){
		return this.condition;
	}
	/** 消耗::*/
	public List<StringIntTuple> getConsume(){
		return this.consume;
	}
	/** 购买前描述::*/
	public String getBuyFrontText(){
		return this.buyFrontText;
	}
	/** 增加奖励::*/
	public List<StringIntTuple> getAddAward(){
		return this.addAward;
	}
	/** 物品类型::*/
	public Integer getItemType(){
		return this.itemType;
	}
	/** 购买后描述::*/
	public String getBuyBackText(){
		return this.buyBackText;
	}
	/** 排序列::*/
	public Integer getRank(){
		return this.rank;
	}
	/** 特殊物品类型::*/
	public Integer getSpecialItemType(){
		return this.specialItemType;
	}
	/**物品ID::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**物品所属范围::*/
	public void setArticleScope(Integer articleScope){
		this.articleScope = articleScope;
	}
	/**物品名称::*/
	public void setArticleName(String articleName){
		this.articleName = articleName;
	}
	/**限制条件::*/
	public void setCondition(List<StringIntTuple> condition){
		this.condition = condition;
	}
	/**消耗::*/
	public void setConsume(List<StringIntTuple> consume){
		this.consume = consume;
	}
	/**购买前描述::*/
	public void setBuyFrontText(String buyFrontText){
		this.buyFrontText = buyFrontText;
	}
	/**增加奖励::*/
	public void setAddAward(List<StringIntTuple> addAward){
		this.addAward = addAward;
	}
	/**物品类型::*/
	public void setItemType(Integer itemType){
		this.itemType = itemType;
	}
	/**购买后描述::*/
	public void setBuyBackText(String buyBackText){
		this.buyBackText = buyBackText;
	}
	/**排序列::*/
	public void setRank(Integer rank){
		this.rank = rank;
	}
	/**特殊物品类型::*/
	public void setSpecialItemType(Integer specialItemType){
		this.specialItemType = specialItemType;
	}
}