package com.lanzuan.function;

import com.lanzuan.common.helper.service.ProjectContext;
import com.lanzuan.entity.ProductCategory;
import com.lanzuan.entity.ProductProperty;
import com.lanzuan.entity.ProductSubCategory;
import com.lanzuan.mall.service.IProductCategoryService;
import com.lanzuan.mall.service.IProductSubCategoryService;
import com.lanzuan.mall.service.impl.ProductPropertyService;

import java.util.List;

/**
 * Created by Administrator on 2015/10/12.
 */
public class ProductFunction {
    public static List<ProductProperty> getProductPropertiesByProductSeriesId(String productSeriesId){
        if (productSeriesId==null) return null;
        ProductPropertyService productPropertyService=ProjectContext.getBean("productPropertyService");
        return productPropertyService.getProductPropertiesByProductSeriesId(productSeriesId);
    }
    public static List<ProductCategory> getProductCategories(){
        IProductCategoryService productCategoryService=ProjectContext.getBean("productCategoryService");
        return productCategoryService.findAll();
    }
    public static String getProductCategoryIdByProductSeriesId(String productSeriesId){
        IProductCategoryService productCategoryService=ProjectContext.getBean("productCategoryService");
        return productCategoryService.getProductCategoryIdByProductSeriesId(productSeriesId);
    }

    public static List<ProductSubCategory> getProductSubCategoriesByCategoryId(String categoryId){
        if (categoryId==null) return null;
        IProductSubCategoryService productSubCategoryService=ProjectContext.getBean("productSubCategoryService");
        List<ProductSubCategory> list= productSubCategoryService.getProductSubCategoriesByCategoryId(categoryId);
        return list;
    }
}
