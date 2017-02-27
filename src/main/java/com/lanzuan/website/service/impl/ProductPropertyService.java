package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ProductProperty;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.website.dao.ProductPropertyDao;
import com.lanzuan.website.service.IProductPropertyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/7/3.
 */
@Service
public class ProductPropertyService extends BaseEntityManager<ProductProperty> implements IProductPropertyService {
    @Resource
    private ProductPropertyDao productPropertyDao;
    protected EntityDao<ProductProperty> getEntityDao() {
        return this.productPropertyDao;
    }

    @Override
    public List<ProductProperty> getProductPropertiesByProductSeriesId(String productSeriesId) {
        return productPropertyDao.getProductPropertiesByProductSeriesId(productSeriesId);
    }

    @Override
    public void removeByProductSeries(ProductSeries productSeries) {
        productPropertyDao.removeByProductSeries(productSeries);
    }
}
