package com.lanzuan.mall.dao;


import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.common.code.AlipayTransStatusEnum;
import com.lanzuan.common.helper.service.ServiceManager;
import com.lanzuan.entity.Account;
import com.lanzuan.entity.AlipayBatchTrans;
import com.lanzuan.entity.AlipayTrans;
import com.lanzuan.entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 */
@Repository
public class AlipayBatchTransDao extends BaseMongoDao<AlipayBatchTrans> {
    private static Logger logger = LogManager.getLogger();
    @Resource
    private MongoOperations mongoTemplate;


}
