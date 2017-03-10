package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.PageTemplate;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface IPageTemplateService extends IBaseEntityManager<PageTemplate> {
    PageTemplate findByUri(String uri);
}
