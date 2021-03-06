package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.Article;

import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface IArticleService extends IBaseEntityManager<Article> {

    void praise(String id);

    Article increaseReadTimes(String id);

    List<Article> findAllArticles();
}
