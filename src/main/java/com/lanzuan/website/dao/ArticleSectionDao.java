package com.lanzuan.website.dao;

import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.common.util.BusinessException;
import com.lanzuan.entity.ArticleSection;
import com.lanzuan.entity.PageTemplate;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/5/22.
 */
@Repository
public class ArticleSectionDao extends BaseMongoDao<ArticleSection>  {
    private static Logger logger = LogManager.getLogger();
    //单个插入
    @Resource
    private MongoOperations mongoTemplate;
    public List<ArticleSection> findArticleSectionByArticleId(String id) {
        DBObject dbObject=new BasicDBObject();
        // {"productSeriesPrices.price":0.05},{"$set":{"productSeriesPrices.$.price":0.01}},false,true
        dbObject.put("articles.$id",new ObjectId(id));
        List<ArticleSection> articleSections=findAll(dbObject);

        return articleSections;
    }

}

