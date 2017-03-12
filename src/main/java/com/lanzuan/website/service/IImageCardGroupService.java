package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.Carousel;
import com.lanzuan.entity.ImageCardGroup;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface IImageCardGroupService extends IBaseEntityManager<ImageCardGroup> {

    ImageCardGroup findByUri(String uri);
}
