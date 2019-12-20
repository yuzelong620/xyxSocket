package com.crxl.xllxj.module.json;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
 
/**
 * json data cache
 * @author zxd
 *
 * @param <E>
 */
public abstract class JsonCache<E>{
	protected  Logger logger=Logger.getLogger(JsonCache.class);
	/**
	 * 通过反射获取子类确定的泛型类
	 */
	@SuppressWarnings("unchecked")
	public JsonCache( ) { 
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		EntityClass = (Class<E>) params[0];
	}
	

	Class<?extends E> EntityClass;
	List<E> list;
	Map<Integer,E> map;
	
	public void setList(List<E> datas) {
		if(datas==null) {
		   return;	
		}
		list=datas;
		Map<Integer,E> dataMap=new HashMap<>();
		for(E obj:datas) {
			try {
				int id=(Integer) obj.getClass().getMethod("getId",null).invoke(obj);
				dataMap.put(id, obj);
			}
			catch (Exception e) {
			  throw new RuntimeException("not find: getId() mothed. class:  "+obj.getClass());
			}
		}
		this.map=dataMap; 
		setDataCache(datas);
	}
	/**实现自己的缓存，请实现此方法*/
	protected void setDataCache(List<E> datas) {
		
	}
	/**用于检查数据*/
	protected void checkData(){
		
	}
    
	public List<E> getList( ) {
		return list;
	}
    
	public  E  getData(int id) {
		return map.get(id);
	}
	
	public  String getJsonFileName(){		
		String str=this.getClass().getSimpleName();
		str=str.replace("Cache", "");
		char[] chars = new char[1];
	    chars[0] = str.charAt(0);
	    String temp = new String(chars);
	    if (chars[0] >= 'A' && chars[0] <= 'Z') {//当为字母时，则转换为小写
	        str=str.replaceFirst(temp, temp.toLowerCase());
	    }
	    return str+".json";
	}
	public static void main(String[] args) {
		
	}
} 
