package com.lanzuan.mall.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.entity.SalesCampaign;

/**
 * Created by Administrator on 2016/1/7.
 */
public interface ISalesCampaignService extends IBaseEntityManager<SalesCampaign> {
    void removeProductSeries(ProductSeries productSeries);
}
