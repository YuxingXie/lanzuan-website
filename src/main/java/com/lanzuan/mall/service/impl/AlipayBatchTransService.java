package com.lanzuan.mall.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.AlipayBatchTrans;
import com.lanzuan.entity.AlipayTrans;
import com.lanzuan.entity.User;
import com.lanzuan.mall.dao.AlipayBatchTransDao;
import com.lanzuan.mall.dao.AlipayTransDao;
import com.lanzuan.mall.service.IAlipayBatchTransService;
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
public class AlipayBatchTransService extends BaseEntityManager<AlipayBatchTrans> implements IAlipayBatchTransService {
    private static Logger logger = LogManager.getLogger();
    @Resource
    private AlipayBatchTransDao alipayBatchTransDao;
    protected EntityDao<AlipayBatchTrans> getEntityDao() {
        return this.alipayBatchTransDao;
    }


}
