package com.lanzuan.mall.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.HomePageBlock;
import com.lanzuan.entity.ProductSeries;

/**
 * Created by Administrator on 2015/12/30.
 */
public interface IHomePageBlockService  extends IBaseEntityManager<HomePageBlock> {
    void removeProductSeries(ProductSeries productSeries);
}
