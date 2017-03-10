package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.CarouselItem;
import com.lanzuan.website.dao.CarouselItemDao;
import com.lanzuan.website.service.ICarouselItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class CarouselItemService extends BaseEntityManager<CarouselItem> implements ICarouselItemService {
    @Resource
    private CarouselItemDao carouselItemDao;

    protected EntityDao<CarouselItem> getEntityDao() {
        return this.carouselItemDao;
    }

}
