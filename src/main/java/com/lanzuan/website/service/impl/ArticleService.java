package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.Article;
import com.lanzuan.website.dao.ArticleDao;
import com.lanzuan.website.service.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class ArticleService extends BaseEntityManager<Article> implements IArticleService {
    @Resource
    private ArticleDao articleDao;

    protected EntityDao<Article> getEntityDao() {
        return this.articleDao;
    }


}
