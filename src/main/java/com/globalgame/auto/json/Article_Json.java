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
public class Article_Json{
	/** 物品ID::*/
	private Integer	id;
	/** 物品名称::*/
	private String	articleName;
	/** 物品类型::*/
	private Integer	articleType;
	/**   预留值::*/
	private Integer	value;
	/** 描述::*/
	private String	describe;

	/** 物品ID::*/
	public Integer getId(){
		return this.id;
	}
	/** 物品名称::*/
	public String getArticleName(){
		return this.articleName;
	}
	/** 物品类型::*/
	public Integer getArticleType(){
		return this.articleType;
	}
	/**   预留值::*/
	public Integer getValue(){
		return this.value;
	}
	/** 描述::*/
	public String getDescribe(){
		return this.describe;
	}
	/**物品ID::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**物品名称::*/
	public void setArticleName(String articleName){
		this.articleName = articleName;
	}
	/**物品类型::*/
	public void setArticleType(Integer articleType){
		this.articleType = articleType;
	}
	/**  预留值::*/
	public void setValue(Integer value){
		this.value = value;
	}
	/**描述::*/
	public void setDescribe(String describe){
		this.describe = describe;
	}
}