package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.PageTemplate;
import com.lanzuan.website.dao.PageComponentDao;
import com.lanzuan.website.dao.PageTemplateDao;
import com.lanzuan.website.service.IPageComponentService;
import com.lanzuan.website.service.IPageTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class PageTemplateService extends BaseEntityManager<PageTemplate> implements IPageTemplateService {
    @Resource
    private PageTemplateDao pageTemplateDao;

    protected EntityDao<PageTemplate> getEntityDao() {
        return this.pageTemplateDao;
    }


    @Override
    public PageTemplate findByUri(String uri) {
        return pageTemplateDao.findByUri(uri);
    }
}
