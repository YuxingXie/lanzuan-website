package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.entity.Article;
import com.lanzuan.entity.VisitInfo;
import com.lanzuan.website.dao.ArticleDao;
import com.lanzuan.website.dao.VisitInfoDao;
import com.lanzuan.website.service.IArticleService;
import com.lanzuan.website.service.IVisitInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class VisitInfoService extends BaseEntityManager<VisitInfo> implements IVisitInfoService {
    @Resource
    private VisitInfoDao visitInfoDao;

    protected EntityDao<VisitInfo> getEntityDao() {
        return this.visitInfoDao;
    }


    @Override
    public void print() {
        System.out.println(Constant.totalClick);
        System.out.println(Constant.totalVisit);
    }
}
