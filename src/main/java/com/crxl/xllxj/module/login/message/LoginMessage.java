package com.crxl.xllxj.module.login.message;

import com.crxl.xllxj.module.player.entity.PlayerEntity;

public class LoginMessage {

	public LoginMessage() {
	}
	
	public LoginMessage(PlayerEntity player) {
		this.player = player;
	}
	
	PlayerEntity player;

	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

}
