package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.FullWidthImage;
import com.lanzuan.entity.ImageTextBlockGroup;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface IFullWidthImageService extends IBaseEntityManager<FullWidthImage> {

    FullWidthImage findByUri(String uri);
}
