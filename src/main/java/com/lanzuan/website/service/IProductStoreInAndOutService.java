package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.entity.ProductStoreInAndOut;

import java.util.List;

/**
 * Created by Administrator on 2015/11/23.
 */
public interface IProductStoreInAndOutService extends IBaseEntityManager<ProductStoreInAndOut> {
    List<ProductStoreInAndOut> findByProductSeries(ProductSeries productSeries);
    void clearNullUserInAndOut();
}
