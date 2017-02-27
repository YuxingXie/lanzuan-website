package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.AlipayTrans;
import com.lanzuan.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 */
public interface IAlipayTransService extends IBaseEntityManager<AlipayTrans> {
    List<AlipayTrans> findSubmittedTransByUser(User user);

    List<AlipayTrans> findSubmittedAndNotSendToAlipayTrans();

    List<AlipayTrans> findAlipayTransFinished();

}
