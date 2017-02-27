package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.AlipayBatchTrans;
import com.lanzuan.website.dao.AlipayBatchTransDao;
import com.lanzuan.website.service.IAlipayBatchTransService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
