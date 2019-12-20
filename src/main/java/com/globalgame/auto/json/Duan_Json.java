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
public class Duan_Json{
	/** 编号::*/
	private Integer	id;
	/** 段位名字::*/
	private String	duanName;
	/** 升级需要的星数::*/
	private Integer	starNum;
	/** 获得特殊奖励的连续次数::*/
	private Integer	succWin;
	/** 特殊奖励星星数量::*/
	private Integer	succWinStar;
	/** 升级下一个id::*/
	private Integer	nextId;
	/** 段位分类::*/
	private Integer	duanClassIfy;
	/** 上一个段位id::*/
	private Integer	lastId;
	/** 段位描述::*/
	private String	duanDescribe;
	/** 段位分类::*/
	private Integer	classIfy;

	/** 编号::*/
	public Integer getId(){
		return this.id;
	}
	/** 段位名字::*/
	public String getDuanName(){
		return this.duanName;
	}
	/** 升级需要的星数::*/
	public Integer getStarNum(){
		return this.starNum;
	}
	/** 获得特殊奖励的连续次数::*/
	public Integer getSuccWin(){
		return this.succWin;
	}
	/** 特殊奖励星星数量::*/
	public Integer getSuccWinStar(){
		return this.succWinStar;
	}
	/** 升级下一个id::*/
	public Integer getNextId(){
		return this.nextId;
	}
	/** 段位分类::*/
	public Integer getDuanClassIfy(){
		return this.duanClassIfy;
	}
	/** 上一个段位id::*/
	public Integer getLastId(){
		return this.lastId;
	}
	/** 段位描述::*/
	public String getDuanDescribe(){
		return this.duanDescribe;
	}
	/** 段位分类::*/
	public Integer getClassIfy(){
		return this.classIfy;
	}
	/**编号::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**段位名字::*/
	public void setDuanName(String duanName){
		this.duanName = duanName;
	}
	/**升级需要的星数::*/
	public void setStarNum(Integer starNum){
		this.starNum = starNum;
	}
	/**获得特殊奖励的连续次数::*/
	public void setSuccWin(Integer succWin){
		this.succWin = succWin;
	}
	/**特殊奖励星星数量::*/
	public void setSuccWinStar(Integer succWinStar){
		this.succWinStar = succWinStar;
	}
	/**升级下一个id::*/
	public void setNextId(Integer nextId){
		this.nextId = nextId;
	}
	/**段位分类::*/
	public void setDuanClassIfy(Integer duanClassIfy){
		this.duanClassIfy = duanClassIfy;
	}
	/**上一个段位id::*/
	public void setLastId(Integer lastId){
		this.lastId = lastId;
	}
	/**段位描述::*/
	public void setDuanDescribe(String duanDescribe){
		this.duanDescribe = duanDescribe;
	}
	/**段位分类::*/
	public void setClassIfy(Integer classIfy){
		this.classIfy = classIfy;
	}
}