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
public class Video_Json{
	/** ID::*/
	private Integer	id;
	/** 视频类型::*/
	private Integer	type;
	/** 难度::*/
	private Integer	hardGrade;
	/**  封面::*/
	private String	videoPicUrl;
	/** 视频地址::*/
	private String	videoUrl;
	/** 视频时间（秒）::*/
	private Integer	videoTime;
	/** 下半段视频封面::*/
	private String	lastVideoPicUrl;
	/** 下半段视频地址::*/
	private String	lastVideoUrl;

	/** ID::*/
	public Integer getId(){
		return this.id;
	}
	/** 视频类型::*/
	public Integer getType(){
		return this.type;
	}
	/** 难度::*/
	public Integer getHardGrade(){
		return this.hardGrade;
	}
	/**  封面::*/
	public String getVideoPicUrl(){
		return this.videoPicUrl;
	}
	/** 视频地址::*/
	public String getVideoUrl(){
		return this.videoUrl;
	}
	/** 视频时间（秒）::*/
	public Integer getVideoTime(){
		return this.videoTime;
	}
	/** 下半段视频封面::*/
	public String getLastVideoPicUrl(){
		return this.lastVideoPicUrl;
	}
	/** 下半段视频地址::*/
	public String getLastVideoUrl(){
		return this.lastVideoUrl;
	}
	/**ID::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**视频类型::*/
	public void setType(Integer type){
		this.type = type;
	}
	/**难度::*/
	public void setHardGrade(Integer hardGrade){
		this.hardGrade = hardGrade;
	}
	/** 封面::*/
	public void setVideoPicUrl(String videoPicUrl){
		this.videoPicUrl = videoPicUrl;
	}
	/**视频地址::*/
	public void setVideoUrl(String videoUrl){
		this.videoUrl = videoUrl;
	}
	/**视频时间（秒）::*/
	public void setVideoTime(Integer videoTime){
		this.videoTime = videoTime;
	}
	/**下半段视频封面::*/
	public void setLastVideoPicUrl(String lastVideoPicUrl){
		this.lastVideoPicUrl = lastVideoPicUrl;
	}
	/**下半段视频地址::*/
	public void setLastVideoUrl(String lastVideoUrl){
		this.lastVideoUrl = lastVideoUrl;
	}
}