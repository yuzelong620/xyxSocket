package com.crxl.xllxj.module.room.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;
import com.crxl.xllxj.module.common.enums.EnumConstant;
import com.crxl.xllxj.module.common.enums.EnumConstant.IdentityId;
import com.crxl.xllxj.module.common.enums.EnumConstant.RoomState;
import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.message.ResponseMsg;
import com.crxl.xllxj.module.core.net.CommandId;
import com.crxl.xllxj.module.core.net.MessageChannel;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.core.thread.TaskManager;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.AnswerCache;
import com.crxl.xllxj.module.json.datacache.ArticleCache;
import com.crxl.xllxj.module.json.datacache.DuanCache;
import com.crxl.xllxj.module.json.datacache.GameParamsCache;
import com.crxl.xllxj.module.json.datacache.QuestionCache;
import com.crxl.xllxj.module.json.datacache.RobotCache;
import com.crxl.xllxj.module.json.datacache.VideoCache;
import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.crxl.xllxj.module.room.bean.AnswerInfoBean;
import com.crxl.xllxj.module.room.bean.ItemAnswerBean;
import com.crxl.xllxj.module.room.bean.MatchBean;
import com.crxl.xllxj.module.room.bean.NoticeAnswerInfoBean;
import com.crxl.xllxj.module.room.bean.PlayVideoBean;
import com.crxl.xllxj.module.room.bean.QuestionAnswerInfoBean;
import com.crxl.xllxj.module.room.bean.QuestionStartBean;
import com.crxl.xllxj.module.room.bean.ReConnetedInfo;
import com.crxl.xllxj.module.room.bean.RoomAnswerReq;
import com.crxl.xllxj.module.room.bean.RoomBean;
import com.crxl.xllxj.module.room.bean.RoomGameOverBean;
import com.crxl.xllxj.module.room.bean.RoomInfoRes;
import com.crxl.xllxj.module.room.bean.RoomItemBean;
import com.crxl.xllxj.module.room.bean.RoomPlayerChange;
import com.crxl.xllxj.module.room.bean.RoomSendMessageRes;
import com.crxl.xllxj.module.room.bean.VideoInfoBean;
import com.crxl.xllxj.module.room.entity.RoomEntity;
import com.crxl.xllxj.module.room.manager.RoomManager;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.crxl.xllxj.module.timer.event.MarkTask;
import com.crxl.xllxj.module.timer.manager.TimerManager;
import com.globalgame.auto.json.Answer_Json;
import com.globalgame.auto.json.Article_Json;
import com.globalgame.auto.json.Duan_Json;
import com.globalgame.auto.json.Question_Json;
import com.globalgame.auto.json.Robot_Json;
import com.globalgame.auto.json.Video_Json;

public class RoomService extends BaseService {
    /**
     * 取消匹配
     * @param req
     */
	public void cancelMatch(RequestMsg req){
    	SessionBean session=sessionService.getSession(req.getChannel()); 
    	MatchBean match=new MatchBean(session.getPlayerEntity(), 0);
    	boolean result=RoomManager.getInstance().cancelMatch(match);
    	JSONObject json=new JSONObject();
    	json.put("result", result);
    	req.sendCurrentCommand(json);
    }
	
	public void answerQuestion(RequestMsg req) {
		SessionBean session = sessionService.getSession(req.getChannel());
		RoomEntity room = RoomManager.getInstance().findByUserId(session.getId());
		if (room == null) {
			logger.error("您不在房间中。无法答题");
			return;
		}
		Runnable run=new Runnable() {			 
			public void run() {
				answer(req,room,session);
			}
			public int hashCode() {
				return room.getId().hashCode();
			}
		};
		TaskManager.instance.hashPutOtherTask(run);		
	}
	
	private void printLog(RoomEntity room){
		logger.error(" room.id:"+room.getId()+" /room.state:"+room.getState()+" /room.questionId:"+room.getCurrentQuestionId()+"/ ");
	}
	public void answer(RequestMsg req,RoomEntity room,SessionBean session) {
		String userId=session.getId();
		logger.error(" 回答问题");
		printLog(room);
		if(room.getState()!=RoomState.answer.ordinal()){
			logger.error("  状态错误。无法答题");
			return;
		}
		RoomAnswerReq body = req.getBody(RoomAnswerReq.class);
		if (body == null || body.getAnswerId() == -1) {
			logger.error("参数错误。无法答题");
			return;
		}
		Question_Json question = JsonCacheManager.getCache(QuestionCache.class).getData(body.getQuestionId());
		if (question == null) {
			req.sendErrorMessage("参数错误");
			return;
		}
		Answer_Json answerJson = JsonCacheManager.getCache(AnswerCache.class).getData(body.getAnswerId());
		if (answerJson == null) {
			logger.error("答案id不存在");
			return;
		}
		 
		if (!answerJson.getQuestionId().equals(question.getId())) {
			logger.error("答案和问题不匹配。");
			return;
		}
		if (room.getCurrentQuestionId() != question.getId()) {// 回答的题目id 和
																// 实际房间的题不一致
			logger.error("问题与房间的问题不匹配。");
			return;
		}
		if (RoomState.answer.ordinal() != room.getState()) {
			logger.info("不是答题状态无法答题。");
			return;
		}
		RoomItemBean item = room.getItems().get(userId);
		if (item == null) {
			req.sendErrorMessage("您不在此房间中");
			return;
		}
		if (item.getAnswerIds().containsKey(question.getId())) {
			logger.error("重复答，userId:" + item.getUserId() + "/answerId:" + answerJson.getId());
			return;
		}
		answer(room,question, answerJson, item);
	}

