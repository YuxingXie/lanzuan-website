package com.lanzuan.mall.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.AuthorizeInfo;
import com.lanzuan.mall.dao.AuthorizeInfoDao;
import com.lanzuan.mall.service.IAuthorizeInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/11.
 */
@Service
public class AuthorizeInfoService extends BaseEntityManager<AuthorizeInfo> implements IAuthorizeInfoService {
    private static Logger logger = LogManager.getLogger();
    @Resource
    private AuthorizeInfoDao authorizeInfoDao;
    protected EntityDao<AuthorizeInfo> getEntityDao() {
        return this.authorizeInfoDao;
    }


}
