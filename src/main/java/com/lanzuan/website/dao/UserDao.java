package com.lanzuan.website.dao;

import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.common.util.BusinessException;
import com.lanzuan.entity.User;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/5/22.
 */
@Repository
public class UserDao extends BaseMongoDao<User>  {
    private static Logger logger = LogManager.getLogger();
    @Resource
    private MongoOperations mongoTemplate;


    /**
     * 获得所有的user
     */

    public List<User> getAllObjects() {
        return mongoTemplate.findAll(User.class);
    }

    /**
     * 保存一个user对象
     */

    public void saveObject(User user) {
        mongoTemplate.insert(user);
    }

    /**
     * 通过id进行查找
     */


    public User getObject(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where(id).is(id)),
                User.class);
    }

    /**
     * 根据id和name进行查找
     */

    public WriteResult updateObject(String id, String name) {
        return mongoTemplate.updateFirst(
                new Query(Criteria.where(id).is(id)),
                Update.update(name, name), User.class);
    }

    /**
     * 根据id删除user
     */

    public void deleteObject(String id) {
        mongoTemplate
                .remove(new Query(Criteria.where(id).is(id)), User.class);
    }

    /**
     * 如果collection不存在则建立
     */

    public void createCollection() {
        if (!mongoTemplate.collectionExists(User.class))
            mongoTemplate.createCollection(User.class);
    }

    /**
     * 如果collection存在则删除之
     */


    public void dropCollection() {
        if (mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.dropCollection(User.class);
        }
    }


    public User findByNameAndPwd(String loginName, String loginPwd) {
        User user=new User();
        user.setName(loginName);
        user.setPassword(loginPwd);//密码123
        return findOne(user);
    }

    public User findByName(String name) {
        return findByName(name, null);
    }
    public User findByEmail(String email) {
        return findOne(new BasicDBObject("email",email));
    }
    public User findByPhone(String phone) {
        return findOne(new BasicDBObject("phone",phone));
    }
    private User findByName(String name,Boolean activated) {
        DBObject dbObject=new BasicDBObject("name",name);
//        if (activated!=null){
//            dbObject.put("activated",activated);
//        }
        List<User> users=findAll(dbObject);
        if (users!=null &&users.size()>1){
            throw new BusinessException("用户名在系统中不是唯一的");
        }
        if (users==null ||users.size()==0){
            return null;

        }
        return users.get(0);
    }

    public User findByLoginName(String loginName) {
        DBObject dbObject=new BasicDBObject("loginName",loginName);

        List<User> users=findAll(dbObject);
        if (users!=null &&users.size()>1){
            throw new BusinessException("用户名在系统中不是唯一的");
        }
        if (users==null ||users.size()==0){
            return null;

        }
        return users.get(0);
    }

    public User findByEmail(String email,boolean activated) {
        DBObject dbObject=new BasicDBObject("email",email);
        if (!activated){
            BasicDBList dbList=new BasicDBList();
            dbList.add(new BasicDBObject("activated",activated));
            dbList.add(new BasicDBObject("activated",new BasicDBObject("$exists",false)));
            dbObject.put("$or",dbList);
        }else {
            dbObject.put("activated",activated);
        }
        return findOne(dbObject);
    }
    public User findByPhone(String phone,boolean activated) {
        DBObject dbObject=new BasicDBObject("phone",phone);
        dbObject.put("activated",activated);
        return findOne(dbObject);
    }
    public boolean isNameUsed(String name) {
        User user=findByName(name,true);
        if (user==null) return false;
        return true;
    }

    /**
     * 用户改昵称时判断昵称是否可用
     * @param name 新昵称
     * @param userId 用户Id
     * @return
     */
    public boolean isNameUsed(String name, String userId) {
        DBObject dbObject=new BasicDBObject();
        dbObject.put("_id", new BasicDBObject("$ne",userId));
        dbObject.put("activated", true);
        dbObject.put("name", name);
        List<User> users=mongoTemplate.find(new BasicQuery(dbObject),User.class);
        return users!=null&&users.size()>0;
    }

    /**
     * 用户邮箱称时判断邮箱是否可用
     * @param email
     * @param userId
     * @return
     */
    public boolean isEmailUsed(String email, String userId) {
        DBObject dbObject=new BasicDBObject();
        dbObject.put("_id", new BasicDBObject("$ne",userId));
        dbObject.put("activated", true);
        dbObject.put("email", email);
        List<User> users=mongoTemplate.find(new BasicQuery(dbObject),User.class);
        return users!=null&&users.size()>0;
    }
    public boolean isPhoneUsed(String phone, String userId) {
        DBObject dbObject=new BasicDBObject();
        dbObject.put("_id", new BasicDBObject("$ne",userId));
        dbObject.put("activated", true);
        dbObject.put("phone", phone);
        List<User> users=mongoTemplate.find(new BasicQuery(dbObject),User.class);
        return users!=null&&users.size()>0;
    }
    public boolean isEmailUsed(String email) {
        User user=findByEmail(email,true);
        if (user==null) return false;
//        if (user.getStatus()!=null && user.getStatus()==0) return false;
        return true;
    }

    public boolean isPhoneUsed(String phone) {
        User user=findByPhone(phone,true);
        if (user==null) return false;
        if (user.getActivated()==null) return false;
        if (!user.getActivated()) return false;
        return true;
    }




    public void clearCart(User user) {
        Assert.notNull(user.getId());
        Update update=new Update();
        update.set("cart", null);
        mongoTemplate.updateFirst(new BasicQuery(new BasicDBObject("_id",user.getId())), update, User.class);
    }






    public void insertUser(User user) {
        ObjectId id=new ObjectId();
        user.setId(id.toString());
        mongoTemplate.insert(user);
    }


    public static  void main(String[] args){
    }




