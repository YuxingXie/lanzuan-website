package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.ProductProperty;
import com.lanzuan.entity.ProductSeries;

import java.util.List;

/**
 * Created by Administrator on 2015/7/3.
 */
public interface IProductPropertyService  extends IBaseEntityManager<ProductProperty> {
    List<ProductProperty> getProductPropertiesByProductSeriesId(String productSeriesId);

    void removeByProductSeries(ProductSeries productSeries);
}
