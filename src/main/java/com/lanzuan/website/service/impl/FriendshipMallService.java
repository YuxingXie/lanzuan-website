package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.FriendshipMall;
import com.lanzuan.website.dao.FriendshipMallDao;
import com.lanzuan.website.service.IFriendshipMallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/12/15.
 */
@Service
public class FriendshipMallService extends BaseEntityManager<FriendshipMall> implements IFriendshipMallService{
    @Resource
    private FriendshipMallDao friendshipMallDao;
    protected EntityDao<FriendshipMall> getEntityDao() {
        return this.friendshipMallDao;
    }

}
