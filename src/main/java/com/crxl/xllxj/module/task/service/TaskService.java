package com.crxl.xllxj.module.task.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.crxl.xllxj.module.common.enums.EnumConstant;
import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.message.ResponseMsg;
import com.crxl.xllxj.module.core.net.CommandId;
import com.crxl.xllxj.module.core.net.MessageChannel;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.TaskCache;
import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.crxl.xllxj.module.task.bean.ReqTaskUpdate;
import com.crxl.xllxj.module.task.bean.ResTask;
import com.crxl.xllxj.module.task.bean.TaskBean;
import com.crxl.xllxj.module.task.entity.TaskEntity;
import com.crxl.xllxj.module.util.DateUtil;
import com.globalgame.auto.json.Task_Json;

public class TaskService extends BaseService{
	
	public TaskEntity load(String userId,int taskId,int taskClassIfy,int taskType) {
		TaskEntity taskEntity = taskDao.findByID(userId + "_" + taskId);
		if(taskEntity == null) {
			long time = System.currentTimeMillis();
			taskEntity = new TaskEntity(userId + "_" + taskId,userId,taskId,0,
					taskClassIfy,taskType,0,time,false);
		}
		return taskEntity;
	}
	
	/**
	 * 任务列表
	 */
	public void everyDayTask(RequestMsg req) {
		Integer taskClassIfy = req.getBody(Integer.class);
		TaskCache taskCache = JsonCacheManager.getCache(TaskCache.class);
		SessionBean session = sessionService.getSession(req.getChannel());
		List<TaskEntity> taskEntityList = taskDao.findByIdAll(session.getId());
		List<Task_Json> task_JsonList =  taskCache.getList();
		List<ResTask> resTaskList = new ArrayList<ResTask>();
		List<ResTask> yetGetStateList = new ArrayList<ResTask>();
		Set<Integer> set = new HashSet<Integer>();
		for (Task_Json task_Json : task_JsonList) {
			if(task_Json.getTaskClassIfy() != taskClassIfy) {  //判断任务分类类型
				continue;
			}
			TaskEntity taskEntity = null;
			for (TaskEntity entity : taskEntityList) {
				if(task_Json.getId() == entity.getTaskId()) {
					taskEntity = entity;
					break;
				}
			}
			if(task_Json.getShowType() == EnumConstant.ShowType.none.ordinal() || //先判断是否不需要折叠 
					!set.contains(task_Json.getShowType())) { //在判断是否记录需要折叠的任务
				if(taskEntity != null) {
					if(taskEntity.getTaskClassIfy() == EnumConstant.TaskClassIfy.everyDayTask.ordinal()) {   //是否是每日任务
						taskEntity = examineDate(taskEntity); //检测是否是同一天
					} 
					if(taskEntity.getTaskClassIfy() == EnumConstant.TaskClassIfy.sportsTask.ordinal()) { //是否是赛季任务
						taskEntity = examineSports(taskEntity,session.getPlayerEntity()); //检测是否是同一赛季
					}
					if(task_Json.getShowType() != EnumConstant.ShowType.none.ordinal()) {
						if(taskEntity.getRewardState() == EnumConstant.TaskAwardGetState.received.ordinal()) {
							continue;
						}
						set.add(task_Json.getShowType()); //记录任务折叠类型
					}
					taskDao.save(taskEntity);
					if(taskEntity.getRewardState() == EnumConstant.TaskAwardGetState.received.ordinal()){ //已领取
						yetGetStateList.add(new ResTask(task_Json.getId(),taskEntity.getValue(),taskEntity.getRewardState()));
					} else {
						resTaskList.add(new ResTask(task_Json.getId(),taskEntity.getValue(),taskEntity.getRewardState()));
					}
					continue;
				} 
				set.add(task_Json.getShowType()); //记录任务折叠类型
				resTaskList.add(new ResTask(task_Json.getId(), 0, 0)); //库中没有的情况下
			}
		}
		Collections.sort(resTaskList, new Comparator<ResTask>() {

			@Override
			public int compare(ResTask o1, ResTask o2) {
				if(o1.getRewardState() < o2.getRewardState()) {
					return 1;
				} else if(o1.getRewardState() == o2.getRewardState()) {
					if(o1.getValue() < o2.getValue()) {
						return 1;
					} else if(o1.getValue() == o2.getValue()) {
						return 0;
					}
					return -1;
				}
				return -1;
			}
			
		});
		resTaskList.addAll(yetGetStateList);
		req.sendCurrentCommand(resTaskList);
	}
	
	/**
	 * 任务进度更新
	 */
	public void updateTaskValue(RequestMsg req) {
		ReqTaskUpdate reqTaskUpdate = req.getBody(ReqTaskUpdate.class);
		SessionBean session = sessionService.getSession(req.getChannel());
		updateTaskValue(session, reqTaskUpdate.getTaskType(), reqTaskUpdate.getValue());
	}
	
