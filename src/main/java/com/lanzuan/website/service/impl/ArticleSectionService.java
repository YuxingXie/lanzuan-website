package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ArticleSection;
import com.lanzuan.entity.PageTemplate;
import com.lanzuan.website.dao.ArticleSectionDao;
import com.lanzuan.website.dao.PageTemplateDao;
import com.lanzuan.website.service.IArticleSectionService;
import com.lanzuan.website.service.IPageTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class ArticleSectionService extends BaseEntityManager<ArticleSection> implements IArticleSectionService {
    @Resource
    private ArticleSectionDao articleSectionDao;

    protected EntityDao<ArticleSection> getEntityDao() {
        return this.articleSectionDao;
    }


    @Override
    public List<ArticleSection> findArticleSectionByArticleId(String id) {
        return articleSectionDao.findArticleSectionByArticleId(id);
    }

    @Override
    public List<ArticleSection> findHomePageArticleSections() {
        return articleSectionDao.findHomePageArticleSections();
    }
}
