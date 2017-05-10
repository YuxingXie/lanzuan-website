package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.BrandIconGroup;

/**
 * Created by Administrator on 2017/5/8.
 */
public interface IBrandIconGroupService extends IBaseEntityManager<BrandIconGroup> {

    BrandIconGroup findCarouselByUri(String s);
}
