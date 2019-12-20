package com.crxl.xllxj.module.room.bean;

import com.crxl.xllxj.module.player.entity.PlayerEntity;

/**
 * 匹配任务
 * @author zxd
 */
public class MatchBean {
	
    public MatchBean() { 
	}
	private PlayerEntity player;
    private long createTime;
    boolean isRobot=false;
    /**
     * 用于玩家匹配
     * @param player
     * @param createTime
     */
	public MatchBean(PlayerEntity player, long createTime) {
		this.player = player;
		this.createTime = createTime;
	}
	
	/**
	 * 用于机器人匹配
	 * @param player
	 * @param createTime
	 * @param isRobot
	 */
	public MatchBean(PlayerEntity player, long createTime,boolean isRobot) {
		this.player = player;
		this.createTime = createTime;
		this.isRobot=isRobot;
	}	
	public boolean isRobot() {
		return isRobot;
	}

	public void setRobot(boolean isRobot) {
		this.isRobot = isRobot;
	}

	public String getUserId() {
		return player.getUserId();
	}
	 
	public long getCreateTime() {
		return createTime;
	}
	//重写父类方法，为队列中查找
	@Override
	public int hashCode() { 
		return this.player.getUserId().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MatchBean)){
			return false;
		}
		MatchBean objTemp=(MatchBean)obj;
		return objTemp.player.getUserId()!=null&&objTemp.getUserId().equals(this.getUserId());
	}

	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}
    
}
