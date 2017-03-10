package com.lanzuan.website.dao;

import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.entity.Article;
import com.lanzuan.entity.ArticleSection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    public List<ArticleSection> findHomePageArticleSections() {

        DBObject dbObject=new BasicDBObject();
        dbObject.put("enabled",true);
//        dbObject.put("articles.$.title",1);
//        dbObject.put("articles.$.date",1);
//        dbObject.put("articles.$.content",0);
        List<String> fields=new ArrayList<String>();
        fields.add("id");
        fields.add("name");
        fields.add("image");
        fields.add("enabled");
//        fields.add("articles.$.title");
        fields.add("articles");
        int limit= Constant.articleSectionNum;
        List<ArticleSection> articleSections=findFields(dbObject,fields,limit,"createDate",false);
        for (ArticleSection articleSection:articleSections){
            //首页并不需要文章内容，设为空提高页面速度
            if (articleSection!=null&&articleSection.getArticles()!=null){
                for (Article article:articleSection.getArticles()){
                    article.setContent(null);
                }
            }
        }
        return articleSections;
    }
}

