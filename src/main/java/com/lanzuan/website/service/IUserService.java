package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.User;
import com.lanzuan.support.vo.Message;

import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface IUserService extends IBaseEntityManager<User> {


    void insertUser(User user);

}
