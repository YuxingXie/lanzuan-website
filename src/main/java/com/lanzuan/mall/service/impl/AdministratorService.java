package com.lanzuan.mall.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.Administrator;
import com.lanzuan.mall.dao.AdministratorDao;
import com.lanzuan.mall.service.IAdministratorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/12/30.
 */
@Service
public class AdministratorService  extends BaseEntityManager<Administrator> implements IAdministratorService {
    @Resource
    private AdministratorDao administratorDao;
    protected EntityDao<Administrator> getEntityDao() {
        return this.administratorDao;
    }

    @Override
    public Administrator findByNameAndPassword(String name, String password) {
        return administratorDao.findByNameAndPassword(name,password);
    }
}
