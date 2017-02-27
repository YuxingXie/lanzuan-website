package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.Bank;
import com.lanzuan.website.dao.BankDao;
import com.lanzuan.website.service.IBankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/12/15.
 */
@Service
public class BankService extends BaseEntityManager<Bank> implements IBankService{
    @Resource
    private BankDao bankDao;
    protected EntityDao<Bank> getEntityDao() {
        return this.bankDao;
    }

}
