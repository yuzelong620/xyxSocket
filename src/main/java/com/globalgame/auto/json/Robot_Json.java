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
public class Robot_Json{
	/** 机器人id::*/
	private Integer	id;
	/** 名字::*/
	private String	nickName;
	/** 性别::*/
	private Integer	gender;
	/** 签名::*/
	private String	signature;
	/** 头像::*/
	private String	headPic;
	/** 头像框::*/
	private Integer	title;
	/** 称号::*/
	private Integer	headRahmen;

	/** 机器人id::*/
	public Integer getId(){
		return this.id;
	}
	/** 名字::*/
	public String getNickName(){
		return this.nickName;
	}
	/** 性别::*/
	public Integer getGender(){
		return this.gender;
	}
	/** 签名::*/
	public String getSignature(){
		return this.signature;
	}
	/** 头像::*/
	public String getHeadPic(){
		return this.headPic;
	}
	/** 头像框::*/
	public Integer getTitle(){
		return this.title;
	}
	/** 称号::*/
	public Integer getHeadRahmen(){
		return this.headRahmen;
	}
	/**机器人id::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**名字::*/
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	/**性别::*/
	public void setGender(Integer gender){
		this.gender = gender;
	}
	/**签名::*/
	public void setSignature(String signature){
		this.signature = signature;
	}
	/**头像::*/
	public void setHeadPic(String headPic){
		this.headPic = headPic;
	}
	/**头像框::*/
	public void setTitle(Integer title){
		this.title = title;
	}
	/**称号::*/
	public void setHeadRahmen(Integer headRahmen){
		this.headRahmen = headRahmen;
	}
}