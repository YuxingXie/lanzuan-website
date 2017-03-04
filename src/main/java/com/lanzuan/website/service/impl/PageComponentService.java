package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.User;
import com.lanzuan.website.dao.PageComponentDao;
import com.lanzuan.website.dao.UserDao;
import com.lanzuan.website.service.IPageComponentService;
import com.lanzuan.website.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class PageComponentService extends BaseEntityManager<PageComponent> implements IPageComponentService {
    @Resource
    private PageComponentDao pageComponentDao;

    protected EntityDao<PageComponent> getEntityDao() {
        return this.pageComponentDao;
    }

    @Override
    public List<PageComponent> findHomePageComponents() {
        return pageComponentDao.findHomePageComponents();
    }
}
