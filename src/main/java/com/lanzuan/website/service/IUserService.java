package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.User;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface IUserService extends IBaseEntityManager<User> {


    void insertUser(User user);

    User findByEmail(String email);
    User findByPhone(String phone);
    User findByName(String name);

    User findByLoginName(String loginName);
}