	private void answer(RoomEntity room,  Question_Json question, Answer_Json answerJson,
			RoomItemBean item) {
		long subTime = System.currentTimeMillis() - room.getCurrentQuestionStartTime();
		boolean isLeft=room.getLeft().contains(item);
	
		boolean result=answerJson.getIsCorrect() == 1;
		int addScore=0;
		if (result) {
			// 答对加分，
			addScore = calculateRewardScore(room,subTime,question);
			item.setGetScore(item.getGetScore() + addScore);
			PlayerEntity playerEntity = playerDao.findByID(item.getUserId());
			if(!item.isRobot()) { //不是机器人
				playerEntity.setCorrectCount(playerEntity.getCorrectCount() + 1); //增加答对题总数
				playerDao.save(playerEntity);
			} else {
				randomSendEmoticons(room,playerEntity.getUserId());
			}
			//当前题队友是否都答对了
			
		} 
		room.getCurrentPlayerAnswers().put(item.getUserId(), answerJson.getId());//记录用户回答的问题
		
		AnswerInfoBean bean=new AnswerInfoBean(answerJson.getId(),subTime,question.getId(),result,addScore);
		item.getAnswerIds().put(question.getId(), bean);// 记录 答题完成的时间差
		
		boolean flag = false; //默认右边
		List<RoomItemBean> leftRoomItemBean = room.getLeft();
		for (RoomItemBean roomItemBean : leftRoomItemBean) {
			if(roomItemBean.getUserId().equals(item.getUserId())) { //左边是否存在
				flag = true;
				break;
			}
		}
		if(flag) {
			List<String> list = new ArrayList<String>();
			for (RoomItemBean roomItemBean : leftRoomItemBean) {
				Map<Integer, AnswerInfoBean> map = roomItemBean.getAnswerIds();
				AnswerInfoBean answerInfoBean = map.get(answerJson.getId());
				if(answerInfoBean!=null) {
					if(answerInfoBean.isResult()) { //当前玩家答对了
						list.add(roomItemBean.getUserId()); //记录玩家
					}
				}
			}
			if(list.size() == 3 || list.size() == 0) { //全部都答对了 || 全部答错了
				for (String string : list) {
					if(item.isRobot()){ //是机器人
						randomSendEmoticons(room,string); //发送表情
					}
				}
			}
		}
		
		noticeChangeStage(room);//通知房间改变
		TaskManager.instance.putSaveTaskByHash(roomDao.getAsynSaveTask(room), room.getId().hashCode());
//		roomDao.save(room);// 保存数据
		 	 
		//通知 玩家 答题结果		 
		NoticeAnswerInfoBean data=new NoticeAnswerInfoBean(item.getUserId(), question.getId(), addScore, result, item.getGetScore(),isLeft);
		ResponseMsg obj=ResponseMsg.createMessage(data, CommandId.room_notice_answer_info);
		 
		noticeRoomItems(room, obj);
		//大家都选择完了
		if(room.getCurrentPlayerAnswers().size()>=room.getItems().size()){
			 //延迟下一阶段
			delayNextQuestion(room);
		}
	}
	 
