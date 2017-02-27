package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.Administrator;

/**
 * Created by Administrator on 2015/12/30.
 */
public interface IAdministratorService extends IBaseEntityManager<Administrator> {
    Administrator findByNameAndPassword(String name, String password);
}
