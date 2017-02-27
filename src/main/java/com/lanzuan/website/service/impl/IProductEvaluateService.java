package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.Order;
import com.lanzuan.entity.ProductEvaluate;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2015/11/28.
 */
public interface IProductEvaluateService extends IBaseEntityManager<ProductEvaluate> {
    ProductEvaluate findByOrderAndProductSeries(Order order, ProductSeries productSeries);

    Page<ProductEvaluate> findProductEvaluatesPageByProductSeries(ProductSeries productSeries, Integer page, int pageSize);

    Page<ProductEvaluate> findProductEvaluatesPageWithoutParentEvaluateByProductSeries(ProductSeries productSeries, Integer page, int pageSize);

    List<ProductEvaluate> findEvaluatesWithParentId(String evaluateId);

    boolean praised(ProductEvaluate parent, User praiseUser);

    ProductEvaluate praisedEvaluate(ProductEvaluate parent, User praiseUser);

    void removeByProductSeries(ProductSeries productSeries);
}
