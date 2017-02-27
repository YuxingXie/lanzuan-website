package com.lanzuan.mall.dao;

import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.entity.FriendshipMall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/12/15.
 */
@Repository
public class FriendshipMallDao extends BaseMongoDao<FriendshipMall> {
    private static Logger logger = LogManager.getLogger();
    @Resource
    private MongoOperations mongoTemplate;
}
