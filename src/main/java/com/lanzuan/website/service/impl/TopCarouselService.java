package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.TopCarousel;
import com.lanzuan.website.dao.TopCarouselDao;
import com.lanzuan.website.service.ITopCarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/12/31.
 */
@Service
public class TopCarouselService extends BaseEntityManager<TopCarousel> implements ITopCarouselService {
    @Resource
    private TopCarouselDao topCarouselDao;
    protected EntityDao<TopCarousel> getEntityDao() {
        return this.topCarouselDao;
    }

    @Override
    public TopCarousel findByMaxPriority() {
        return topCarouselDao.findByMaxPriority();
    }
}
