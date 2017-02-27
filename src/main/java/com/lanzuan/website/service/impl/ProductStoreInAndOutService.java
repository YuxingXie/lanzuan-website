package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.entity.ProductStoreInAndOut;
import com.lanzuan.website.dao.ProductStoreInAndOutDao;
import com.lanzuan.website.service.IProductStoreInAndOutService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductStoreInAndOutService extends BaseEntityManager<ProductStoreInAndOut> implements IProductStoreInAndOutService {
    @Resource
    private ProductStoreInAndOutDao productStoreInAndOutDao;
    @Override
    protected EntityDao<ProductStoreInAndOut> getEntityDao() {
        return this.productStoreInAndOutDao;
    }

    @Override
    public List<ProductStoreInAndOut> findByProductSeries(ProductSeries productSeries) {
        return productStoreInAndOutDao.findByProductSeries(productSeries);
    }
    @Override
    public void clearNullUserInAndOut(){
        productStoreInAndOutDao.clearNullUserInAndOut();
    }
}
