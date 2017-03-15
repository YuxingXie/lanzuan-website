package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ImageTextBlockGroup;
import com.lanzuan.website.dao.ImageTextBlockGroupDao;
import com.lanzuan.website.service.IImageTextBlockGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class ImageTextBlockGroupService extends BaseEntityManager<ImageTextBlockGroup> implements IImageTextBlockGroupService {
    @Resource
    private ImageTextBlockGroupDao imageTextBlockGroupDao;

    protected EntityDao<ImageTextBlockGroup> getEntityDao() {
        return this.imageTextBlockGroupDao;
    }


    @Override
    public ImageTextBlockGroup findByUri(String uri) {
        return imageTextBlockGroupDao.findByUri(uri);
    }
}
