package com.crxl.xllxj.module.task.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.xlcr.mongo.dao.BaseDao;

import com.crxl.xllxj.module.task.entity.TaskEntity;

public class TaskDao extends BaseDao<TaskEntity>{
	
	private static final String USERID = "userId";
	private static final String TASKID = "taskId";
	private static final String TASKTYPE = "taskType";
	
	
	/**
	 * 根据玩家ID和所属类型查询所有任务信息
	 */
	public List<TaskEntity> findByIdAll(String userId) {
		Criteria criteria = new Criteria(USERID).is(userId);
		Query query = new Query(criteria);
		return super.find(query);
	}
	
	/**
	 * 根据userId查询此玩家所有任务信息
	 */
	public List<TaskEntity> findByUserIdAll(String userId) {
		Criteria criteria = new Criteria(USERID).is(userId);
		Query query = new Query(criteria);
		return super.find(query);
	}
	/**
	 * 根据taskId查询任务信息
	 */
	public TaskEntity findByTaskIdData(String userId,int taskId) {
		Criteria criteria = new Criteria(USERID).is(userId).and(TASKID).is(taskId);
		Query query = new Query(criteria);
		return super.findOne(query);
	}
	
	/**
	 * 根据任务类型和玩家ID查询所有任务
	 */
	public List<TaskEntity> findByTaskTypeAndUserIdData(String userId,int taskType) {
		Criteria criteria = new Criteria(USERID).is(userId).and(TASKTYPE).is(taskType);
		Query query = new Query(criteria);
		return super.find(query);
	}
}