	private int calculateRewardScore(RoomEntity room,long time,Question_Json question){
		int addBaseScore=GameParamsCache.getGameParamsInstance().getQuestionAddBaseScore();//答题添加的基础分数::
		int sumTime=GameParamsCache.getGameParamsInstance().getAnswerLimitTime()*1000;
		int addScore=GameParamsCache.getGameParamsInstance().getQuestionAddScore();
		double num=(sumTime-time)*1.0/sumTime*addScore;//剩余时间越多分越高
		long value=Math.round(num)+addBaseScore;//四舍五入
		if(room.getQuestionIds().isEmpty()){
			int leftScore=sumScore(room.getLeft());
			int rightScore=sumScore(room.getRight());
			if(leftScore==rightScore){
				value+=1;//加1让他永远不出现平局。
			}
		}
		return (int)value;
	}
    /**消息发给其他人*/
	void sendMsgToOther(RoomEntity room, ResponseMsg msg,String myUserId){
		for (RoomItemBean obj : room.getItems().values()) {
			if(myUserId.equals(obj.getUserId())){
				continue;//不给自己发
			}
			SessionBean otherSession = sessionService.findByUserId(obj.getUserId());
			if (otherSession == null) {
				continue;
			}			
			try {
				MessageChannel.sendMessage(otherSession.getChannel(), msg);
			} 
			catch (Exception e) {
				logger.error("", e);
			}
		}
	}
	/**
	 * 通知房间内的所有玩家消息
	 * 
	 * @param room
	 * @param msg
	 */
	private void noticeRoomItems(RoomEntity room, ResponseMsg msg) {
		for (RoomItemBean obj : room.getItems().values()) {
			if(obj.isRobot()){//机器人不发送
				continue;
			}
			SessionBean otherSession = sessionService.findByUserId(obj.getUserId());
			if (otherSession == null) {
				continue;
			}
			try {
				MessageChannel.sendMessage(otherSession.getChannel(), msg);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

 

	public void roomInfo(RequestMsg req) {
		SessionBean session = sessionService.getSession(req.getChannel());		
		RoomEntity room = RoomManager.getInstance().findByUserId(session.getId());
		if(room==null){
			RoomInfoRes res = new RoomInfoRes("",null);
			req.sendMessage(ResponseMsg.createMessage(res, req.getCommandId()));
			return;
		}
		int leftScore = sumScore(room.getLeft());
		int rightScore = sumScore(room.getRight());	
		Integer questionNumber=room.getQuestionNumber().get(room.getCurrentQuestionId());
		if(questionNumber==null){
			questionNumber=0;
		}
		RoomBean roomBean=new ReConnetedInfo(room, leftScore, rightScore,questionNumber);
		RoomInfoRes res = new RoomInfoRes(room.getId(),roomBean);
		req.sendMessage(ResponseMsg.createMessage(res, req.getCommandId()));
	}

	/**通知游戏信息*/
	private void noticeRoomInfo(RoomEntity room,int commandId) {
		if (room == null) {
			return;
		}
		for (RoomItemBean item : room.getItems().values()) {
			SessionBean session = sessionService.findByUserId(item.getUserId());
			if (session == null) {
				continue;
			}
			try {
				MessageChannel.sendMessage(session.getChannel(),
						ResponseMsg.createMessage(new RoomBean(room),commandId));
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}
 
	/** 通知阶段已经改变 */
	private void noticeChangeStage(RoomEntity room) {
		noticeRoomInfo(room, CommandId.room_notice_stage_change);
	}
	/**
	 * 通知游戏开始
	 * @param room
	 */
	private void noticeGameStart(RoomEntity room) {
		noticeRoomInfo(room, CommandId.room_notice_game_start);
	}

	// 下一题
	private void nextQuestion(RoomEntity room) {		
		if (room.getQuestionIds().isEmpty()) {
			answerOver(room);//通知问题结束
			Runnable run = new Runnable() {			 
				public void run() {
					gameFinishSettle(room);
				}
			};
			long limitTime = 1500; //限制时间
			TimerManager.instance.schedule(run, limitTime); //等待结束动漫加载完毕
			logger.info("游戏结束gameId:"+room.getId());
			printLog(room);
			return;
		}
		int lastIndex = 0;
		int nextQuestionId = room.getQuestionIds().remove(lastIndex);
		QuestionCache questCache = JsonCacheManager.getCache(QuestionCache.class);
		Question_Json nextQuestion = questCache.getData(nextQuestionId);
		int oldVideoId = room.getCurrentVideoId();
		// 设置当前的题
		room.setCurrentQuestionId(nextQuestionId);
		room.setCurrentQuestionStartTime(System.currentTimeMillis());
		room.setCurrentVideoId(nextQuestion.getVideoId());
		room.setCurrentPlayerAnswers(new HashMap<>());
		//

		if (oldVideoId != nextQuestion.getVideoId()) {			
			printLog(room);
			room.setState(RoomState.playVideo.ordinal());
			VideoCache videoCache = JsonCacheManager.getCache(VideoCache.class);
			Video_Json videoJson = videoCache.getData(nextQuestion.getVideoId());
			long delay=0;
			if(oldVideoId!=0){
			    answerOver(room);//通知问题结束
			    delay=GameParamsCache.getGameParamsInstance().getGameIntervalTime()*1000;
			}	
			
			MarkTask task=new MarkTask() {
				public String getMark() { 
					return room.getId();
				} 
				@Override
				public void execute() {
					long videoTime=videoJson.getVideoTime() * 1000;
					int videoLoadTime=GameParamsCache.getGameParamsInstance().getVideoLoadTime();
					videoTime+=videoLoadTime;
					
					long endTime=System.currentTimeMillis()+videoTime;
					PlayVideoBean data = new PlayVideoBean(videoJson,endTime);
					ResponseMsg res = ResponseMsg.createMessage(data, CommandId.room_notice_play_video);
					noticeRoomItems(room, res);
					logger.info("/播放視頻："+nextQuestion.getVideoId());
					// 延迟答题,先播放视频
					TimerManager.instance.scheduleMarkTask(getStartAnswerTask(room),videoTime);
				}
			};
			TimerManager.instance.schedule(task, delay);
			return;
		}
		
		answerOver(room);
		
		long delay=GameParamsCache.getGameParamsInstance().getGameIntervalTime()*1000;
		MarkTask task=new MarkTask(){
			public String getMark(){
				return room.getId();
			}
			public void execute(){
				room.setState(RoomState.answer.ordinal());
				noticeChangeStage(room);
				TaskManager.instance.putSaveTaskByHash(roomDao.getAsynSaveTask(room), room.getId().hashCode());
			}
		};
		TimerManager.instance.schedule(task, delay); 
	}
    /**一道题 结束*/
	private  void  answerOver(RoomEntity room){
		if (room == null) {
			return;
		}
		logger.info(" 结束答题 ");
		for (RoomItemBean item : room.getItems().values()) {
			SessionBean session = sessionService.findByUserId(item.getUserId());
			if (session == null) {
				continue;
			}
			try {
				MessageChannel.sendMessage(session.getChannel(),
						ResponseMsg.createMessage(CommandId.room_notice_answer_over));
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}
	private void settleItem(RoomItemBean myItem,boolean win,int myScore,int rivalScore,List<RoomItemBean> myItemInfo,
			List<RoomItemBean> rivalItemInfo,List<QuestionAnswerInfoBean> questionAnswers){
		if(myItem.isRobot()){
			return;
		}
		DuanCache duanCache=JsonCacheManager.getCache(DuanCache.class);
		SessionBean otherSession = sessionService.findByUserId(myItem.getUserId());
		if (otherSession == null) {
			return;
		}
		PlayerEntity player=otherSession.getPlayerEntity();
		int addStartNum=0;
		int continuousWinNum=0;//连胜次数
		int addCoin=getGetCoin(win, myItem.isMvp(), myScore, myItem.getGetScore());
		taskService.updateTaskValue(otherSession, 2, 1); //任务接口 类型2 累计游戏场次
		if(win){
			addStartNum=1;				
			//设置连胜
			continuousWinNum=player.getContinuousWinNum();
			Duan_Json json=duanCache.getData(myItem.getCurrentDuanId());
			if(json!=null&&continuousWinNum>=json.getSuccWin()){
				addStartNum=json.getSuccWinStar();//获得星星数量
			}
			continuousWinNum++;			 
			player.setContinuousWinNum(continuousWinNum);//连胜次数
			player.setCurrentStarNum(player.getCurrentStarNum()+addStartNum);//加星星数
			player.setScoreSun(player.getScoreSun() + 10); //段位积分增加10
			player.setWinCount(player.getWinCount()+1);//总胜利次数加1
			taskService.updateTaskValue(otherSession, 3, 1); //任务接口 类型3 累计获胜场次
			//检查升段
			playerService.checkPlayerUpgrade(player);
		}
		else{
			addStartNum=-1;
			player.setCurrentStarNum(player.getCurrentStarNum()-1);//失败星星数量-1
			if(player.getScoreSun() < 10){ //如果段位积分小于10
				player.setScoreSun(0); //段位积分设为0
			} else {
				player.setScoreSun(player.getScoreSun() - 10); //段位积分减10
			}
			player.setContinuousWinNum(0); //连胜重置
			player.setDefeatedNum(player.getDefeatedNum() + 1); //失败数 +1
			//检查降低段位
			playerService.checkPlayerDowngrading(player);
		}
		if(continuousWinNum == 5){ //连续5场胜利
			taskService.updateTaskValue(otherSession, 7, 1); //任务接口 类型7  连续5场胜利
		}
		player.setGoldCoin(player.getGoldCoin() + addCoin);
		player.setGamesCount(player.getGamesCount()+1);//游戏总场次
		TaskManager.instance.saveGroup.hashPut(player.getUserId().hashCode(), playerDao.getAsynSaveTask(player));
		myItem.setCurrentDuanId(player.getCurrentDuanId());
		myItem.setCurrentStarNum(player.getCurrentStarNum());
		RoomGameOverBean leftData = new RoomGameOverBean(myScore,  rivalScore,myItemInfo,rivalItemInfo,win,myItem.getGetScore(),
				myItem.getCurrentDuanId(),myItem.getCurrentStarNum(),addStartNum,continuousWinNum,questionAnswers,myItem.isMvp(),addCoin);
		ResponseMsg leftMsg = ResponseMsg.createMessage(leftData, CommandId.room_notice_game_over);
		try {
			MessageChannel.sendMessage(otherSession.getChannel(), leftMsg);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	
	private int  getGetCoin(boolean isWin,boolean isMVP,int sumScore,int myScore){
		if(sumScore==0||myScore==0){//如果玩家没有得分，或者本组没有得分 都不会得金币
			return 0;
		}
		double sum=50;//获得的总积分
		if(!isWin){
			sum=sum*0.6;//失败为胜利得分的  60%
		}
		Double add=1.0*myScore/sumScore*sum;//  获得积分币 乘以 总获得积分
		if(isMVP){
			add+=sum*0.1;//mvp额外10%
		}
		return add.intValue();
	}
	private void gameFinishSettle(RoomEntity room) {
		logger.info( "/游戏结束开始结算。");
		int leftScore = sumScore(room.getLeft());
		int rightScore = sumScore(room.getRight());
		boolean leftWin = leftScore > rightScore;
		VideoCache videoCache=JsonCacheManager.getCache(VideoCache.class);
		QuestionCache questionCache=JsonCacheManager.getCache(QuestionCache.class);
		AnswerCache answerCache=JsonCacheManager.getCache(AnswerCache.class);
		List<QuestionAnswerInfoBean> questionAnswers=new ArrayList<>();		
		for(Entry<Integer, Integer> entry:room.getQuestionNumber().entrySet()){
			int questionId=entry.getKey();
			int number=entry.getValue();
			Question_Json json=questionCache.getData(questionId);
			Video_Json videoJson=videoCache.getData(json.getVideoId());
			List<Answer_Json> answers=answerCache.getListByQuestionId(questionId);
			List<ItemAnswerBean> left=new ArrayList<>();
			for(RoomItemBean item:room.getLeft()){
				AnswerInfoBean answerBean=item.getAnswerIds().get(questionId);
				if(answerBean==null){
					continue;
				}
				left.add(new ItemAnswerBean(answerBean, item.getUserId() ));
			}
			List<ItemAnswerBean> right=new ArrayList<>();
			for(RoomItemBean item:room.getRight()){
				AnswerInfoBean answerBean=item.getAnswerIds().get(questionId);
				if(answerBean==null){//没有回答的跳过
					continue;
				}
				right.add(new ItemAnswerBean(answerBean, item.getUserId()));
			} 
			QuestionAnswerInfoBean questionInfo=new QuestionAnswerInfoBean(number, new VideoInfoBean(videoJson), left,right,json.getDesc(),answers);
			questionAnswers.add(questionInfo);
		}
		RoomItemBean mvp=null;
		List<RoomItemBean> roomItemBeanList = new ArrayList<RoomItemBean>();
		roomItemBeanList.addAll(room.getLeft()); //所有玩家信息
		roomItemBeanList.addAll(room.getRight());
		for (RoomItemBean roomItemBean : roomItemBeanList) {
			if(mvp == null){
				mvp = roomItemBean;
				continue;
			}
			if(roomItemBean.getGetScore() > mvp.getGetScore()){
				mvp = roomItemBean;
			}
		}
		for (RoomItemBean obj : room.getLeft()) {
			if(obj.equals(mvp)){
				obj.setMvp(true);
			}
		}
		for (RoomItemBean obj : room.getRight()) {
			if(obj.equals(mvp)){
				obj.setMvp(true);
			}
		}
		for (RoomItemBean obj : room.getLeft()) {
			settleItem(obj, leftWin, leftScore, rightScore, room.getLeft(),room.getRight(),questionAnswers);
		}
		for (RoomItemBean obj : room.getRight()) {
			settleItem(obj, !leftWin, leftScore, rightScore, room.getLeft(),room.getRight(),questionAnswers);
		}
		if(!mvp.isRobot()){ //不是机器人
			SessionBean session = sessionService.findByUserId(mvp.getUserId());
			taskService.updateTaskValue(session, 4, 1); //任务接口 类型4 累计游戏mvp
		}
		//删除 用户房间关联信息
		RoomManager.getInstance().delRoom(room);
	}

	public int sumScore(List<RoomItemBean> list) {
		int score = 0;
		for (RoomItemBean item : list) {
			score += item.getGetScore();
		}
		return score;
	}

	private MarkTask getStartAnswerTask(final RoomEntity room) {
		MarkTask run = new MarkTask() {
			public void execute() {
				questionStart(room);
			}

			public String getMark() {
				return room.getId();
			}
			@Override
			public String toString() {
				return "房间："+room.getId()+"延迟答题。";
			}
		};
		return run;
	}

	private void questionStart(final RoomEntity room) {		
		QuestionCache questCache = JsonCacheManager.getCache(QuestionCache.class);
		final Question_Json json = questCache.getData(room.getCurrentQuestionId());
		AnswerCache answerCache = JsonCacheManager.getCache(AnswerCache.class);
		 
		List<Answer_Json> list = answerCache.getListByQuestionId(json.getId());
		
		logger.info( ",開始第"+room.getQuestionNumber().get(json.getId())+"題,题号："+json.getId());
		printLog(room);
		// 修改房间状态
		room.setCurrentQuestionStartTime(System.currentTimeMillis());
		room.setState(RoomState.answer.ordinal());
		TaskManager.instance.putSaveTaskByHash(roomDao.getAsynSaveTask(room), room.getId().hashCode()); 
		
		noticeChangeStage(room);// 通知改变房间
		
		QuestionStartBean data = new QuestionStartBean(json.getId(), json.getDesc(), list);
		ResponseMsg msg = ResponseMsg.createMessage(data, CommandId.room_start_questioin);
		noticeRoomItems(room, msg);// 通知答题开始 
		// 10秒后 进入下一题
		MarkTask task = new MarkTask() {
			public void execute() { 
				nextQuestion(room);//开始下一题 
			}
			public String getMark() {
				return room.getId();
			}
			@Override
			public String toString() {
				return "房间："+room.getId()+"延迟进入下一题任务。";
			}
		};
		long limitTime=GameParamsCache.getGameParamsInstance().getAnswerLimitTime()*1000;
		TimerManager.instance.scheduleMarkTask(task,limitTime);
		
		//让机器人开始答题
		int min=GameParamsCache.getGameParamsInstance().getAnswerLimitTime()-2;
		Random random=new Random();
		List<Answer_Json> answerIds=answerCache.getListByQuestionId(json.getId());
		for(RoomItemBean item:room.getItems().values()){
			if(!item.isRobot()){
				continue;
			}
			int m=random.nextInt(min)+1;//延迟回答的秒数
			long delay=m*1000; 
			Runnable run=new Runnable() {			 
				public void run() {
					Answer_Json answerJson=answerIds.get(random.nextInt(answerIds.size()));
//					logger.info( item.isRobot()+item.getUserId()+" 答题。题号："+json.getId()+"/答案："+answerJson.getId()+" /"+(answerJson.getIsCorrect()==1));
					answer(room, json, answerJson, item);
				}
				public int hashCode() {
					return room.getId().hashCode();
				}
			};
			TimerManager.instance.schedule(run, delay);
		}
	}
    /**随机N个视频出来
     * @param matchBeans */
	private List<Integer> randomQuestion(ArrayList<MatchBean> matchBeans){
		//随机视频
		VideoCache videoCache=JsonCacheManager.getCache(VideoCache.class);
		
		int minGamesCount=GameParamsCache.getGameParamsInstance().getShortVideoGamesCount();
		boolean isMinList=false;
		for(MatchBean bean:matchBeans){
			SessionBean session=sessionService.findByUserId(bean.getUserId());
			if(session==null){
				continue;
			}
			if(session.getPlayerEntity().getGamesCount()<minGamesCount){
				isMinList=true;
				break;
			}
		}
		List<Video_Json> videos=null;; 
		if(isMinList){
			videos=new ArrayList<>(videoCache.getShortList());
		}
		else{
			videos=new ArrayList<>(videoCache.getOtherList());
		}
		
		int videoNum=GameParamsCache.getGameParamsInstance().getRoomVideoNum(); 
		ArrayList<Video_Json> videoList=new ArrayList<>();
		if(videoNum>=videos.size()){
			videoList.addAll(videos);
		}
		else{
			Random random=new Random();
			while(true){
				if(videoList.size()>=videoNum){
					break;
				}
				int index=random.nextInt(videos.size());
				Video_Json json=videos.get(index);
				if(videoList.contains(json)){
					continue;
				}
				videoList.add(json);
			}
		}
		//找出跟这些视频相关的 问题
		ArrayList<Integer> questionIds=new ArrayList<>();
		QuestionCache questionCache=JsonCacheManager.getCache(QuestionCache.class);
		for(Video_Json json:videoList){
			List<Question_Json> questioins=questionCache.findQuestioinIdByVideoId(json.getId());
			if(questioins==null||questioins.isEmpty()){
				continue;
			}
			for(Question_Json quest:questioins){
				questionIds.add(quest.getId());
			}
		}
		logger.info("-----------随机出题号："+JSONObject.toJSONString(questionIds));
		return questionIds;
	}
	
	public void createRoom(ArrayList<MatchBean> matchBeans) {
		String id = identityDao.nextId(IdentityId.room) + "";
		List<Integer> questionIds = randomQuestion(matchBeans);// 所有题id
        
		Map<String, RoomItemBean> items = new HashMap<>();
		List<RoomItemBean> left = new ArrayList<RoomItemBean>();
		List<RoomItemBean> right = new ArrayList<RoomItemBean>();
		int playerCurrentDuanId = -1; //真人的段位ID
		for (MatchBean matchBean : matchBeans) {
			if(!matchBean.isRobot()){ //找到真人
				PlayerEntity player = playerService.load(matchBean.getUserId());
				playerCurrentDuanId = player.getCurrentDuanId(); //记录真实玩家的段位信息
				break;
			}
		}
		//分组
		int len = matchBeans.size();
		Random ra = new Random();
		DuanCache cache=JsonCacheManager.getCache(DuanCache.class);
		for (int i = 0; i < len; i++) {
			MatchBean bean = matchBeans.get(i);
			PlayerEntity player = playerService.load(bean.getUserId());
			int duan = player.getCurrentDuanId(); //记录段位信息
			int starNum = player.getCurrentStarNum();//记录星星数
			if(bean.isRobot()){//是机器人
				playerService.robotCheckAdorn(player,i); //检测机器人检测佩戴
				if(playerCurrentDuanId != 0){ //真人不是荣耀之星段位（不是最高段位）
					Duan_Json json=cache.getData(playerCurrentDuanId);
					if(ra.nextInt(2) == 1){ //随机设置机器人段位
						duan = json.getNextId(); //对比真人设置 下一个段位
					} else {
						duan = json.getLastId(); //对比真人设置 上一个段位
					}
					starNum = 0; //永远保持机器人0星
				}
			}
			RoomItemBean item = new RoomItemBean(player,bean.isRobot(),duan,starNum,player.getTitle(),player.getHeadRahmen());
			if (i % 2 == 0) {
				left.add(item);
			} else {
				right.add(item);
			}
			items.put(item.getUserId(), item);
		}
		Map<Integer,Integer> questionNumber=new HashMap<>();
		int number=1;
		for(Integer questionId:questionIds){
			questionNumber.put(questionId, number);
			number++;
		}
		RoomEntity room = new RoomEntity(id, System.currentTimeMillis(), RoomState.init.ordinal(), left, right, items,questionIds,questionNumber);
		RoomManager.getInstance().addRoom(room);
		TaskManager.instance.saveGroup.hashPut(roomDao.getAsynSaveTask(room)); 
		//发送  房间信息 
		noticeGameStart(room);//通知游戏开始
		logger.info("游戏开始："+room.getId());
		delayNextQuestion(room);//开始下一题
	}
	
    private void delayNextQuestion(RoomEntity room){
    	MarkTask run =new MarkTask(){
			public String getMark() { 
				return room.getId();
			}
			public void execute() {
				nextQuestion(room);//开始下一题 
			}
		};
		long gameIntervalTime=GameParamsCache.getGameParamsInstance().getGameIntervalTime()*1000;//游戏间隔时间
    	TimerManager.instance.scheduleMarkTask(run,gameIntervalTime);
    }
	public void matching(RequestMsg req) {
		SessionBean session = sessionService.getSession(req.getChannel());
		if(RoomManager.getInstance().findByUserId(session.getId())!=null){
			req.sendInfoMessage("您已经在房间中！");
			return;
		}
		MatchBean item = new MatchBean(session.getPlayerEntity(), System.currentTimeMillis());
		RoomManager.getInstance().matching(item);
		req.sendCurrentCommand();
	}
//	/**
//     * 获取所有表情
//     * @param req
//     */
//	public void getEmoticons(RequestMsg req) { 
//		EmoticonsCache cache=JsonCacheManager.getCache(EmoticonsCache.class);
//		req.sendCurrentCommand(cache.getList());
//	}
	/**
	 *发送表情
	 */
	public void sendEmoticons(RequestMsg req) {
		Integer emoticonsId = req.getBody(Integer.class);
		if (emoticonsId== null) {
			req.sendMessage(ResponseMsg.createInfoMessage("参数错误"));
			return;
		}
		SessionBean session=sessionService.getSession(req.getChannel());
		PlayerEntity player=session.getPlayerEntity();
		if(!player.getLanguageBag().contains(emoticonsId)){
			req.sendErrorMessage("没有带这个表情");
			return;
		}
		ArticleCache cache=JsonCacheManager.getCache(ArticleCache.class);
		Article_Json json=cache.getData(emoticonsId);
		if(json==null){
			req.sendMessage(ResponseMsg.createInfoMessage("参数错误"));
			return;
		}
		RoomEntity room = RoomManager.getInstance().findByUserId(session.getId());
		if (room == null) {
			req.sendMessage(ResponseMsg.createInfoMessage("您不在房间中。"));
			return;
		}
		// 消息发给对方
		RoomSendMessageRes data = new RoomSendMessageRes(session.getId(), json.getArticleName());
		ResponseMsg msg=ResponseMsg.createMessage(data,CommandId.emoticons_send);
		// 返回发送成功
		noticeRoomItems(room, msg);
		taskService.updateTaskValue(session, 5, 1); //任务接口 类型5  发送消息任务
	}
	
	/**
	 *  取消连接
	 */
	public void cancelConnectRoom(RequestMsg req) {
		SessionBean session = sessionService.getSession(req.getChannel());
		RoomEntity room = RoomManager.getInstance().findByUserId(session.getId());
		if (room == null) {
			req.sendMessage(ResponseMsg.createInfoMessage("游戏已经结束"));
			return;
		}
		RoomItemBean roomItemBean = room.getItems().get(session.getId());
		if(roomItemBean == null) {
			req.sendMessage(ResponseMsg.createInfoMessage("您不在房间中。"));
			return;
		}
		RoomManager.getInstance().delUser(session.getId());
		roomItemBean.setRobot(true); //设为机器人
		PlayerEntity player = session.getPlayerEntity();
		player.setCurrentStarNum(player.getCurrentStarNum()-1);//星星数量-1
		if(player.getScoreSun() < 10) {
			player.setScoreSun(0); //段位积分设为0
		} else {
			player.setScoreSun(player.getScoreSun() - 10); //段位积分减10
		}
		player.setContinuousWinNum(0);
		//检查降低段位
		playerService.checkPlayerDowngrading(player);
		playerDao.save(player);
		List<RoomItemBean> roomItemBeanList = new ArrayList<RoomItemBean>();
		List<RoomItemBean> left = room.getLeft();  //右方玩家
		List<RoomItemBean> right = room.getRight();  //左方玩家
		roomItemBeanList.addAll(left);
		roomItemBeanList.addAll(right);
		RobotCache robotCache = JsonCacheManager.getCache(RobotCache.class);
		List<Robot_Json> robot_JsonList = robotCache.getList();
		Map<String, RoomItemBean> map = room.getItems();
		Random ra = new Random();
		boolean flag = true;
		boolean flsg = true;
		String newId = "";
		String oldId = player.getUserId();
		for (RoomItemBean roomAllPlayer : roomItemBeanList) {
			if(player.getUserId().equals(roomAllPlayer.getUserId())){ //找到取消连接的玩家
				if(flsg){
					for (RoomItemBean roomItem : roomItemBeanList) {
						String robotId = String.valueOf(ra.nextInt(robot_JsonList.size()));
						newId = robotId;
						if(roomItem.getUserId().equals(robotId)){
							continue;
						}
						RoomItemBean roomBean = map.get(player.getUserId());
						map.remove(player.getUserId());
						map.put(robotId, roomBean);
						roomAllPlayer.setUserId(robotId);
						flsg = false;
						break;
					}
				}
			}
			if(roomAllPlayer.isRobot()){
				continue;
			}
			flag = false;
		}
		if(flag){ //全部都是机器人
			RoomManager.getInstance().delRoom(room);
		} else {
			RoomPlayerChange roomPlayerChange = new RoomPlayerChange(oldId,newId);
			for (RoomItemBean roomAllPlayer : roomItemBeanList) {
				if(roomAllPlayer.isRobot()){
					continue;
				}
				//通知房间所有真人有玩家退出被设置为机器人
				ResponseMsg responseMsg = ResponseMsg.createMessage(roomPlayerChange,CommandId.room_cancel_connect_room);
				SessionBean otherSession = sessionService.findByUserId(roomAllPlayer.getUserId());
				MessageChannel.sendMessage(otherSession.getChannel(),responseMsg);  //发送消息
			}
		}
		req.sendCurrentCommand(player);
	}
	
	
	/**
	 * 机器人随机发送表情
	 */
	public void randomSendEmoticons(RoomEntity room,String playerId) {
		Random ra = new Random();
		int ia = ra.nextInt(2);
		if(ia == 1) { //发送表情
			ArticleCache articleCache = JsonCacheManager.getCache(ArticleCache.class);
			List<Article_Json> article_JsonList = articleCache.getListByArticleType(EnumConstant.ItemType.languageBag.ordinal());
			// 消息发给对方
			RoomSendMessageRes data = new RoomSendMessageRes(playerId, article_JsonList.get(ra.nextInt(article_JsonList.size())).getArticleName());
			ResponseMsg msg=ResponseMsg.createMessage(data,CommandId.emoticons_send);
			// 返回发送成功
			try {
				for (RoomItemBean item : room.getItems().values()) {
					SessionBean session = sessionService.findByUserId(item.getUserId());
					if (session == null) {
						continue;
					}
					
					long limitTime = ra.nextInt(5) * 1000 + 2000;
					Runnable run = new Runnable() {			 
						public void run() {
							SessionBean otherSession = sessionService.findByUserId(item.getUserId());
							MessageChannel.sendMessage(otherSession.getChannel(), msg);
						}
					};
					TimerManager.instance.schedule(run, limitTime);
				}
				
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

}
