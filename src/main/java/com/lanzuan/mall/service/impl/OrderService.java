package com.lanzuan.mall.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.Order;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.mall.dao.OrderDao;
import com.lanzuan.mall.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/2.
 */
@Service
public class OrderService extends BaseEntityManager<Order> implements IOrderService{
    @Resource
    private OrderDao orderDao;
    protected EntityDao<Order> getEntityDao() {
        return this.orderDao;
    }

    @Override
    public Order findLastOrderByUserId(String userId) {
        return orderDao.findLastOrderByUserId(userId);
    }

    @Override
    public Order findOrderById(String orderId) {
        return orderDao.findOrderById(orderId) ;
    }

    @Override
    public void removeOrderInterval(long ago) {
        orderDao.removeOrderInterval(ago);
    }

    @Override
    public void removeOrderById(String id) {
        orderDao.removeOrderById(id);
    }

    @Override
    public long findUnHandlerOrdersCount() {
        return orderDao.findUnHandlerOrdersCount();
    }

    @Override
    public List<Order> findUnHandlerOrders() {
        return orderDao.findUnHandlerOrders();
    }

    @Override
    public List<Order> findOrdersByProductSeries(ProductSeries productSeries) {
        return orderDao.findOrdersByProductSeries(productSeries);
    }

    @Override
    public List<Order> findOrdersByUserId(String userId) {
        return orderDao.findOrdersByUserId( userId);
    }

    @Override
    public long findOrdersCountByProductSeries(ProductSeries productSeries) {
        return orderDao.findOrdersCountByProductSeries(productSeries);
    }

    @Override
    public List<Order> findReturnExchangeOrders() {
        return orderDao.findReturnExchangeOrders();
    }

    @Override
    public long findReturnExchangeOrdersCount() {
        return orderDao.findReturnExchangeOrdersCount();
    }

}
