package com.crxl.xllxj.module.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crxl.xllxj.module.appEarance.service.AppEaranceService;
import com.crxl.xllxj.module.bag.dao.BagDao;
import com.crxl.xllxj.module.bag.service.BagService;
import com.crxl.xllxj.module.common.RewardService;
import com.crxl.xllxj.module.core.net.JSONMessageHandler;
import com.crxl.xllxj.module.core.net.JSONMessageHandlerImpl;
import com.crxl.xllxj.module.drop.service.DropService;
import com.crxl.xllxj.module.gamePeriod.dao.GamePeriodDao;
import com.crxl.xllxj.module.gamePeriod.service.GamePeriodService;
import com.crxl.xllxj.module.identity.dao.IdentityDao;
import com.crxl.xllxj.module.identity.service.IdentityService;
import com.crxl.xllxj.module.login.LoginService;
import com.crxl.xllxj.module.login.sms.SmsLoginService;
import com.crxl.xllxj.module.login.wx.service.WXService;
import com.crxl.xllxj.module.player.dao.PlayerDao;
import com.crxl.xllxj.module.player.service.PlayerService;
import com.crxl.xllxj.module.redDotMessage.dao.RedDotMessageDao;
import com.crxl.xllxj.module.redDotMessage.service.RedDotMessageService;
import com.crxl.xllxj.module.room.dao.RoomDao;
import com.crxl.xllxj.module.room.service.RoomService;
import com.crxl.xllxj.module.session.service.SessionService;
import com.crxl.xllxj.module.shop.dao.ShopDao;
import com.crxl.xllxj.module.shop.service.ShopService;
import com.crxl.xllxj.module.signIn.dao.SignInDao;
import com.crxl.xllxj.module.signIn.server.SignInServer;
import com.crxl.xllxj.module.sms.dao.SmsDao;
import com.crxl.xllxj.module.sms.service.SmsService;
import com.crxl.xllxj.module.system.service.SystemService;
import com.crxl.xllxj.module.task.dao.TaskDao;
import com.crxl.xllxj.module.task.service.TaskService;
import com.crxl.xllxj.module.user.dao.UserDao;
import com.crxl.xllxj.module.user.service.UserService; 

public class BaseService {
	
	protected static Logger logger=LoggerFactory.getLogger(BaseService.class); 
	protected static UserDao userDao=new UserDao();
	protected static PlayerDao playerDao=new PlayerDao(); 
	protected static SmsDao  smsDao=new SmsDao();
	protected static BagDao  bagDao=new BagDao(); 
	protected static IdentityDao identityDao=new IdentityDao();
	protected static RoomDao roomDao=new RoomDao(); 
	protected static SignInDao signInDao=new SignInDao();
	protected static GamePeriodDao gamePeriodDao=new GamePeriodDao();
	protected static TaskDao taskDao=new TaskDao();
	protected static ShopDao shopDao=new ShopDao();
	protected static RedDotMessageDao redDotMessageDao=new RedDotMessageDao();
	 
	protected static RoomService roomService=new RoomService();
	protected static JSONMessageHandler jSONMessageHandlerImpl=new JSONMessageHandlerImpl();
	protected static LoginService loginService=new LoginService();
	protected static UserService userService=new UserService();
	protected static WXService wXService=new WXService();
	protected static PlayerService playerService=new PlayerService();
	protected static SessionService sessionService=new SessionService();
	protected static SmsService smsService=new SmsService();
	protected static SmsLoginService smsLoginService=new SmsLoginService();
	protected static BagService  bagService=new BagService();
	protected static RewardService  rewardService=new RewardService();
	protected static IdentityService identityService=new IdentityService();
	protected static SystemService systemService=new SystemService();
	protected static SignInServer signInServer=new SignInServer();
	protected static GamePeriodService gamePeriodService=new GamePeriodService();
	protected static TaskService taskService=new TaskService();
	protected static ShopService shopService=new ShopService();
	protected static AppEaranceService appEaranceService=new AppEaranceService();
	protected static DropService dropService=new DropService();
	protected static RedDotMessageService redDotMessageService=new RedDotMessageService();

}