//TODO 有问题
    public List<User> findAllLowerUsers(User user) {
        if (user==null) return null;
        if (user.getId()==null) return null;
        if (user.getId().trim().equals("")) return null;
        return mongoTemplate.find(new Query(new Criteria("membershipPath").regex(".*?" + user.getId() + ".*")), User.class);
//        return mongoTemplate.find(new Query(new Criteria("membershipPath").regex(".*?" + user.getId() + ".*").and("directSaleMember").is(true)), User.class);
    }

    public long findAllLowerUsersCount(User user) {
        if (user==null) return 0;
        if (user.getId()==null) return 0;
        if (user.getId().trim().equals("")) return 0;
        return mongoTemplate.count(new Query(new Criteria("membershipPath").regex(".*?" + user.getId() + ".*")), User.class);
    }

    public List<User> findAllLowerMemberUsers(User user) {
        if (user==null) return null;
        if (user.getId()==null) return null;
        if (user.getId().trim().equals("")) return null;
        return mongoTemplate.find(new Query(new Criteria("membershipPath").regex(".*?" + user.getId() + ".*").and("directSaleMember").is(true)), User.class);
    }

    public long findAllLowerMemberUsersCount(User user) {
        if (user==null) return 0;
        if (user.getId()==null) return 0;
        if (user.getId().trim().equals("")) return 0;
        return mongoTemplate.count(new Query(new Criteria("membershipPath").regex(".*?" + user.getId() + ".*").and("membershipPath").ne("/"+user.getId())), User.class);
//        return mongoTemplate.count(new Query(new Criteria("membershipPath").regex(".*?" + user.getId() + ".*").and("directSaleMember").is(true)), User.class);
    }
    public long findAllLowerMemberUsersCountBeforeADate(User user,Date date) {
        if (user==null) return 0;
        if (user.getId()==null) return 0;
        if (user.getId().trim().equals("")) return 0;
        return mongoTemplate.count(new Query(new Criteria("membershipPath").regex(".*?" + user.getId() + ".*").and("membershipPath").ne("/"+user.getId()).and("becomeMemberDate").lt(date)), User.class);
//        return mongoTemplate.count(new Query(new Criteria("membershipPath").regex(".*?" + user.getId() + ".*").and("directSaleMember").is(true)), User.class);
    }




    public User findFirstMember() {
        DBObject obj = new BasicDBObject();
        BasicDBList dbList=new BasicDBList();
        dbList.add(new BasicDBObject("membershipPath",new BasicDBObject("$exists",false)));
        dbList.add(new BasicDBObject("$where", "this.membershipPath == '/'+this._id"));
        obj.put("$or", dbList);
        Query query = new BasicQuery(obj);
        return mongoTemplate.findOne(query, User.class);
    }

    public List<User> findAllMembers() {
        return findAll();
    }



}
