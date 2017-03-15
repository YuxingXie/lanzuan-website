package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.CardGroup;
import com.lanzuan.entity.Carousel;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface ICardGroupService extends IBaseEntityManager<CardGroup> {

    CardGroup findByUri(String uri);
}
