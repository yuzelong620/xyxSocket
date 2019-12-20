package com.crxl.xllxj.module.json.datacache;

import java.util.ArrayList;
import java.util.List;

import com.crxl.xllxj.module.json.JsonCache;
import com.globalgame.auto.json.Video_Json;

public class VideoCache extends JsonCache<Video_Json> {
	List<Video_Json> shortList;//短的视频集合
	List<Video_Json> otherList;//一般的正常的视频集合

	@Override
	protected void setDataCache(List<Video_Json> datas) {
		List<Video_Json> shortList=new ArrayList<>();
		List<Video_Json> otherList=new ArrayList<>();
		int shortTime=GameParamsCache.getGameParamsInstance().getShortVideoTime();//短视频秒
		for(Video_Json json:datas){
			if(json.getVideoTime()>shortTime){
				otherList.add(json);
			}
			else{
				shortList.add(json);
			}
		}
		this.otherList=otherList;
		this.shortList=shortList;
	}
	/**
	 * 获取其他一般视频
	 * @return
	 */
	public List<Video_Json> getOtherList(){
		return otherList;
	}
    /**
     * 获取短的视频集合
     * @return
     */
	public List<Video_Json> getShortList() {
		return shortList;
	}
	
}
