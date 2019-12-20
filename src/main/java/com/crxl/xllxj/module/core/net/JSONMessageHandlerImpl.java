package com.crxl.xllxj.module.core.net;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.crxl.xllxj.module.core.JsonEvent;
import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.message.ResponseMsg;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.core.thread.TaskManager;
import com.crxl.xllxj.module.session.bean.SessionBean;

@Service
public class JSONMessageHandlerImpl extends BaseService implements JSONMessageHandler {

	private static Map<Integer, JsonEvent> commands = new HashMap<>();

	public void register(JsonEvent event, int cmdId) {
		if (commands.containsKey(cmdId)) {
			throw new RuntimeException("命令号重复");
		}
		commands.put(cmdId, event);
	}

	// other cmd
	private void loginHandler(RequestMsg req) {
		switch (req.getCommandId()) {
		case CommandId.lonin_validation_login:
			// loginService.
			// case CommandId.login_token_auto_login://token自动登录
			// loginService.tokenAutoLogin(req);
			// break;
			// case CommandId.lonin_wx_login:
			// wXService.wxLogin(req);
			// break;
		case CommandId.lonin_test_login:
			loginService.testLogin(req);
			break;
		case CommandId.login_sms_send_code:
			smsLoginService.sendVerifyCode(req);
			break;
		case CommandId.login_sms_login:
			smsLoginService.smsLogin(req);
			break;
		case CommandId.lonin_wx_login:
			wXService.wxLogin(req);
			break;
		case CommandId.update_wx_info:
			wXService.updateUserInfo(req);
			break;
		default:
			ResponseMsg msg = ResponseMsg.createErrorMessage("无效指令。");
			req.sendMessage(msg);
			break;
		}
	}

	// game cmd
	private void gameHandler(RequestMsg req) {
		SessionBean session = sessionService.getSession(req.getChannel());
		if (session == null) {
			req.sendMessage(ResponseMsg.createMessage(CommandId.login_visitors_relogin));
			return;
		}
		switch (req.getCommandId()) {
		case CommandId.room_answer:
			roomService.answerQuestion(req);
			break;
//		case CommandId.room_send_private_message://私聊
//			roomService.sendChatPrivateMessage(req);
//			break;
		case CommandId.room_matching:
			roomService.matching(req);
			break;
		case CommandId.room_cancel_match:
			roomService.cancelMatch(req);
			break;
		case CommandId.room_info:
			roomService.roomInfo(req);
			break;
		case CommandId.room_cancel_connect_room:
			roomService.cancelConnectRoom(req);
			break;
		case CommandId.user_get_signIn_data://用户获取签到信息
			signInServer.userReadSignInData(req);
			break;
		case CommandId.user_daily_signIn://用户每日签到
			signInServer.everydaySignIn(req);
			break;
		case CommandId.task_list://任务列表
			taskService.everyDayTask(req);
			break;
		case CommandId.task_update://任务进度更新
			taskService.updateTaskValue(req);
			break;
		case CommandId.task_get_award://任务领取奖励
			taskService.getAward(req);
			break;
		case CommandId.shop_list://商品列表
			shopService.shopList(req);
			break;
		case CommandId.shop_buy://商品购买
			shopService.buyGoods(req);
			break;
		case CommandId.appearance_list://外观列表
			appEaranceService.findByArticleTypeData(req);
			break;
		case CommandId.appearance_get://外观获取
			appEaranceService.getItem(req);
			break;
		case CommandId.appearance_adorn://外观佩戴
			appEaranceService.itemAdorn(req);
			break;
		case CommandId.emoticons_send:
			roomService.sendEmoticons(req);
			break;
		case CommandId.bag_openbox:
			bagService.openBox(req);
			break;
		case CommandId.score_ranKing_list:
			playerService.scoreRanKingList(req);
			break;
		case CommandId.detec_tive:
			playerService.detecTive(req);
			break;
//		case CommandId.emoticons_find:
//			roomService.getEmoticons(req);
//			break;
		default:
			ResponseMsg msg = ResponseMsg.createErrorMessage("无效指令");
			req.sendMessage(msg);
			break;
		}
	}

	@Override
	public void handler(RequestMsg req) {
		int commandType = req.getCommandId() / 100000;
		switch (commandType) {
		case CommandId.command_type_system:// 0 system
			Runnable systemTask = new Runnable() {
				public void run() {
					systemHandler(req);
				}
			};
			TaskManager.instance.putLoginTask(new JsonEvent(systemTask, req));
			break;
		case CommandId.command_type_common:// 1公共命令
			Runnable loginTask = new Runnable() {
				public void run() {
					loginHandler(req);
				}
			};
			TaskManager.instance.putLoginTask(new JsonEvent(loginTask, req));
			break;
		case CommandId.command_type_game:// 2游戏命令
			Runnable gameTask = new Runnable() {
				public void run() {
					gameHandler(req);
				}
			};
			TaskManager.instance.putMainTask(req.getChannel(), new JsonEvent(gameTask, req));
			break;
		// case CommandId.command_type_gm://客服相关
		// Runnable gmTask = new Runnable() {
		// public void run() {
		// gmHandler(req);
		// }
		// };
		// TaskManager.instance.putOtherTask(new JsonEvent(gmTask,req));
		// break;
		default:
			ResponseMsg msg = ResponseMsg.createErrorMessage("无效指令。");// new
																		// CommonInfoMessage("无效指令.",
																		// req.getCommand());
			// super.sendEvent(req.channel, msg);
			req.sendMessage(msg);
			break;
		}
	}

	private void systemHandler(RequestMsg req) {
		switch (req.getCommandId()) {
		case CommandId.system_confim:
			systemService.confimOk(req);
			break;
		default:
			ResponseMsg msg = ResponseMsg.createErrorMessage("无效指令。");
			req.sendMessage(msg);
			break;
		}
	}

//	// gm handler
//	private void gmHandler(RequestMsg req) {
//		switch (req.getCommandId()) {
//
//		}
//	}
}
