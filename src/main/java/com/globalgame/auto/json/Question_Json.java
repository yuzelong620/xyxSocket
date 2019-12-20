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
public class Question_Json{
	/** ID::*/
	private Integer	id;
	/** 视频ID::*/
	private Integer	videoId;
	/** 问题描述::*/
	private String	desc;

	/** ID::*/
	public Integer getId(){
		return this.id;
	}
	/** 视频ID::*/
	public Integer getVideoId(){
		return this.videoId;
	}
	/** 问题描述::*/
	public String getDesc(){
		return this.desc;
	}
	/**ID::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**视频ID::*/
	public void setVideoId(Integer videoId){
		this.videoId = videoId;
	}
	/**问题描述::*/
	public void setDesc(String desc){
		this.desc = desc;
	}
}