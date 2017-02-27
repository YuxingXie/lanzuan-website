package com.lanzuan.mall.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.HomePageBlock;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.mall.dao.HomePageBlockDao;
import com.lanzuan.mall.service.IHomePageBlockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/12/30.
 */
@Service
public class HomePageBlockService extends BaseEntityManager<HomePageBlock> implements IHomePageBlockService {
    @Resource
    private HomePageBlockDao homePageBlockDao;
    protected EntityDao<HomePageBlock> getEntityDao() {
        return this.homePageBlockDao;
    }

    @Override
    public void removeProductSeries(ProductSeries productSeries) {
        homePageBlockDao.removeProductSeries(productSeries);
    }
}
