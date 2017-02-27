package com.lanzuan.website.service;

import com.lanzuan.common.base.IBaseEntityManager;
import com.lanzuan.entity.Account;
import com.lanzuan.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 */
public interface IAccountService extends IBaseEntityManager<Account> {
    Account findAccountsByUserIdAndCardNo(String userId, String cardNo);
    List<Account> findAccountsByUser(User user);
}
