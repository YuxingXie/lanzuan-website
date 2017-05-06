package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.Article;
import com.lanzuan.entity.ArticlesAndImages;
import com.lanzuan.website.dao.ArticleDao;
import com.lanzuan.website.dao.ArticlesAndImagesDao;
import com.lanzuan.website.service.IArticleService;
import com.lanzuan.website.service.IArticlesAndImagesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class ArticlesAndImagesService extends BaseEntityManager<ArticlesAndImages> implements IArticlesAndImagesService{
    @Resource
    private ArticlesAndImagesDao articlesAndImagesDao;

    protected EntityDao<ArticlesAndImages> getEntityDao() {
        return this.articlesAndImagesDao;
    }

    @Override
    public ArticlesAndImages findByUri(String s) {
        return articlesAndImagesDao.findByUri(s);
    }
}
