package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ProductCategory;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.website.dao.ProductCategoryDao;
import com.lanzuan.website.service.IProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/10/12.
 */
@Service
public class ProductCategoryService extends BaseEntityManager<ProductCategory> implements IProductCategoryService {
    @Resource
    private ProductCategoryDao productCategoryDao;
    protected EntityDao<ProductCategory> getEntityDao() {
        return this.productCategoryDao;
    }

    @Override
    public String getProductCategoryIdByProductSeriesId(String productSeriesId) {
        return productCategoryDao.getProductCategoryIdByProductSeriesId(productSeriesId);
    }

    @Override
    public List<ProductCategory> findAllCategories() {
        return productCategoryDao.findAllCategories();
    }

    @Override
    public void removeByProductSeries(ProductSeries productSeries) {
        productCategoryDao.removeByProductSeries(productSeries);
    }
}
