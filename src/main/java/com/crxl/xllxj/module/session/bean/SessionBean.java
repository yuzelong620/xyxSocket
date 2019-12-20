package com.crxl.xllxj.module.session.bean;

import java.util.Map;

import com.crxl.xllxj.module.bag.entity.BagEntity;
import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.crxl.xllxj.module.task.entity.TaskEntity;

import io.netty.channel.Channel;

/**
 * 会话
 */
public class SessionBean {  
	public SessionBean(long lastSettleLoginTime, String id, Channel channel, PlayerEntity playerEntity,BagEntity bagEntity, String ip,Map<Integer,TaskEntity> taskMap) {
	    this.lastSettleLoginTime = lastSettleLoginTime;
		this.id = id;
		this.channel = channel;
		this.playerEntity = playerEntity;
		this.bagEntity = bagEntity;
		this.ip = ip; 
		this.taskMap = taskMap;
	}

	public SessionBean() {
	}

	long lastSettleLoginTime;
	String id;
	Channel channel;
	PlayerEntity playerEntity;
	BagEntity bagEntity;
	String ip;
	Map<Integer,TaskEntity> taskMap;

	public String getId() {
		return id;
	}

	public long getLastSettleLoginTime() {
		return lastSettleLoginTime;
	}

	public void setLastSettleLoginTime(long lastSettleLoginTime) {
		this.lastSettleLoginTime = lastSettleLoginTime;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public PlayerEntity getPlayerEntity() {
		return playerEntity;
	}

	public void setPlayerEntity(PlayerEntity playerEntity) {
		this.playerEntity = playerEntity;
	}

	public BagEntity getBagEntity() {
		return bagEntity;
	}

	public void setBagEntity(BagEntity bagEntity) {
		this.bagEntity = bagEntity;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<Integer, TaskEntity> getTaskMap() {
		return taskMap;
	}

	public void setTaskMap(Map<Integer, TaskEntity> taskMap) {
		this.taskMap = taskMap;
	}
	
}
