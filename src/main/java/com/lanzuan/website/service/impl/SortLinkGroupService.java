package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.SortLinkGroup;
import com.lanzuan.website.dao.SortLinkGroupDao;
import com.lanzuan.website.service.ISortLinkGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
public class SortLinkGroupService extends BaseEntityManager<SortLinkGroup> implements ISortLinkGroupService {
    @Resource
    private SortLinkGroupDao sortLinkGroupDao;

    protected EntityDao<SortLinkGroup> getEntityDao() {
        return this.sortLinkGroupDao;
    }


    @Override
    public SortLinkGroup findByUri(String uri, int index) {
        return sortLinkGroupDao.findByUri(uri,index);
    }

    @Override
    public SortLinkGroup findByUri(String uri) {
        return sortLinkGroupDao.findByUri(uri);
    }

    @Override
    public List<SortLinkGroup> findByUriAndIndex(String uri, int index) {
        return sortLinkGroupDao.findByUriAndIndex(uri, index);
    }
}
