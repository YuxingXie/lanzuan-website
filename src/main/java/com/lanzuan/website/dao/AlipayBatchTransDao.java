package com.lanzuan.website.dao;


import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.entity.AlipayBatchTrans;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/11.
 */
@Repository
public class AlipayBatchTransDao extends BaseMongoDao<AlipayBatchTrans> {
    private static Logger logger = LogManager.getLogger();
    @Resource
    private MongoOperations mongoTemplate;


}
