package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.Article;
import com.lanzuan.entity.Navbar;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface INavbarService extends IBaseEntityManager<Navbar> {

    Navbar findByUri(String uri);
}
