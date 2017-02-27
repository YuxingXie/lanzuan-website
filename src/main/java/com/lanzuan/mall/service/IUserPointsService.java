package com.lanzuan.mall.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.UserPoints;

/**
 * Created by Administrator on 2015/11/11.
 */
public interface IUserPointsService extends IBaseEntityManager<UserPoints> {
    void addPointsToAllUser(int points);
}
