package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.PageComponent;

import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface IPageComponentService extends IBaseEntityManager<PageComponent> {
    List<PageComponent> findHomePageComponents();
}
