package com.crxl.xllxj.module.system.service;

import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.service.BaseService;

public class SystemService extends BaseService {

	public void confimOk(RequestMsg req) {
		RequestMsg obj = new RequestMsg();
		obj.setCommandId(req.getCommandId());
		obj.setBody(req.getBody());
		obj.setChannel(req.getChannel());
		jSONMessageHandlerImpl.handler(obj);
	}
}
