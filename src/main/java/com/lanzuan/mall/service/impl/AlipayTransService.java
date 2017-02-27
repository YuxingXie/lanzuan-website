package com.lanzuan.mall.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.AlipayTrans;
import com.lanzuan.entity.User;
import com.lanzuan.mall.dao.AlipayTransDao;
import com.lanzuan.mall.service.IAlipayTransService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 */
@Service
public class AlipayTransService extends BaseEntityManager<AlipayTrans> implements IAlipayTransService {
    private static Logger logger = LogManager.getLogger();
    @Resource
    private AlipayTransDao alipayTransDao;
    protected EntityDao<AlipayTrans> getEntityDao() {
        return this.alipayTransDao;
    }



    @Override
    public List<AlipayTrans> findSubmittedTransByUser(User user) {
        return alipayTransDao.findSubmittedTransByUser(user);
    }

    @Override
    public List<AlipayTrans> findSubmittedAndNotSendToAlipayTrans() {
        return alipayTransDao.findSubmittedAndNotSendToAlipayTrans();
    }

    @Override
    public List<AlipayTrans> findAlipayTransFinished() {
        return alipayTransDao.findAlipayTransFinished();
    }
}
