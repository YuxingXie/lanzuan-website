package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.Carousel;
import com.lanzuan.website.dao.CarouselDao;
import com.lanzuan.website.service.ICarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class CarouselService extends BaseEntityManager<Carousel> implements ICarouselService {
    @Resource
    private CarouselDao carouselDao;

    protected EntityDao<Carousel> getEntityDao() {
        return this.carouselDao;
    }


    @Override
    public Carousel findCarouselByUri(String uri) {
        return  carouselDao.findCarouselByUri(uri);
    }
}
