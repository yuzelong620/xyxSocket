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
public class PlaySeason_Json{
	/** 编号::*/
	private Integer	id;
	/** 赛季日期::*/
	private Integer	playSeasonDate;

	/** 编号::*/
	public Integer getId(){
		return this.id;
	}
	/** 赛季日期::*/
	public Integer getPlaySeasonDate(){
		return this.playSeasonDate;
	}
	/**编号::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**赛季日期::*/
	public void setPlaySeasonDate(Integer playSeasonDate){
		this.playSeasonDate = playSeasonDate;
	}
}