package com.lanzuan.website.dao;


import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.common.code.AlipayTransStatusEnum;
import com.lanzuan.common.helper.service.ServiceManager;
import com.lanzuan.entity.Account;
import com.lanzuan.entity.AlipayTrans;
import com.lanzuan.entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 */
@Repository
public class AlipayTransDao extends BaseMongoDao<AlipayTrans> {
    private static Logger logger = LogManager.getLogger();
    @Resource
    private MongoOperations mongoTemplate;


    public List<AlipayTrans> findSubmittedTransByUser(User user) {
        //db.mallUser.find({"account.user":new DBRef()})
        DBObject dbObject=new BasicDBObject();
        List<Account> userAccounts= ServiceManager.accountService.findAccountsByUser(user);
        dbObject.put("account",new BasicDBObject("$in",userAccounts));
        dbObject.put("alipayTransStatus", AlipayTransStatusEnum.SUBMITTED.toCode());
        return findAll(dbObject);
    }

    public List<AlipayTrans> findSubmittedAndNotSendToAlipayTrans() {
        DBObject dbObject=new BasicDBObject();
        dbObject.put("alipayTransStatus", AlipayTransStatusEnum.SUBMITTED.toCode());
        dbObject.put("alipayBatchTrans", new BasicDBObject("$exists",false));
        return findAll(new BasicQuery(dbObject).with(new Sort(Sort.Direction.DESC,"date")));
    }

    public List<AlipayTrans> findAlipayTransFinished() {
        DBObject dbObject=new BasicDBObject();
        dbObject.put("alipayTransStatus", AlipayTransStatusEnum.SUBMITTED.toCode());
        dbObject.put("alipayBatchTrans", new BasicDBObject("$exists",true));
        return findAll(new BasicQuery(dbObject).with(new Sort(Sort.Direction.DESC,"date")));
    }
}
