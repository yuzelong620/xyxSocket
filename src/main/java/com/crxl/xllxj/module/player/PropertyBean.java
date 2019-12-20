package com.crxl.xllxj.module.player;

import com.crxl.xllxj.module.player.entity.PlayerEntity;

public class PropertyBean {

	public PropertyBean(PlayerEntity player) { 
		this.goldCoin=player.getGoldCoin();
	}
	 
	int goldCoin;

	public PropertyBean(){
	}

	public int getGoldCoin() {
		return goldCoin;
	}

	public void setGoldCoin(int goldCoin) {
		this.goldCoin = goldCoin;
	}
	
  
}
