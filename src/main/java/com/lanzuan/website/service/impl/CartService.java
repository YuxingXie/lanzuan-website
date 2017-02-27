package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.Cart;
import com.lanzuan.website.dao.CartDao;
import com.lanzuan.website.service.ICartService;
import com.mongodb.DBObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/7/29.
 */
@Service
public class CartService extends BaseEntityManager<Cart> implements ICartService {
    @Resource
    private CartDao cartDao;
    protected EntityDao<Cart> getEntityDao() {
        return this.cartDao;
    }

    @Override
    public Cart getCartByUserId(String id) {
        return cartDao.getCartByUserId(id);
    }

    @Override
    public Cart findOne(DBObject condition) {
        return cartDao.findOne(condition);
    }
}
