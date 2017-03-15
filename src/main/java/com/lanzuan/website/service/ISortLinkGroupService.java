package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.SortLinkGroup;

import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface ISortLinkGroupService extends IBaseEntityManager<SortLinkGroup> {
    SortLinkGroup findByUri(String uri, int index);
    SortLinkGroup findByUri(String uri);

//    List<ArticleSectionGroupItem> findArticleSectionByArticleId(String id);
//
//    List<ArticleSectionGroupItem> findHomePageArticleSections();
}
