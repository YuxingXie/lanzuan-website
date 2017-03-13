package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.entityfield.ArticleSection;

import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface IArticleSectionService extends IBaseEntityManager<ArticleSection> {

    List<ArticleSection> findArticleSectionByArticleId(String id);

    List<ArticleSection> findHomePageArticleSections();
}
