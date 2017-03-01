package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.User;
import com.lanzuan.website.dao.UserDao;
import com.lanzuan.website.service.IUserService;
import com.lanzuan.support.vo.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class UserService extends BaseEntityManager<User> implements IUserService {
    @Resource
    private UserDao userDao;




    protected EntityDao<User> getEntityDao() {
        return this.userDao;
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }


}
