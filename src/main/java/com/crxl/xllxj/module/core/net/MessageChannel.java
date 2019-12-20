package com.crxl.xllxj.module.core.net;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.crxl.xllxj.module.core.message.ResponseMsg;
import com.crxl.xllxj.module.core.thread.TaskManager;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class MessageChannel {
	static Logger logger=Logger.getLogger(MessageChannel.class);
	Channel channel;

	public MessageChannel(Channel channel) { 
		this.channel = channel;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void sendMessage(ResponseMsg msg) {		
		sendMessage(this.channel,msg);
	}
	
	public static void sendMessage(Channel channel,ResponseMsg msg) {
		if (msg == null) {
			return;
		}
		if(!channel.isActive()){
		    return;
		}
		String json = JSONObject.toJSONString(msg);
		sendMessage(channel,json);
	}

	public static void sendMessage(Channel channel,  String json) {
		channel.writeAndFlush(new TextWebSocketFrame(json));
//		logger.info("发送消息给："+channel.remoteAddress().toString()+" /"+json);
		// 记录消息收发日志
 		TaskManager.instance.putMessageLogger(json);
	}

}