	/**
	 * 任务进度更新
	 */
	public void updateTaskValue(SessionBean session,int taskType,int value) {
		List<TaskBean> taskBeanList = new ArrayList<TaskBean>();
		TaskCache taskCache = JsonCacheManager.getCache(TaskCache.class);
		List<Task_Json> taskCacheList = taskCache.getList();
		Map<Integer,TaskEntity> taskMap = session.getTaskMap(); //获取session中的任务信息
		for (Task_Json task_Json : taskCacheList) {
			if(task_Json.getTaskType().equals(taskType)){
				TaskEntity taskEntity = load(session.getId(),task_Json.getId(),
						task_Json.getTaskClassIfy(),task_Json.getTaskType());
				if(taskEntity.getTaskClassIfy() == EnumConstant.TaskClassIfy.everyDayTask.ordinal()) {   //是否是每日任务
					taskEntity = examineDate(taskEntity); //检测是否是同一天
				} 
				if(taskEntity.getTaskClassIfy() == EnumConstant.TaskClassIfy.sportsTask.ordinal()) { //是否是赛季任务
					taskEntity = examineSports(taskEntity,session.getPlayerEntity()); //检测是否是同一赛季
				}
				taskEntity.setValue(taskEntity.getValue() + value);
				if(taskEntity.getValue() >= task_Json.getTotal()) {
					if(taskEntity.getRewardState() != EnumConstant.TaskAwardGetState.received.ordinal()){
						taskEntity.setRewardState(EnumConstant.TaskAwardGetState.canReceive.ordinal());
						if(!taskEntity.isMessageSendState()){ //没有发送过
							ResponseMsg responseMsg = ResponseMsg.createMessage(task_Json.getId(),CommandId.game_task_update); //游戏内提醒指定任务完成消息
							MessageChannel.sendMessage(session.getChannel(),responseMsg);
							redDotMessageService.recordRedDotMessage(session, EnumConstant.RedDotMessageScope.task.ordinal(), 
					    			taskEntity.getTaskId());  //记录任务 消息红点
							taskEntity.setMessageSendState(true);//设置已经发送过
						}
					}
				}
				taskDao.save(taskEntity);
				taskBeanList.add(new TaskBean(taskEntity.getTaskId(),taskEntity.getTaskClassIfy(),
						task_Json.getTaskType(),taskEntity.getValue()));
				taskMap.put(task_Json.getId(), taskEntity);
			}
		}
		session.setTaskMap(taskMap); //更新session中的任务信息
//		responseMsg = ResponseMsg.createMessage(taskBeanList,CommandId.task_update);
//		MessageChannel.sendMessage(session.getChannel(),responseMsg);  //发送消息
	}
	
	/**
	 * 领取奖励
	 */
	public void getAward(RequestMsg req) {
		Integer taskId = req.getBody(Integer.class);
		SessionBean session = sessionService.getSession(req.getChannel());
		TaskEntity taskEntity = taskDao.findByTaskIdData(session.getId(),taskId);
		TaskCache taskCache = JsonCacheManager.getCache(TaskCache.class);
		List<Task_Json> taskCacheList = taskCache.getList();
		for (Task_Json task_Json : taskCacheList) {
			if(taskEntity == null) {
				req.sendInfoMessage("未达成领取条件！");
				return;
			} 
			if(task_Json.getId().equals(taskEntity.getTaskId())) {
				if(taskEntity.getRewardState() == 2) {
					req.sendInfoMessage("已经领取过了");
					return;
				} 
				if(taskEntity.getValue() >= task_Json.getTotal()) {
					rewardService.addResource(session,task_Json.getRewardList()); //增加奖励
					taskEntity.setRewardState(EnumConstant.TaskAwardGetState.received.ordinal());
					taskDao.save(taskEntity);
					req.sendCurrentCommand(task_Json.getRewardList());
					//消除红点
					redDotMessageService.userReadRedDotMessage(session, EnumConstant.RedDotMessageScope.task.ordinal(), taskEntity.getTaskId());
					return;
				} 
				req.sendInfoMessage("未达成领取条件！");
				return;
			}
			
		}
	}
	
	/**
	 * 任务列表加载用户存储session
	 */
	public Map<Integer,TaskEntity> loadTaskList(String userId){
		List<TaskEntity> taskEntityList  = taskDao.findByUserIdAll(userId);
		Map<Integer,TaskEntity> taskMap = new HashMap<Integer, TaskEntity>();
		for (TaskEntity taskEntity : taskEntityList) {
			taskMap.put(taskEntity.getTaskId(), taskEntity);
		}
		return taskMap;
	}
	
	//检查是否是同一赛季
	private TaskEntity examineSports(TaskEntity taskEntity,PlayerEntity playerEntity) {
		int nowDate = playerEntity.getGameSeason();
		long oldDate = taskEntity.getNowDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		int disposeTime = Integer.valueOf(sdf.format(oldDate));
		//不是同一赛季
		if(!(nowDate == disposeTime)) {
			taskEntity.setValue(0);
			taskEntity.setRewardState(0);
			taskEntity.setNowDate(System.currentTimeMillis());
			taskEntity.setMessageSendState(false);
		}
		return taskEntity;
	}
	
	//检查日期
	private TaskEntity examineDate(TaskEntity taskEntity) {
		long nowDate = System.currentTimeMillis();
		long time = taskEntity.getNowDate();
		//不是同一天
		if(!DateUtil.isSameDate(nowDate, time)) {
			taskEntity.setValue(0);
			taskEntity.setRewardState(0);
			taskEntity.setNowDate(nowDate);
			taskEntity.setMessageSendState(false);
		}
		return taskEntity;
	}
}
