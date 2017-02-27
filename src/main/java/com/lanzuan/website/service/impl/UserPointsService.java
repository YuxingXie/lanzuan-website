package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.UserPoints;
import com.lanzuan.website.dao.UserPointsDao;
import com.lanzuan.website.service.IUserPointsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/11.
 */
@Service
public class UserPointsService extends BaseEntityManager<UserPoints> implements IUserPointsService{
    private static Logger logger = LogManager.getLogger();
    @Resource
    private UserPointsDao userPointsDao;
    protected EntityDao<UserPoints> getEntityDao() {
        return this.userPointsDao;
    }


    @Override
    public void addPointsToAllUser(int points) {
        userPointsDao.addPointsToAllUser(points);
    }
}
