package com.crxl.xllxj.module.json.datacache;

import java.util.List;

import com.crxl.xllxj.module.json.JsonCache;
import com.globalgame.auto.json.GameParams_Json;

public class GameParamsCache extends JsonCache<GameParams_Json>{

	private static GameParams_Json gameParams;

	@Override
	protected void setDataCache(List<GameParams_Json> datas) {
		gameParams = datas.get(0);
	}

	public static GameParams_Json getGameParamsInstance() {
		return gameParams;
	}
 

}
