package com.lanzuan.mall.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.Interest;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public interface  IInterestService extends IBaseEntityManager<Interest> {
    List<Interest> findInterestsOfUser(User user);

    boolean alreadyInterested(User user, ProductSeries productSeries);

    List<Interest> findByUserAndProductSeries(User user, ProductSeries productSeries);

    List<Interest> findByProductSeries(ProductSeries productSeries);
}
