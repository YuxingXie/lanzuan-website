package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.entity.SalesCampaign;
import com.lanzuan.website.dao.SalesCampaignDao;
import com.lanzuan.website.service.ISalesCampaignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/1/7.
 */
@Service
public class SalesCampaignService extends BaseEntityManager<SalesCampaign> implements ISalesCampaignService {
    @Resource
    private SalesCampaignDao salesCampaignDao;
    protected EntityDao<SalesCampaign> getEntityDao() {
        return this.salesCampaignDao;
    }

    @Override
    public void removeProductSeries(ProductSeries productSeries) {
        salesCampaignDao.removeProductSeries(productSeries);
    }
}
