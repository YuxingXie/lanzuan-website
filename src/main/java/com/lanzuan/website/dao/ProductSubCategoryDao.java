package com.lanzuan.website.dao;

import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.common.helper.service.ServiceManager;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.entity.ProductSubCategory;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/10/12.
 */
@Repository
public class ProductSubCategoryDao  extends BaseMongoDao<ProductSubCategory> {
    private static Logger logger = LogManager.getLogger();
    @Resource
    private MongoOperations mongoTemplate;
    public List<ProductSubCategory> getProductSubCategoriesByCategoryId(String categoryId) {
//        ProductSubCategory productSubCategory=new ProductSubCategory();
        //TODO
//        productSubCategory.setCategoryId(categoryId);
        DBRef dbRef=new DBRef("productCategory", new ObjectId(categoryId));
        DBObject dbObject=new BasicDBObject();
        dbObject.put("productCategory",dbRef);
        return getMongoTemplate().find(new BasicQuery(dbObject),ProductSubCategory.class);
    }

    public ProductSubCategory getProductSubCategoriesByProductSeries(ProductSeries productSeries) {
        //TODO
        return null;
    }

    public ProductSubCategory findProductSubCategoryById(String id) {
       return findProductSubCategoryById(id,true);
    }
    public ProductSubCategory findProductSubCategoryById(String id,boolean withProductSeries) {
        ProductSubCategory productSubCategory=findById(id);
        if (productSubCategory==null) return null;
        DBRef dbRef=new DBRef("productSubCategory",new ObjectId(id));
        DBObject dbObject=new BasicDBObject("productSubCategory",dbRef);
        if (withProductSeries){
            List<ProductSeries> productSeriesList= ServiceManager.productSeriesService.findProductSeriesAllRef(dbObject);
            productSubCategory.setProductSeriesList(productSeriesList);
        }

        return productSubCategory;
    }
    public ProductSubCategory findProductSubCategoryByIdWithoutProductSeries(String id) {
        return findProductSubCategoryById(id,false);
    }

@Deprecated
    public void removeByProductSeries(ProductSeries productSeries) {
//        DBRef dbRef=new DBRef("productSubCategory",new ObjectId(id));
//        DBObject dbObject=new BasicDBObject("productSubCategory",dbRef);
    }
}
