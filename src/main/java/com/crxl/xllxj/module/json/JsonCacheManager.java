package com.crxl.xllxj.module.json;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.json.datacache.AnswerCache;
import com.crxl.xllxj.module.json.datacache.AppEaranceCache;
import com.crxl.xllxj.module.json.datacache.ArticleCache;
import com.crxl.xllxj.module.json.datacache.DetecTiveCache;
import com.crxl.xllxj.module.json.datacache.DropGroupCache;
import com.crxl.xllxj.module.json.datacache.DuanCache;
import com.crxl.xllxj.module.json.datacache.GameParamsCache;
import com.crxl.xllxj.module.json.datacache.QuestionCache;
import com.crxl.xllxj.module.json.datacache.RobotCache;
import com.crxl.xllxj.module.json.datacache.ShopCache;
import com.crxl.xllxj.module.json.datacache.SignInCache;
import com.crxl.xllxj.module.json.datacache.TaskCache;
import com.crxl.xllxj.module.json.datacache.VideoCache;
import com.mind.core.util.JsonUtil;

public class JsonCacheManager extends BaseService {

	private JsonCacheManager() {
	}

	private static JsonCacheManager instance = new JsonCacheManager();

	public static JsonCacheManager getInstance() {
		return instance;
	}

	public Map<Class<?>, JsonCache<?>> getClassMappingCache() {
		return ClassMappingCache;
	}

	Map<Class<?>, JsonCache<?>> ClassMappingCache = new HashMap<>();

	public synchronized <T> void putJSONDataCache(JsonCache<T> cache) {
		JsonCache<?> cacheObj = ClassMappingCache.get(cache.getClass());
		if (cacheObj != null) {//如果已经有cache,替换cache里的数据
			JsonCache<T> temp = (JsonCache<T>) cacheObj;
			temp.setList(cache.getList());
			return;
		}
		ClassMappingCache.put(cache.getClass(), cache);
	}

	public static <T> T getCache(Class<? extends T> clz) {
		return (T)instance.ClassMappingCache.get(clz);
	}

	/**
	 * 加载全部json数据
	 */
	public static synchronized void reloadAll() {
		JsonCacheManager object = new JsonCacheManager();
		object.init();
		instance = object;
	}

	private void loadData(Class<? extends JsonCache>... clzs) {
		List<JsonCache> checkTemp=new ArrayList<>();
		Map<String,List<String>> pathMap=getPathMapping();
		for (Class<? extends JsonCache> clz : clzs) {
			try {
				JsonCache obj = clz.newInstance();
				List<String> paths=pathMap.get(obj.getJsonFileName());
				if(paths==null){
					throw new RuntimeException("没有找到对应的json 文件。cache:"+clz.getName());
				}
				checkTemp.add(obj);
				List list =null;
				for(String path:paths){			   				  
					List items = JsonUtil.parseJsonArray(path, obj.EntityClass);
					if(list==null){
						list=items;
					}
					else{
						list.addAll(items);
					}
					logger.info("加载json文件：" + path);
				}
				obj.setList(list);
				putJSONDataCache(obj);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		//检查数据
		for(JsonCache obj:checkTemp){
			obj.checkData();
		}		
	}

	/**
	 * 初始化
	 */
	public void init() {
		loadData(
				GameParamsCache.class,
				RobotCache.class,
				VideoCache.class,
				QuestionCache.class,
				AnswerCache.class,
				SignInCache.class,
				TaskCache.class,
				ArticleCache.class,
				ShopCache.class,
				DuanCache.class,
				AppEaranceCache.class,
				DropGroupCache.class,
				DetecTiveCache.class
				);
	}

	// /**
	// * 获得json 配置目录
	// * @return
	// */
	// private static String getJsonPath(){
	// if(jsonPath==null){
	// jsonPath="json/";
	// }
	// return jsonPath;
	// }
	private  Map<String,List<String>> getPathMapping() {
		File file = new File("json/");
		if (file.exists()) {
			System.out.println("外部文件");
		} 
		else {
			System.out.println("类路径文件");
			file = new File(JsonCacheManager.class.getResource("/").getPath() + "//json//"  );
		}
		Map<String,List<String>> map=new HashMap<>();
		for (File obj : file.listFiles()) { 
			String path=obj.getPath();
			int beginIndex=path.lastIndexOf("//"); 
			if(beginIndex==-1){
				beginIndex=path.lastIndexOf("\\");
			}
			path=path.substring(beginIndex+1,path.length());
			if(path.contains("/")){
				path=path.substring(path.lastIndexOf("/")+1,path.length());
			}
			
			
			String pathKey=path;
			if(pathKey.contains("_")){
				pathKey=pathKey.substring(pathKey.lastIndexOf("_")+1,pathKey.length());
			}
			if(pathKey.contains("/")){
				pathKey=pathKey.substring(pathKey.lastIndexOf("/")+1,pathKey.length());
			}
			List<String> list=map.get("pathKey");
			if(list==null){
				list=new ArrayList<>();
				map.put(pathKey, list);
			}
			list.add(path);
		}
		
		for(Entry<String, List<String>> entry:map.entrySet()){
			System.out.println(entry.getKey()+"  <>  "+entry.getValue().toString()); 
		}
		return map;
	}
 
}
