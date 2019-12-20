package com.crxl.xllxj.module.login;

import com.crxl.xllxj.module.common.enums.EnumConstant.IdentityId;
import com.crxl.xllxj.module.core.constent.ChannelCost.ChannelId;
import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.message.ResponseMsg;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.login.bean.TestLogin;
import com.crxl.xllxj.module.login.bean.UserLogin;
import com.crxl.xllxj.module.login.message.LoginMessage;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.crxl.xllxj.module.user.entity.UserEntity;

import io.netty.channel.Channel;

public class LoginService extends BaseService{
	
//	public void login (String id,Channel channel,int cmdId,String nickName){
//		SessionBean sessionBean = sessionService.createById(id,channel,nickName); 
//		loginBySession(channel, cmdId, sessionBean);
//	}

	public void loginBySession(Channel channel, int cmdId, SessionBean sessionBean) {
		 
		 
//		List<BagItemBean> bagItems = bagService.toBagItems(sessionBean.getBagEntity().getItems());
//		//更新token
//		String token=IdUtil.randomUUID();
//		userDao.updateUserToken(sessionBean.getId(),token);
//		LoginMessage msg = new LoginMessage( token );
//		msg.setCommand(cmdId);
//		sendEvent(channel, msg);//发送数据
	}
	/**
	 * 验证用户登录是否过期
	 * @param req 
	 */
	
	public void validationLogin(RequestMsg req) {
		UserLogin userLogin = req.getBody(UserLogin.class);
		String deviceId = userLogin.getDeviceId();
		UserEntity userEntity = userDao.findByDeviceId(deviceId);
		if (userEntity == null) {
			req.sendMessage( ResponseMsg.createMessage("用户未登录", 500));
		}else {
			userEntity.getLoginTime();
		}
	}
	
//	public void gmLogin(RequestMessage req) {
//		UserLogin userLogin = req.getBody(UserLogin.class);
//		String deviceId = userLogin.getDeviceId();
//		UserEntity userEntity = userDao.findByDeviceId(deviceId);
//		if (userEntity == null) {
//				super.sendEvent(req.getChannel(), CommonDataMessage.createMessage("用户未登录", 500));			
//			}
//			
//		}
//		
//		String phone = userLogin.getPhone();
//		String code = userLogin.getCode();
//		if ((phone == null || "".equals(phone))&&(code == null ||"".equals(code))) {
//			super.sendEvent(req.getChannel(), CommonInfoMessage.createInfoMessage("请求參數沒有获取到")); 
//			return;
//		}
//			
	
		
	

//	public void tokenAutoLogin(RequestMessage req) { 
//		String token=req.getParams("token");//token
//	    UserEntity user=userDao.findByToken(token);
//	    if(user==null){//自动登录失败
//	    	sendEvent(req.getChannel(), new ResponseMessage(CommandId.login_token_login_fail));
//	    	return;
//	    }
//	    String nick= loginService.createNick( user.getIdentityId());
//	    login(user.getId(), req.getChannel(), req.getCommand(),nick);//先传一个 空昵称
//	}
	
	/**
	 * "user_"+identityId
	 * @param identityId
	 * @return
	 */
	public String createNick(long identityId){
		return "user_"+identityId;
	}
	
	/**测试登录接口 */
	public void testLogin(RequestMsg req) { 
		TestLogin login=req.getBody(TestLogin.class);
		String id=ChannelId.test.name()+"_"+login.getId();
		UserEntity user=userDao.findByID(id);
		boolean isRegister=false;
		if(user==null){
			long identityId=identityDao.nextId(IdentityId.user);
			user=new UserEntity(id, "test", ""+identityId, identityId);
			userDao.save(user);
			isRegister=true;
		} 
		SessionBean session=sessionService.createByPlayer(req.getChannel(), user,isRegister);
		sendSessionInfo(req, session);
	}
	/**
	 * 发送会话信息
	 * @param req
	 * @param session
	 */
	public void sendSessionInfo(RequestMsg req, SessionBean session) {
		LoginMessage data=new LoginMessage(session.getPlayerEntity());
		ResponseMsg msg=ResponseMsg.createMessage(data, req.getCommandId());
		req.sendMessage(msg);
	}
}
