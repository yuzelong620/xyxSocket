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
public class DetecTive_Json{
	/** 编号::*/
	private Integer	id;
	/** 消耗::*/
	private List<StringIntTuple>	consume;
	/** 名称::*/
	private String	name;
	/** 路径::*/
	private String	path;
	/** 下一个等级::*/
	private Integer	nextGrade;
	/** 头部数据::*/
	private List<Double>	head;
	/** 身体数据::*/
	private List<Double>	body;

	/** 编号::*/
	public Integer getId(){
		return this.id;
	}
	/** 消耗::*/
	public List<StringIntTuple> getConsume(){
		return this.consume;
	}
	/** 名称::*/
	public String getName(){
		return this.name;
	}
	/** 路径::*/
	public String getPath(){
		return this.path;
	}
	/** 下一个等级::*/
	public Integer getNextGrade(){
		return this.nextGrade;
	}
	/** 头部数据::*/
	public List<Double> getHead(){
		return this.head;
	}
	/** 身体数据::*/
	public List<Double> getBody(){
		return this.body;
	}
	/**编号::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**消耗::*/
	public void setConsume(List<StringIntTuple> consume){
		this.consume = consume;
	}
	/**名称::*/
	public void setName(String name){
		this.name = name;
	}
	/**路径::*/
	public void setPath(String path){
		this.path = path;
	}
	/**下一个等级::*/
	public void setNextGrade(Integer nextGrade){
		this.nextGrade = nextGrade;
	}
	/**头部数据::*/
	public void setHead(List<Double> head){
		this.head = head;
	}
	/**身体数据::*/
	public void setBody(List<Double> body){
		this.body = body;
	}
}