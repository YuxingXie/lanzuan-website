package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.FriendshipExchange;
import com.lanzuan.website.dao.FriendshipExchangeDao;
import com.lanzuan.website.service.IFriendshipExchangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/12/15.
 */
@Service
public class FriendshipExchangeService extends BaseEntityManager<FriendshipExchange> implements IFriendshipExchangeService{
    @Resource
    private FriendshipExchangeDao friendshipExchangeDao;
    protected EntityDao<FriendshipExchange> getEntityDao() {
        return this.friendshipExchangeDao;
    }

}
