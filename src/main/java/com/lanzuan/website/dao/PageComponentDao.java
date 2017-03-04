package com.lanzuan.website.dao;

import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.common.util.BusinessException;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.User;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/5/22.
 */
@Repository
public class PageComponentDao extends BaseMongoDao<PageComponent>  {
    private static Logger logger = LogManager.getLogger();
    //单个插入
    @Resource
    private MongoOperations mongoTemplate;

    public List<PageComponent> findHomePageComponents() {
        DBObject dbObject=new BasicDBObject();

        return findAll(dbObject);
    }
}
