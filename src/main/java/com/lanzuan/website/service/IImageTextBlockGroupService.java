package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.ImageCardGroup;
import com.lanzuan.entity.ImageTextBlockGroup;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface IImageTextBlockGroupService extends IBaseEntityManager<ImageTextBlockGroup> {

    ImageTextBlockGroup findByUri(String uri);
}
