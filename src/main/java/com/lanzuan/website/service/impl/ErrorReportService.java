package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.Article;
import com.lanzuan.entity.ErrorReport;
import com.lanzuan.website.dao.ArticleDao;
import com.lanzuan.website.dao.ErrorReportDao;
import com.lanzuan.website.service.IArticleService;
import com.lanzuan.website.service.IErrorReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class ErrorReportService extends BaseEntityManager<ErrorReport> implements IErrorReportService {
    @Resource
    private ErrorReportDao errorReportDao;

    protected EntityDao<ErrorReport> getEntityDao() {
        return this.errorReportDao;
    }


}
