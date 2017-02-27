package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.entity.ProductSubCategory;
import com.lanzuan.website.dao.ProductSubCategoryDao;
import com.lanzuan.website.service.IProductSubCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/10/12.
 */
@Service
public class ProductSubCategoryService extends BaseEntityManager<ProductSubCategory> implements IProductSubCategoryService {
    @Resource
    private ProductSubCategoryDao productSubCategoryDao;
    protected EntityDao<ProductSubCategory> getEntityDao() {
        return this.productSubCategoryDao;
    }

    @Override
    public List<ProductSubCategory> getProductSubCategoriesByCategoryId(String categoryId) {
        return productSubCategoryDao.getProductSubCategoriesByCategoryId(categoryId);
    }

    @Override
    public ProductSubCategory getProductSubCategoriesByProductSeries(ProductSeries productSeries) {
        return productSubCategoryDao.getProductSubCategoriesByProductSeries(productSeries);
    }

    @Override
    public ProductSubCategory findProductSubCategoryById(String id) {
        return productSubCategoryDao.findProductSubCategoryById(id);
    }

    @Override
    public ProductSubCategory findProductSubCategoryByIdWithoutProductSeries(String id) {
        return productSubCategoryDao.findProductSubCategoryByIdWithoutProductSeries(id) ;
    }

    @Override
    public void removeByProductSeries(ProductSeries productSeries) {
        productSubCategoryDao.removeByProductSeries(productSeries);
    }


}
