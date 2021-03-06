package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.Article;
import com.lanzuan.website.dao.ArticleDao;
import com.lanzuan.website.service.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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


    @Override
    public void praise(String id) {
        articleDao.praise(id);
    }

    @Override
    public Article increaseReadTimes(String id) {
        return articleDao.increaseReadTimes(id);
    }

    @Override
    public List<Article> findAllArticles() {
        return articleDao.findAllArticles();
    }
}
