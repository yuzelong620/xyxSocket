package com.crxl.xllxj.module.identity.dao;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.xlcr.mongo.dao.BaseDao;

import com.crxl.xllxj.module.common.enums.EnumConstant.IdentityId;
import com.crxl.xllxj.module.identity.entity.IdentityEntity;

public class IdentityDao extends BaseDao<IdentityEntity> {

	private static final String VALUE = "value";
	private static final String _ID = "_id";

	public long nextId(IdentityId identityType) {
		Query query = new Query(Criteria.where(_ID).is(identityType.getId()));
		Update update = new Update().inc(VALUE, 1);
		IdentityEntity identity = super.findAndModify(query, update);
		return identity.getValue();
	}
}
