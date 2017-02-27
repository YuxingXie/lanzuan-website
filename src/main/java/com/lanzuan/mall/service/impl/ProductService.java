package com.lanzuan.mall.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.mall.dao.ProductSeriesDao;
import com.lanzuan.mall.service.IProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/7/1.
 */
@Service
public class ProductService extends BaseEntityManager<ProductSeries> implements IProductService {
    @Resource
    private ProductSeriesDao productSeriesDao;
    protected EntityDao<ProductSeries> getEntityDao() {
        return this.productSeriesDao;
    }
}
