package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.TopCarousel;

/**
 * Created by Administrator on 2015/12/31.
 */
public interface ITopCarouselService extends IBaseEntityManager<TopCarousel> {
    TopCarousel findByMaxPriority();
}
