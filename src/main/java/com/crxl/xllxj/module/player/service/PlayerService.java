package com.crxl.xllxj.module.player.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.crxl.xllxj.module.bag.entity.BagEntity;
import com.crxl.xllxj.module.common.enums.EnumConstant;
import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.message.ResponseMsg;
import com.crxl.xllxj.module.core.net.CommandId;
import com.crxl.xllxj.module.core.net.MessageChannel;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.core.thread.TaskManager;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.AppEaranceCache;
import com.crxl.xllxj.module.json.datacache.ArticleCache;
import com.crxl.xllxj.module.json.datacache.DetecTiveCache;
import com.crxl.xllxj.module.json.datacache.DuanCache;
import com.crxl.xllxj.module.json.datacache.GameParamsCache;
import com.crxl.xllxj.module.json.datacache.RobotCache;
import com.crxl.xllxj.module.player.PropertyBean;
import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.crxl.xllxj.module.user.entity.UserEntity;
import com.crxl.xllxj.module.util.DateUtil;
import com.globalgame.auto.json.AppEarance_Json;
import com.globalgame.auto.json.Article_Json;
import com.globalgame.auto.json.DetecTive_Json;
import com.globalgame.auto.json.Duan_Json;
import com.globalgame.auto.json.Robot_Json;
import com.globalgame.auto.json.Task_Json;

@Service
public class PlayerService extends BaseService {
	public static org.slf4j.Logger loger = LoggerFactory.getLogger(BaseService.class);

	public PlayerEntity load(UserEntity user) {
		PlayerEntity player = playerDao.findByID(user.getId());
		if (player == null) {
			player = new PlayerEntity(user.getId());
			player.setNickName("user" + user.getIdentityId());
			player.setHeadPic(GameParamsCache.getGameParamsInstance().getUserPic1());
			playerDao.save(player);
		}
		return player;
	}

	public PlayerEntity load(String userId) {
		SessionBean session = sessionService.findByUserId(userId);
		if (session != null) {
			return session.getPlayerEntity();
		}
		return playerDao.findByID(userId);
	}

