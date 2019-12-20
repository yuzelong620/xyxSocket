package com.crxl.xllxj.module.session.manager;
 
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import com.crxl.xllxj.module.core.service.BaseService; 
import com.crxl.xllxj.module.session.bean.SessionBean;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

public class SessionManager extends BaseService{
	
	public static final SessionManager instance = new SessionManager();

	SessionManager() {
	}
	private static final AttributeKey<SessionBean> channelSession = AttributeKey.valueOf("channelSession"); 
	
	private static final ConcurrentHashMap<String,SessionBean> sessionMap=new ConcurrentHashMap<String, SessionBean>();//userId<>session

	public SessionBean getSession(Channel channl) {
		return channl.attr(channelSession).get();
	}
	public void setSession(Channel channl, SessionBean session ){
		SessionBean oldLocal=channl.attr(channelSession).get();
		if(oldLocal!=null){
		    if(oldLocal.getId().equals(session.getId())){//重复登录
		    	return;
		    }
		    sessionMap.remove(oldLocal.getId(),oldLocal);//删除上一个登录的用户信息
		}
		SessionBean old=sessionMap.putIfAbsent(session.getId(),session);
		if(old!=null){
			try{
			   old.getChannel().close();//顶掉另一端登录
			}
			catch(Exception e){
				logger.error("关闭channel 异常：",e);
			}
		} 
		channl.attr(channelSession).set(session); 
	}
 
	
	public void onCloseChannel(Channel channel){
		SessionBean sessionBean=channel.attr(channelSession).get();
		if(sessionBean==null){
			return;
		}
		channel.attr(channelSession).remove();//删除管道缓存
		//删除key
		sessionMap.remove(sessionBean.getId(),sessionBean);
		logger.info("关闭连接："+channel.remoteAddress());
	} 
	public SessionBean findByUserId(String userId){
		return sessionMap.get(userId);
	}
	/**
	 * 当服务器关闭时调用
	 */
	public static void  onServerClose() {
		for(String key:sessionMap.keySet()){
			SessionBean session=sessionMap.get(key);
			if(session!=null) {
				onOffLine(session);
			}
		}
	}
    /**用户下线 */
	private static void onOffLine(SessionBean session){
		playerDao.save(session.getPlayerEntity());//保存用户信息 
		sessionMap.remove(session.getId());
		session.getChannel().attr(channelSession).remove();
		logger.info("用户下线。ip:"+session.getIp()+",用户id:"+session.getPlayerEntity().getUserId()+",用户nick:"+session.getPlayerEntity().getNickName());
	}
	
	/**获取所有的在线会话  */
	public static Collection<SessionBean> getOnlineSessions(){
		return sessionMap.values();
	}
	
    /**获取在线人数  */
	public static int getOnlineNumber() {
		return sessionMap.size();
	}
}
