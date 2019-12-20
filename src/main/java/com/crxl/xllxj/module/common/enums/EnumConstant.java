package com.crxl.xllxj.module.common.enums;

/** 常量 */
public interface EnumConstant {
	/**
	 * 游戏结果 
	 *
	 */
	public enum GameResult{
		/** 0失败 */
		loss
		/** 1成功 */
		,win
	}
	/**
	 *回答结果   1对 。0错 
	 */
	public enum AnswerResult{
		loss,
		win;
	} 
	/**
	 * 房间状态
	 */
	public enum RoomState {
		/**
		 *初始化
		 */
		init,
		/**
		 * 准備中
		 */
		playVideo,
		/**
		 * 答题
		 */
		answer,
		/**
		 * 游戲結束
		 */
		finish;

	}

	/**
	 * 准备状态
	 *
	 */
	public enum ReadyState {
		none, ready;
	}

	/** 渠道号 */
	public enum ChannelId {
		test;
	}

	/** 序列号的类型 */
	public enum IdentityId {

		none(0, 0), user(1, 1000001), room(2, 100001);

		/**
		 * @param id
		 * @param initValue
		 */
		private IdentityId(int id, int initValue) {
			this.id = id;
			this.initValue = initValue;
		}

		int id;
		int initValue;

		public int getInitValue() {
			return initValue;
		}

		/**
		 * 获取类型
		 */
		public int getId() {
			return id;
		}
		
	}
	/**
	 * 任务类型
	 */
	public enum TaskType {
		none,
		/**赛季段位任务*/
		gameSeasonDanTask,
		/**赛季游戏场次任务*/
		gameSeasonBoutTask,
		/**累计胜场任务*/
		addUpWinsTask,
		/**累计MVP任务*/
		addUpMvpTask,
		/**每日任务*/
		everyDayTask,
		/**新手任务*/
		newPlayersTask;
	}
	
	/**
	 * 外观物品获取类型
	 */
	public enum AppEarance{
		/**无需任何需求直接送*/
		none,
		/**金币购买*/
		needCoin,
		/**达到指定需求*/
		assignAsk,
		/**达到指定需求在购买*/
		assignAskAndCoin
	}
	
	/**
	 * 物品类型
	 */
	public enum ItemType{
		none,
		/**宝箱*/
		cowryBox,
		/**头像框*/
		headFrame,
		/**称号*/
		title,
		/**语言包*/
		languageBag
	}

	
	/**
	 * 物品状态 
	 */
	public enum ItemState{
		/**未拥有状态*/
		none,
		/**可领取状态*/
		attainable,
		/**拥有状态*/
		ownState
	}
	
	/**
	 * 限制条件类型
	 */
	public enum RestrictType{
		/**没有限制*/
		none,
		/**段位限制*/
		duan,
		/**答对题限制*/
		correct,
		/**胜利限制*/
		win,
		/**排名限制*/
		ranKing,
		/**时间限制(节日)*/
		time;
		
		public static RestrictType find(String key) {
			for (RestrictType member: values()) {
				if(member.name().equals(key)) {
					return member;
				} 
			}
			return none;
		}
	}
	
	/**
	 * 任务分类
	 */
	public enum TaskClassIfy{
		none,
		/**赛季任务类型*/
		sportsTask,
		/**挑战任务类型*/
		challengeType,
		/**日常任务类型*/
		everyDayTask,
	} 
	
	/**
	 * 展示类型
	 */
	public enum ShowType{
		/**不需要折叠*/
		none,
	}
	
	/**
	 * 任务奖励领取状态
	 */
	public enum TaskAwardGetState{
		/**未领取*/
		unreceived,
		/**可领取*/
		canReceive,
		/**已领取*/
		received
		
	}
	
	 /**
     * 特殊物品
     */
    public enum SpecialItemType{
    	none,
    	/**赛季物品*/
    	areSportsSeasonItem,
    	/**节日礼品（时间）*/
    	holidayGift
    }
    
    /**
     * 消息红点范围
     */
    public enum RedDotMessageScope{
    	none,
    	/**外观*/
    	appEarance,
    	/**背包*/
    	bag,
    	/**任务*/
    	task;
    }
}
