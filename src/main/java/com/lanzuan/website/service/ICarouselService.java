package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.Carousel;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface ICarouselService extends IBaseEntityManager<Carousel> {

    Carousel findCarouselByUri(String uri);
}
