package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.WebPage;
import com.lanzuan.website.dao.WebPageDao;
import com.lanzuan.website.service.IWebPageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class WebPageService extends BaseEntityManager<WebPage> implements IWebPageService {
    @Resource
    private WebPageDao webPageDao;

    protected EntityDao<WebPage> getEntityDao() {
        return this.webPageDao;
    }


    @Override
    public WebPage findByUri(String uri) {
        return webPageDao.findByUri(uri);
    }
}
