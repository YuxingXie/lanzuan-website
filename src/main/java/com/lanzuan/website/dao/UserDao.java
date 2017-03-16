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
    //单个插入
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

    /**
     *
     * @param user
     * @param maxRelativeLevel <0找上级，大于0找下级
     * @return
     */


    public List<User> findUpperUsers(User user, int maxRelativeLevel) {
        if (user==null ||user.getId()==null) return null;
        // membershipPath is  such as /aa/bb/bbc/dd/userId
        String membershipPath=user.getMembershipPath();
        if (membershipPath==null) return null;
        if (membershipPath.indexOf("/")<0) return null;
        if (membershipPath.indexOf(user.getId())<0) return null;
        if (membershipPath.startsWith("/"+user.getId())) return null;
        if (!membershipPath.endsWith("/"+user.getId())) return null;
        // upperUserIdsStr is  such as /aa/bb/bbc/dd
        String upperUserIdsStr=membershipPath.substring(0, membershipPath.indexOf("/"+user.getId()));
        DBObject dbObject=new BasicDBObject();
        BasicDBList dbList=new BasicDBList();
        for(int i=0;i<Math.abs(maxRelativeLevel);i++){
            String upperUserId=upperUserIdsStr.substring(upperUserIdsStr.lastIndexOf("/")+1);
            if (upperUserId.equals("")) break;
            upperUserIdsStr=upperUserIdsStr.substring(0,upperUserIdsStr.lastIndexOf("/"));
//            System.out.println(i+":"+upperUserId);
            dbList.add(new BasicDBObject("id", new ObjectId(upperUserId)));
            if (upperUserIdsStr.lastIndexOf("/")==0){
                dbList.add(new BasicDBObject("id", new ObjectId(upperUserIdsStr.substring(1))));
//                System.out.println("last id:"+upperUserIdsStr.substring(1));
                break;
            }
        }
        dbObject.put("$or",dbList);
        List<User> ret = findAll(dbObject);
        return ret;
    }

    public static  void main(String[] args){
    }






    public List<User> getDirectUpperUsers(List<User> newMemberUsers) {
        if (newMemberUsers==null||newMemberUsers.size()==0) return null;
        BasicDBList dbList=new BasicDBList();
        for (User newMemberUser:newMemberUsers){
            if (newMemberUser.getMembershipPath()==null) continue;
            if (newMemberUser.getMembershipPath().trim().equals("")) continue;
            if (newMemberUser.getMembershipPath().equalsIgnoreCase("/" + newMemberUser.getId())) continue;
            //membershipPath such as: /aaa/bbb/ccc/ddd/eee/id
            String membershipPath=newMemberUser.getMembershipPath();
            String abcde=membershipPath.substring(0,membershipPath.lastIndexOf("/"));
            String directUpperUserId=abcde.substring(abcde.lastIndexOf("/") + 1);
//            directUpperUserIds.add(new ObjectId(directUpperUserId));
            System.out.println(directUpperUserId);
            dbList.add(new ObjectId(directUpperUserId));
        }
        return findAll(new BasicDBObject("id",new BasicDBObject("$in",dbList)));
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
    public User getDirectUpperUser(User membershipUser) {
        if (membershipUser==null) return null;
        if (membershipUser.getMembershipPath()==null) return null;
        if (membershipUser.getMembershipPath().trim().equals(""))  return null;
        if (membershipUser.getMembershipPath().equalsIgnoreCase("/" + membershipUser.getId()))  return null;
        //membershipPath such as: /aaa/bbb/ccc/ddd/eee/id
        String membershipPath=membershipUser.getMembershipPath();
        String abcde=membershipPath.substring(0,membershipPath.lastIndexOf("/"));
        String directUpperUserId=abcde.substring(abcde.lastIndexOf("/") + 1);
        return findById(directUpperUserId);
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