	/**
	 * 检查赛季
	 * 
	 * @param session
	 */
	public void checkGameSeason(SessionBean session) {
		try {
			PlayerEntity player = session.getPlayerEntity();
			int oldSeason = player.getGameSeason();
			int currentSeason = DateUtil.getCurrentDateMothInt();
			if (oldSeason != currentSeason) {// 赛季不同重置段位
				checkAward(session); // 检测奖励
				DuanCache cache = JsonCacheManager.getCache(DuanCache.class);
				player.setGameSeason(currentSeason);// 当前赛季
				player.setCurrentDuanId(cache.getMinId());
				player.setCurrentStarNum(0);
				TaskManager.instance.saveGroup.hashPut(player.getUserId().hashCode(),
						playerDao.getAsynSaveTask(player));
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 检测赛季奖励
	 */
	private void checkAward(SessionBean session) {
		AppEaranceCache appEaranceCache = JsonCacheManager.getCache(AppEaranceCache.class);
		List<AppEarance_Json> appEarance_JsonList = appEaranceCache.getList();
		BagEntity bagEntity = session.getBagEntity();
		Map<Integer, Integer> item = bagEntity.getItems();
		for (AppEarance_Json appEarance_Json : appEarance_JsonList) {
			if (appEarance_Json.getSpecialItemType() == EnumConstant.SpecialItemType.areSportsSeasonItem.ordinal()) {
				if (item.get(appEarance_Json.getId()) != null) { // 背包里面没有
					if (rewardService.checkLimit(session, appEarance_Json.getId(), appEarance_Json.getCondition())) {
						rewardService.addResource(session, appEarance_Json.getAddAward());
					}
				}
			}
		}
	}

	/**
	 * 检测佩戴
	 */
	public void checkAdorn(SessionBean session) {
		PlayerEntity player = session.getPlayerEntity();
		BagEntity bagEntity = session.getBagEntity();
		Map<Integer, Integer> map = bagEntity.getItems();
		List<Integer> languageBagList = new ArrayList<Integer>();
		ArticleCache articleCache = JsonCacheManager.getCache(ArticleCache.class);
		for (Integer key : map.keySet()) {
			Article_Json article_Json = articleCache.getData(key);
			if (player.getTitle() == 0) {
				if (article_Json.getArticleType().equals(EnumConstant.ItemType.title.ordinal())) { // 称号
					player.setTitle(key);
				}
			}
			if (player.getHeadRahmen() == 0) {
				if (article_Json.getArticleType().equals(EnumConstant.ItemType.headFrame.ordinal())) { // 头像框
					player.setHeadRahmen(key);
				}
			}
			if (player.getLanguageBag() == null || player.getLanguageBag().size() == 0) {
				if (article_Json.getArticleType().equals(EnumConstant.ItemType.languageBag.ordinal())) { // 语言包
					languageBagList.add(key);
				}
			}
		}
		if (player.getLanguageBag() == null || player.getLanguageBag().size() == 0) {
			player.setLanguageBag(languageBagList);
		}
		playerDao.save(player);
	}

	/**
	 * 检测侦探等级
	 */
	public void checkDetective(SessionBean session) {
		PlayerEntity playerEntity = session.getPlayerEntity();
		if (playerEntity.getDetectiveGrade() == 0) {
			playerEntity.setDetectiveGrade(1); // 侦探等级设为1
			playerDao.save(playerEntity);
		}
	}

	/**
	 * 机器人检测佩戴
	 */
	public void robotCheckAdorn(PlayerEntity player, int robotId) {
		RobotCache robotCache = JsonCacheManager.getCache(RobotCache.class);
		Robot_Json robot_Json = robotCache.getData(robotId);
		if (player.getTitle() == 0) {
			player.setTitle(robot_Json.getTitle());
		}
		if (player.getHeadRahmen() == 0) {
			player.setHeadRahmen(robot_Json.getHeadRahmen());
		}
		playerDao.save(player);
	}

	/**
	 * 检查是否升级段位 。
	 * 
	 * @param player
	 */
	public void checkPlayerUpgrade(PlayerEntity player) {
		DuanCache cache = JsonCacheManager.getCache(DuanCache.class);
		if (player.getCurrentDuanId() == cache.getMaxId()) {// 最大的段位不需要升级
			return;
		}
		Duan_Json json = cache.getData(player.getCurrentDuanId());
		if (json == null) {
			player.setCurrentDuanId(cache.getMinId());
		}
		json = cache.getData(player.getCurrentDuanId());
		if (player.getCurrentStarNum() < json.getStarNum()) {// 无法升级
			return;
		}
		// 升级段位
		player.setCurrentDuanId(json.getNextId());
		player.setCurrentStarNum(player.getCurrentStarNum() - json.getStarNum());
		SessionBean session = sessionService.findByUserId(player.getUserId());
		int ia = json.getNextId() / 100 % 10;
		int ib = json.getId() / 100 % 10;
		if (ia > ib) {
			taskService.updateTaskValue(session, 1, 1); // 任务接口 类型1 段位升级
		}
	}

	public void checkPlayerDowngrading(PlayerEntity player) {
		DuanCache cache = JsonCacheManager.getCache(DuanCache.class);
		if (player.getCurrentDuanId() == cache.getMinId()) {// 降到最小
			if (player.getCurrentStarNum() < 0) {
				player.setCurrentStarNum(0);
			}
			return;
		}
		if (player.getCurrentStarNum() >= 0) {// 不能降级
			return;
		}
		Duan_Json json = cache.getDowngrading(player.getCurrentDuanId());
		if (json == null) {
			return;
		}
		player.setCurrentDuanId(json.getId());
		player.setCurrentStarNum(json.getStarNum() + player.getCurrentStarNum());// 减掉上一个段位欠的星星
	}

	/**
	 * 发送属性
	 * 
	 * @param session
	 */
	public void sendPropertys(SessionBean session) {
		PropertyBean data = new PropertyBean(session.getPlayerEntity());
		ResponseMsg msg = ResponseMsg.createMessage(data, CommandId.property_send_info);
		MessageChannel.sendMessage(session.getChannel(), msg);
	}

	/**
	 * 段位排行榜
	 */
	public void scoreRanKingList(RequestMsg req) {
		int limit = 100;
		List<PlayerEntity> playerEntityList = playerDao.scoreRanKingList(limit);
		req.sendCurrentCommand(playerEntityList);
	}

	/**
	 * 人物养成
	 */
	public void detecTive(RequestMsg req) {
		DetecTiveCache detecTiveCache = JsonCacheManager.getCache(DetecTiveCache.class);
		SessionBean session = sessionService.getSession(req.getChannel());
		PlayerEntity player = session.getPlayerEntity();
		DetecTive_Json detecTive_Json = detecTiveCache.getData(player.getDetectiveGrade());
		DetecTive_Json nextDetecTive_Json = detecTiveCache.getData(detecTive_Json.getNextGrade());
		int currency = nextDetecTive_Json.getConsume().get(0).getValue(); // 获取限制
		if (player.getGoldCoin() >= currency) { // 判断金币
			if (detecTive_Json.getNextGrade() == 999) { // 最高限制
				req.sendInfoMessage("已经达到顶级");
				return;
			}
			if (player.getDetectiveGrade() == 8) { // 目前最高8级
				req.sendInfoMessage("敬请期待");
				return;
			}
			player.setGoldCoin(player.getGoldCoin() - currency);
			// 设置下一个等级
			player.setDetectiveGrade(detecTive_Json.getNextGrade());
			playerDao.save(player);
			req.sendCurrentCommand(detecTive_Json.getNextGrade());
			sendPropertys(session);
			taskService.updateTaskValue(session, 8, 1); // 任务接口 类型8 养成升级
			return;
		}
		req.sendInfoMessage("金币不足");
	}

	/**
	 * 节日礼品检测
	 */
	public void checkHolidayGift(SessionBean session) {
		AppEaranceCache appEaranceCache = JsonCacheManager.getCache(AppEaranceCache.class);
		List<AppEarance_Json> appEarance_JsonList = appEaranceCache.getList();
		BagEntity bagEntity = session.getBagEntity();
		Map<Integer, Integer> item = bagEntity.getItems();
		for (AppEarance_Json appEarance_Json : appEarance_JsonList) {
			// 找到节日物品(时间)
			if (appEarance_Json.getSpecialItemType() == EnumConstant.SpecialItemType.holidayGift.ordinal()) {
				if (item.get(appEarance_Json.getId()) == null) { // 背包里面没有
					if (rewardService.checkLimit(session, appEarance_Json.getId(), appEarance_Json.getCondition())) {
						rewardService.addResource(session, appEarance_Json.getAddAward());
					}
				}
			}
		}
	}
}
