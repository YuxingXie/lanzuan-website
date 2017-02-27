package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.Notify;
import com.lanzuan.website.dao.NotifyDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/12/21.
 */
@Service
public class NotifyService extends BaseEntityManager<Notify> implements INotifyService {
    @Resource
    private NotifyDao notifyDao;
    protected EntityDao<Notify> getEntityDao() {
        return this.notifyDao;
    }
}
