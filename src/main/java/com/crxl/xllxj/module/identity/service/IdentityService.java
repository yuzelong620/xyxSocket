package com.crxl.xllxj.module.identity.service;
 

import com.alibaba.fastjson.JSONObject;
import com.crxl.xllxj.module.common.enums.EnumConstant.IdentityId;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.identity.entity.IdentityEntity; 

public class IdentityService extends BaseService {
 
    /**
     * 系统启动时调用
     */
	public static void checkIdentityEntity() {
		//序列号生成器
		for (IdentityId obj : IdentityId.values()) {
			IdentityEntity entity = identityDao.findByID(obj.getId());
			if (entity == null) {
				entity = new IdentityEntity(obj.getId(), obj.name(), obj.getInitValue());
				identityDao.save(entity);
				logger.info("创建序列生成器：" + JSONObject.toJSONString(entity));
			} else {
				logger.info("查询序列生成器：" + JSONObject.toJSONString(entity));
			}
		}		 
	}

}
