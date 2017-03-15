package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.CardGroup;
import com.lanzuan.website.dao.CardGroupDao;
import com.lanzuan.website.service.ICardGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class CardGroupService extends BaseEntityManager<CardGroup> implements ICardGroupService {
    @Resource
    private CardGroupDao cardGroupDao;

    protected EntityDao<CardGroup> getEntityDao() {
        return this.cardGroupDao;
    }


    @Override
    public CardGroup findByUri(String uri) {
        return cardGroupDao.findByUri(uri);
    }
}
