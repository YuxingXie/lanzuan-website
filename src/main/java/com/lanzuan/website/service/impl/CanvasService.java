package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.Article;
import com.lanzuan.entity.Canvas;
import com.lanzuan.website.dao.ArticleDao;
import com.lanzuan.website.dao.CanvasDao;
import com.lanzuan.website.service.IArticleService;
import com.lanzuan.website.service.ICanvasService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class CanvasService extends BaseEntityManager<Canvas> implements ICanvasService {
    @Resource
    private CanvasDao canvasDao;

    protected EntityDao<Canvas> getEntityDao() {
        return this.canvasDao;
    }


}
