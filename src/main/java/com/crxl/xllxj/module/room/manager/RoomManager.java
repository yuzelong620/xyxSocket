package com.crxl.xllxj.module.room.manager;

import java.util.HashMap;
import com.crxl.xllxj.module.core.service.BaseService; 
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.RobotCache;
import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.crxl.xllxj.module.room.bean.MatchBean;
import com.crxl.xllxj.module.room.bean.MatchingThread;
import com.crxl.xllxj.module.room.bean.RoomItemBean;
import com.crxl.xllxj.module.room.entity.RoomEntity;
import com.globalgame.auto.json.Robot_Json;

public class RoomManager extends BaseService {

	static RoomManager instance = new RoomManager();

	public static RoomManager getInstance() {
		return instance;
	}

	RoomManager() {
	}
    final  MatchingThread thread=new MatchingThread();
    public boolean cancelMatch(MatchBean task){
    	return thread.remove(task);
    }
    
    public void matching(MatchBean task){
    	thread.put(task);
    }
	public RoomEntity findByUserId(String userId) {
		return userIdMap.get(userId);
	}

	public RoomEntity findByRoomId(String roomId) {
		return roomIdMap.get(roomId);
	}

	public boolean addRoom(RoomEntity room) {
		if (roomIdMap.containsKey(room.getId())) {// id错误
			return false;
		}		 
		roomIdMap.put(room.getId(), room);
		for(RoomItemBean obj:room.getItems().values()){
			userIdMap.put(obj.getUserId(),room);
		}		
		return true;
	}

	public boolean delRoom(RoomEntity room) {
		room = roomIdMap.remove(room.getId());// id错误
		if (room == null) {
			return false;
		}
		for (String userId : room.getItems().keySet()) {
			userIdMap.remove(userId);
		}
		return true;
	}

	// 初始化
	public void init() {
		//检查机器人是否都存在
	   RobotCache robotCache= JsonCacheManager.getCache(RobotCache.class); 
	   for(Robot_Json obj:robotCache.getList()){
		   PlayerEntity p=new PlayerEntity();
		   p.setGender(obj.getGender());
		   p.setUserId(obj.getId()+"");
		   p.setHeadPic(obj.getHeadPic());
		   p.setNickName(obj.getNickName());
		   p.setSignature(obj.getSignature());
		   playerDao.save(p);
	   }
	   //启动线程
	   thread.start();
	}
	// 映射核心
	HashMap<String, RoomEntity> userIdMap = new HashMap<>();
	HashMap<String, RoomEntity> roomIdMap = new HashMap<>();

	public void intoRoom(RoomEntity room, String userId) {
		userIdMap.put(userId, room);
	}

	public void delUser(String userId) {
		userIdMap.remove(userId);
	}
    /**
     * 当服务器关闭时，等待房间关闭！
     */
	public void onCloseServer() {
		 while(true){
			int size=roomIdMap.size();
			if(size<1){
				break;
			}
			try {
				logger.info("服务器等待房间全部关闭！当前房间数量:"+size);
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
			}
		 }
	}
}
