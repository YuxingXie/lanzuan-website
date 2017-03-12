package com.lanzuan.website.dao;

import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.entity.ImageCardGroup;
import com.lanzuan.entity.ImageTextBlockGroup;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/5/22.
 */
@Repository
public class ImageTextBlockGroupDao extends BaseMongoDao<ImageTextBlockGroup>  {
    private static Logger logger = LogManager.getLogger();
    @Resource
    private MongoOperations mongoTemplate;


    public ImageTextBlockGroup findByUri(String uri) {
        DBObject dbObject=new BasicDBObject();
        dbObject.put("uri",uri);
        dbObject.put("enabled",true);
        return findOne(dbObject);
    }
}
