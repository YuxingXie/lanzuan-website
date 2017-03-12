package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ImageCardGroup;
import com.lanzuan.entity.Navbar;
import com.lanzuan.website.dao.ImageCardGroupDao;
import com.lanzuan.website.dao.NavbarDao;
import com.lanzuan.website.service.IImageCardGroupService;
import com.lanzuan.website.service.INavbarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class ImageCardGroupService extends BaseEntityManager<ImageCardGroup> implements IImageCardGroupService {
    @Resource
    private ImageCardGroupDao imageCardGroupDao;

    protected EntityDao<ImageCardGroup> getEntityDao() {
        return this.imageCardGroupDao;
    }


    @Override
    public ImageCardGroup findByUri(String uri) {
        return imageCardGroupDao.findByUri(uri);
    }
}
