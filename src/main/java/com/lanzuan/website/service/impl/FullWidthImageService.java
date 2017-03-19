package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.FullWidthImage;
import com.lanzuan.website.dao.FullWidthImageDao;
import com.lanzuan.website.service.IFullWidthImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class FullWidthImageService extends BaseEntityManager<FullWidthImage> implements IFullWidthImageService {
    @Resource
    private FullWidthImageDao fullWidthImageDao;

    protected EntityDao<FullWidthImage> getEntityDao() {
        return this.fullWidthImageDao;
    }


    @Override
    public FullWidthImage findByUri(String uri) {
        return fullWidthImageDao.findByUri(uri);
    }
}
