package com.lanzuan.website.dao;

import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.entity.WebPage;
import com.lanzuan.entity.WebPage;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/5/22.
 */
@Repository
public class WebPageDao extends BaseMongoDao<WebPage>  {
    private static Logger logger = LogManager.getLogger();
    //单个插入
    @Resource
    private MongoOperations mongoTemplate;


    public WebPage findByUri(String uri) {

        DBObject dbObject=new BasicDBObject();
        dbObject.put("uri",uri);
        dbObject.put("active",true);
        List<WebPage> list=findAll(dbObject);
//        query.fields().include("_id");
//        query.fields().include("domain");
//        query.fields().include("count");
        return list==null||list.size()!=1?null:list.get(0);
    }
}
