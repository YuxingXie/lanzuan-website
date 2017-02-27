package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.entity.ProductSubCategory;

import java.util.List;

/**
 * Created by Administrator on 2015/10/12.
 */
public interface IProductSubCategoryService extends IBaseEntityManager<ProductSubCategory> {
    List<ProductSubCategory> getProductSubCategoriesByCategoryId(String categoryId);

    ProductSubCategory getProductSubCategoriesByProductSeries(ProductSeries productSeries);

    ProductSubCategory findProductSubCategoryById(String id);

    ProductSubCategory findProductSubCategoryByIdWithoutProductSeries(String id);

@Deprecated
    void removeByProductSeries(ProductSeries productSeries);
}
