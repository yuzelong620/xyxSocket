package com.crxl.xllxj.module.room.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.apache.log4j.Logger;

import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.GameParamsCache;
import com.crxl.xllxj.module.json.datacache.RobotCache;
import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.crxl.xllxj.module.room.manager.RoomManager;
import com.crxl.xllxj.module.room.service.RoomService;
import com.globalgame.auto.json.Robot_Json;

public class MatchingThread extends Thread{
	
	public MatchingThread() {
		super.setName(MatchingThread.class.getSimpleName());
	}
	Logger logger = Logger.getLogger(MatchingThread.class);
	
	protected ConcurrentLinkedDeque<MatchBean> queue = new ConcurrentLinkedDeque<>();
	protected volatile boolean open = true;// 关闭装填	 
	public boolean isOpen(){
		return open;
	}	 
	/**
	 * 删除匹配用户
	 * @param match
	 * @return
	 */
	public boolean remove(MatchBean match){
		return queue.remove(match);
	}
	/**
	 * 存入匹配用户
	 * @param match
	 */
	public void put(MatchBean match) {
		if(queue.contains(match)){//该任务在当前队列中
			return;
		}
		queue.offer(match);
	}
	
	final MatchBean closeTask=new MatchBean(null,0);
	
	public void close(){
	   this.open=false;
 	   queue.offer(closeTask);
	}	 
	@Override
	public void run() {
		final RoomService roomService=new RoomService();
		RobotCache robotCache=JsonCacheManager.getCache(RobotCache.class);
		int maxMatchTime=GameParamsCache.getGameParamsInstance().getMaxMatchTime()*1000;
		ArrayList<MatchBean> firstList=new ArrayList<>();
		ArrayList<MatchBean> secondList=new ArrayList<>();
		ArrayList<MatchBean> thirdList=new ArrayList<>();
		while (open) {
			try {
				MatchBean matchItem = queue.poll();
				int duanId = 0;
				if(matchItem == null){
					mate(roomService, robotCache, maxMatchTime, firstList, matchItem);
					mate(roomService, robotCache, maxMatchTime, secondList, matchItem);
					mate(roomService, robotCache, maxMatchTime, thirdList, matchItem);
				}
				if (matchItem != null){
					 duanId = matchItem.getPlayer().getCurrentDuanId();
				}
				if(duanId>=10101 && duanId<=10204){
					mate(roomService, robotCache, maxMatchTime, firstList, matchItem);
				}else if(duanId>=10301 && duanId<=10404){
					mate(roomService, robotCache, maxMatchTime, secondList, matchItem);
				}else{
					mate(roomService, robotCache, maxMatchTime, thirdList, matchItem);
				}
			}
			catch (Exception e) {
				logger.error("", e);
			} 
			catch (Throwable e){
				logger.error("", e);
			}
		}
	}
	
	public void mate(RoomService roomService, RobotCache robotCache, int maxMatchTime, ArrayList<MatchBean> list, MatchBean matchItem){
		if(matchItem==null){	
			if(list.size()>0){
				long sub=System.currentTimeMillis()-list.get(0).getCreateTime();
				if(sub>maxMatchTime){
					int size=6-list.size();
					Random ra = new Random();
					Set<Integer> set = new HashSet<Integer>();
					List<Robot_Json> robot_JsonList = robotCache.getList();
					while (set.size()<size) {
						int id = ra.nextInt(robot_JsonList.size());
						set.add(id);
					}
					Iterator<Integer> it = set.iterator();
					while(it.hasNext()) {
						Robot_Json json= robotCache.getList().get(it.next());
						PlayerEntity player=robotCache.getRobot(json.getId());
						MatchBean match=new MatchBean(player, System.currentTimeMillis(),true);
						list.add(match);
					}
					//创建房间 
					roomService.createRoom(new ArrayList<>(list));
					list.clear();
				}
			}
			try{
				Thread.sleep(50); 
			}
			catch(Exception e){						
			}
			return;
		}
		if(matchItem==closeTask){
			return;
		}
		if(list.contains(matchItem)){
			return;
		}				 
		if(RoomManager.getInstance().findByUserId(matchItem.getUserId())!=null){
			 logger.error("用户已经在房间中，无法匹配! userId:"+matchItem.getUserId());
			 return;
		}
		list.add(matchItem);
		//匹配创建房间
		if(list.size()==6){
			//创建房间 
			roomService.createRoom(list);
			list.clear();
			return;
		}
	
	}
		
}
