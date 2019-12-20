package com.crxl.xllxj.module.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.crxl.xllxj.module.core.net.MainWebSocket;
import com.crxl.xllxj.module.room.manager.RoomManager;
import com.crxl.xllxj.module.session.manager.SessionManager;

/**
 * 服务器关闭钩子线程
 *
 */
public class ShutdownHookThread extends Thread {
	
	Logger logger = LoggerFactory.getLogger(ShutdownHookThread.class);

	private ShutdownHookThread() {
	}
	
	public static final ShutdownHookThread instance=new ShutdownHookThread();

	@Override
	public void run() {
		MainWebSocket.closed = true;
		logger.info("-----------------------》关闭消息入口");
		RoomManager.getInstance().onCloseServer();
		logger.info("房间全部关闭");
		SessionManager.onServerClose();
		logger.info("-----------------------》清理会话");
		TaskManager.instance.close();
		logger.info("-----------------------》关闭所有任务线程");

		logger.info("-----------------------》服务器关闭结束");
	}

}
