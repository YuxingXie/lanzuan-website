package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ArticleSection;
import com.lanzuan.entity.Navbar;
import com.lanzuan.website.dao.ArticleSectionDao;
import com.lanzuan.website.dao.NavbarDao;
import com.lanzuan.website.service.IArticleSectionService;
import com.lanzuan.website.service.INavbarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class NavbarService extends BaseEntityManager<Navbar> implements INavbarService {
    @Resource
    private NavbarDao navbarDao;

    protected EntityDao<Navbar> getEntityDao() {
        return this.navbarDao;
    }


    @Override
    public Navbar findByUri(String uri) {
        return navbarDao.findByUri(uri);
    }
}
