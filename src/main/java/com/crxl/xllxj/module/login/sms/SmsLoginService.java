package com.crxl.xllxj.module.login.sms;

import com.crxl.xllxj.module.common.enums.EnumConstant.IdentityId;
import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.message.ResponseMsg;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.core.thread.TaskManager;
import com.crxl.xllxj.module.login.bean.SmsLogin;
import com.crxl.xllxj.module.user.entity.UserEntity;
import com.crxl.xllxj.module.util.IdUtil;

/**
 * 短信登录
 * 
 * @author zxd
 *
 */
public class SmsLoginService extends BaseService {

	public void sendVerifyCode(String mobile, String areaCode) throws Exception {
		smsService.sendVerifyCode(mobile, areaCode);
	}

	public void sendVerifyCode(RequestMsg req) {
		SmsLogin smsLogin = req.getBody(SmsLogin.class);
		String mobile = smsLogin.getMobile();
		String areaCode = smsLogin.getAreaCode();
		if (mobile == null || "".equals(mobile)) {
			req. sendMessage(ResponseMsg.createInfoMessage("手机号不能为空。"));
			return;
		}
		if (smsService.sendVerifyCode(mobile, areaCode)) {
			req. sendMessage(ResponseMsg.createInfoMessage("发送成功。"));
		} else {
			req. sendMessage(ResponseMsg.createInfoMessage("失败，请稍后重试。"));
		}
	}

	/**
	 * @param req
	 */
	public void smsLogin(RequestMsg req) {
		SmsLogin smsLogin = req.getBody(SmsLogin.class);
		String verifyCode = smsLogin.getVerifyCode();
		String mobile = smsLogin.getMobile();
		String areaCode = smsLogin.getAreaCode();

		if (mobile == null || "".equals(mobile)) {
			req. sendMessage(ResponseMsg.createInfoMessage("手机号不能为空。"));
			 
			return;
		}
		if (verifyCode == null || "".equals(verifyCode)) {
			req. sendMessage( ResponseMsg.createInfoMessage("验证码不能为空。"));
			return;
		}
		if (!smsService.validateVerifyCode(mobile, areaCode, verifyCode)) {
			req. sendMessage( ResponseMsg.createInfoMessage("验证失败。"));
			return;
		}
		UserEntity entity = userDao.findByPhone(mobile);
		String nick = null;
		if (entity == null) {
			long identityId = identityDao.nextId(IdentityId.user);
			if (nick == null) {
				nick = loginService.createNick(identityId);
			}
			entity = new UserEntity(IdUtil.randomUUID(), smsLogin.getChannelId(), mobile, identityId);
			// userDao.save(entity);

			TaskManager.instance.putSaveTask(req.getChannel(), userDao.getAsynSaveTask(entity));
		}
		// SessionBean sessionBean=
		// sessionService.createById(entity.getId(),req.getChannel(),nick);
		// loginService.loginBySession(req.getChannel(), req.getCommand(),
		// sessionBean);
	}
}