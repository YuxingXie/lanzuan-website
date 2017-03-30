package com.lanzuan.website.dao;

import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.entity.SortLinkGroup;
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
public class SortLinkGroupDao extends BaseMongoDao<SortLinkGroup>  {
    private static Logger logger = LogManager.getLogger();
    @Resource
    private MongoOperations mongoTemplate;

    public SortLinkGroup findByUri(String uri) {
        return findByUri(uri,0);
    }
    public SortLinkGroup findByUri(String uri, int index) {
        DBObject dbObject=new BasicDBObject();
        dbObject.put("uri",uri);
        dbObject.put("enabled",true);
        dbObject.put("indexOfPage",index);
        return findOne(dbObject);
    }

    public List<SortLinkGroup> findByUriAndIndex(String uri, int index) {
        DBObject dbObject=new BasicDBObject();
        dbObject.put("uri",uri);
        dbObject.put("indexOfPage",index);
        return findAll(dbObject);
    }
//    public List<ArticleSectionGroupItem> findArticleSectionByArticleId(String id) {
//        DBObject dbObject=new BasicDBObject();
//        // {"productSeriesPrices.price":0.05},{"$set":{"productSeriesPrices.$.price":0.01}},false,true
//        dbObject.put("articles.$id",new ObjectId(id));
//        List<ArticleSectionGroupItem> articleSectionGroup =findAll(dbObject);
//
//        return articleSectionGroup;
//    }
//
//    public List<ArticleSectionGroupItem> findHomePageArticleSections() {
//
//        DBObject dbObject=new BasicDBObject();
//        dbObject.put("enabled",true);
////        dbObject.put("articles.$.title",1);
////        dbObject.put("articles.$.date",1);
////        dbObject.put("articles.$.content",0);
//        List<String> fields=new ArrayList<String>();
//        fields.add("id");
//        fields.add("name");
//        fields.add("image");
//        fields.add("enabled");
////        fields.add("articles.$.title");
//        fields.add("articles");
//        int limit= Constant.ARTICLE_SECTION_NUM;
//        List<ArticleSectionGroupItem> articleSectionGroup =findAllOrderBy(dbObject,fields,limit,"createDate",false);
//        for (ArticleSectionGroupItem articleSectionGroupItem : articleSectionGroup){
//            //首页并不需要文章内容，设为空提高页面速度
//            if (articleSectionGroupItem !=null&& articleSectionGroupItem.getArticles()!=null){
//                for (Article article: articleSectionGroupItem.getArticles()){
//                    article.setContent(null);
//                }
//            }
//        }
//        return articleSectionGroup;
//    }
}

