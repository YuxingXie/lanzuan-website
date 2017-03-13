package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.WebPage;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface IWebPageService extends IBaseEntityManager<WebPage> {
    WebPage findByUri(String uri);
}
