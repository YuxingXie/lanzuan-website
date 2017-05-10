package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ArticlesAndImages;
import com.lanzuan.entity.BrandIconGroup;
import com.lanzuan.website.dao.ArticlesAndImagesDao;
import com.lanzuan.website.dao.BrandIconGroupDao;
import com.lanzuan.website.service.IArticlesAndImagesService;
import com.lanzuan.website.service.IBrandIconGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class BrandIconGroupService extends BaseEntityManager<BrandIconGroup> implements IBrandIconGroupService{
    @Resource
    private BrandIconGroupDao brandIconGroupDao;
    protected EntityDao<BrandIconGroup> getEntityDao() {
        return this.brandIconGroupDao;
    }

    @Override
    public BrandIconGroup findCarouselByUri(String s) {
        return brandIconGroupDao.findCarouselByUri(s);
    }
}
