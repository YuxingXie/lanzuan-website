package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ProductPropertyValue;
import com.lanzuan.website.dao.ProductPropertyValueDao;
import com.lanzuan.website.service.IProductPropertyValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/10/24.
 */
@Service
public class ProductPropertyValueService extends BaseEntityManager<ProductPropertyValue> implements IProductPropertyValueService {
    @Resource
    private ProductPropertyValueDao productPropertyValueDao;
    protected EntityDao<ProductPropertyValue> getEntityDao() {
        return this.productPropertyValueDao;
    }

}
