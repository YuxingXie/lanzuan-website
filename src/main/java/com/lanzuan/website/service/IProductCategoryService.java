package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.ProductCategory;
import com.lanzuan.entity.ProductSeries;

import java.util.List;

/**
 * Created by Administrator on 2015/10/12.
 */
public interface IProductCategoryService extends IBaseEntityManager<ProductCategory> {
    String getProductCategoryIdByProductSeriesId(String productSeriesId);

    List<ProductCategory> findAllCategories();
@Deprecated
    void removeByProductSeries(ProductSeries productSeries);
}
