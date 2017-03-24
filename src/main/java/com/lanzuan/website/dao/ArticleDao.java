package com.lanzuan.website.dao;

import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.entity.Article;
import com.mongodb.BasicDBObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/5/22.
 */
@Repository
public class ArticleDao extends BaseMongoDao<Article>  {
    private static Logger logger = LogManager.getLogger();

    @Resource
    private MongoOperations mongoTemplate;

    public void praise(String id) {
        //db.users.update({name: 'Lisi'}, {$inc: {age: 50}}, false, true);
        Query query=new BasicQuery(new BasicDBObject("_id",new ObjectId(id)));
        Update update=new Update().inc("praises", 1);
        Article article=mongoTemplate.findAndModify(query, update, Article.class);
    }

    public Article increaseReadTimes(String id) {
        //db.users.update({name: 'Lisi'}, {$inc: {age: 50}}, false, true);
        Query query=new BasicQuery(new BasicDBObject("_id",new ObjectId(id)));
        Update update=new Update().inc("readTimes", 1);
        Article article=mongoTemplate.findAndModify(query, update, Article.class);
        return article;
    }
}
