package com.lanzuan.mall.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.Order;
import com.lanzuan.entity.ProductSeries;

import java.util.List;

/**
 * Created by Administrator on 2015/11/2.
 */
public interface IOrderService  extends IBaseEntityManager<Order> {
    Order findLastOrderByUserId(String userId);

    Order findOrderById(String orderId);
    void removeOrderInterval(long ago);

    void removeOrderById(String id);

    long findUnHandlerOrdersCount();

    List<Order> findUnHandlerOrders();

    List<Order> findOrdersByProductSeries(ProductSeries productSeries);
    List<Order> findOrdersByUserId(String userId);

    long findOrdersCountByProductSeries(ProductSeries productSeries);

    List<Order> findReturnExchangeOrders();

    long findReturnExchangeOrdersCount();
//    void insertOrder(User user);
}
