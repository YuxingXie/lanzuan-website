package com.lanzuan.mall.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.Cart;
import com.mongodb.DBObject;

/**
 * Created by Administrator on 2015/7/29.
 */
public interface ICartService extends IBaseEntityManager<Cart> {
    Cart getCartByUserId(String id);

    Cart findOne(DBObject condition);
}
