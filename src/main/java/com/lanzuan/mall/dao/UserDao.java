package com.lanzuan.mall.dao;

import com.lanzuan.common.base.BaseMongoDao;
import com.lanzuan.common.code.NotifyTypeCodeEnum;
import com.lanzuan.common.directSale.util.UserRelationship;
import com.lanzuan.common.helper.service.ServiceManager;
import com.lanzuan.common.util.MD5;
import com.lanzuan.entity.*;
import com.lanzuan.support.vo.Message;
import com.lanzuan.common.support.Pair;
import com.lanzuan.support.yexin.DirectSalePairTouchMode;
import com.mongodb.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private DirectSalePairTouchMode directSalePairTouchMode;

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

    private User findByName(String name) {
        return findOne(new BasicDBObject("name",name));
    }
    public User findByEmail(String email) {
        return findOne(new BasicDBObject("email",email));
    }
    public User findByPhone(String phone) {
        return findOne(new BasicDBObject("phone",phone));
    }
    private User findByName(String name,boolean activated) {
        DBObject dbObject=new BasicDBObject("name",name);
        dbObject.put("activated",activated);
        return findOne(dbObject);
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

    public User findByEmailOrPhone(String emailOrPhone) {
        DBObject queryCondition = new BasicDBObject();
        queryCondition = new BasicDBObject();
        BasicDBList values = new BasicDBList();
        values.add(new BasicDBObject("phone", emailOrPhone));
        values.add(new BasicDBObject("email", emailOrPhone));
        queryCondition.put("$or", values);
        Query query=new BasicQuery(queryCondition);
//        return mongoTemplate.findOne(query,User.class);
//        User user=findOne(queryCondition);
        User user=mongoTemplate.findOne(query,User.class);
        if (user!=null){
            Cart cart=user.getCart();
            if (cart!=null){
                List<ProductSelected> productSelectedList=cart.getProductSelectedList();
                if (productSelectedList!=null){
                    for (ProductSelected productSelected:productSelectedList){
                        ProductSeries productSeries= productSelected.getProductSeries();
                        if (productSeries==null) continue;
//                        productSeries.setProductSeriesPrices(ServiceManager.productSeriesPriceService.findByProductSeriesId(productSeries.getId()));

//                        productSelected.setProductSeries(productSeries);
                        List<String> productPropertyValueIds=productSelected.getProductPropertyValueIds();
                        if (productPropertyValueIds!=null){
                            List<ProductPropertyValue> productPropertyValueList=new ArrayList<ProductPropertyValue>();
                            for (String productPropertyValueId:productPropertyValueIds){
                                ProductPropertyValue productPropertyValue=ServiceManager.productPropertyValueService.findById(productPropertyValueId);
                                productPropertyValueList.add(productPropertyValue);
                            }
                            productSelected.setProductPropertyValueList(productPropertyValueList);
                        }
                    }
                }
            }
        }
        return user;
    }


    public void clearCart(User user) {
        Assert.notNull(user.getId());
        Update update=new Update();
        update.set("cart", null);
        mongoTemplate.updateFirst(new BasicQuery(new BasicDBObject("_id",user.getId())), update, User.class);
    }


    public List<User> findUsersByProductSeriesInCart(ProductSeries productSeries) {
        DBObject dbObject=new BasicDBObject();
        dbObject.put("cart.productSelectedList.productSeries",productSeries);
        return getMongoTemplate().find(new BasicQuery(dbObject),User.class);
    }

    public User findByTencentOpenId(String openId) {
        DBObject dbObject=new BasicDBObject();
        dbObject.put("tencentLoginInfo.openId",openId);
        return getMongoTemplate().findOne(new BasicQuery(dbObject),User.class);
    }
       public User findInviteUserByPhoneAndInviteCode(String phone, String inviteCode) {
        DBObject condition=new BasicDBObject();
        condition.put("phone",phone);
        condition.put("inviteCode",inviteCode);
        AuthorizeInfo authorizeInfo= getMongoTemplate().findOne(new BasicQuery(condition),AuthorizeInfo.class);
        if (authorizeInfo==null) return null;
        return authorizeInfo.getUser();
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
    public List<User> findLowerOrUpperUsers(User user,int maxRelativeLevel) {
        if (user==null) return null;
        if (user.getId()==null) return null;
        if (maxRelativeLevel<0)
            return findUpperUsers(user,maxRelativeLevel);
        List<User> users=findAllLowerUsers(user);
        if (users==null||users.size()==0) return null;
        List<User> ret = new ArrayList<User>();
        for(User member :users){
            if (user.getId().equalsIgnoreCase(member.getId())){
                continue;
            }
            String membershipPath= member.getMembershipPath();
            if (membershipPath.indexOf(member.getId())<0){
                continue;//没有包含自己id的路径是程序错误导致的，忽略
            }
            UserRelationship userRelationship=new UserRelationship(user,member);
            int relativeLevel=userRelationship.getRelativeLevel();
            if (maxRelativeLevel<0){
                if(relativeLevel<0 && relativeLevel>=maxRelativeLevel){
                    member.setRelativeLevel(relativeLevel);
                    ret.add(member);
                }
            }else{
                if(relativeLevel>0 &&relativeLevel<=maxRelativeLevel){
                    member.setRelativeLevel(relativeLevel);
                    ret.add(member);
                }
            }
        }
        if (ret.size()==0) return null;
        return ret;

    }

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
    public User findByEmailOrPhoneAndPassword(String loginStr, String password) {
        DBObject queryCondition = new BasicDBObject();
        queryCondition = new BasicDBObject();
        BasicDBList values = new BasicDBList();
        values.add(new BasicDBObject("phone", loginStr));
        values.add(new BasicDBObject("email", loginStr));
        queryCondition.put("$or", values);
        queryCondition.put("password", MD5.convert(password));
        Query query=new BasicQuery(queryCondition);
//        return mongoTemplate.findOne(query,User.class);
//        User user=findOne(queryCondition);
        User user=mongoTemplate.findOne(query,User.class);
        if (user!=null){
            Cart cart=user.getCart();
            if (cart!=null){
                List<ProductSelected> productSelectedList=cart.getProductSelectedList();
                if (productSelectedList!=null){
                    for (ProductSelected productSelected:productSelectedList){
                        ProductSeries productSeries= productSelected.getProductSeries();
                        if (productSeries==null) continue;
//                        productSeries.setProductSeriesPrices(ServiceManager.productSeriesPriceService.findByProductSeriesId(productSeries.getId()));

//                        productSelected.setProductSeries(productSeries);
                        List<String> productPropertyValueIds=productSelected.getProductPropertyValueIds();
                        if (productPropertyValueIds!=null){
                            List<ProductPropertyValue> productPropertyValueList=new ArrayList<ProductPropertyValue>();
                            for (String productPropertyValueId:productPropertyValueIds){
                                ProductPropertyValue productPropertyValue=ServiceManager.productPropertyValueService.findById(productPropertyValueId);
                                productPropertyValueList.add(productPropertyValue);
                            }
                            productSelected.setProductPropertyValueList(productPropertyValueList);
                        }
                    }
                }
            }
        }
        return user;
    }

    public List<UserPoints> findUserPointsByUser(String userId) {
        DBObject dbObject=new BasicDBObject();
        DBRef userDBRef=new DBRef("mallUser",userId);
        dbObject.put("user",userDBRef);
        Query query=new BasicQuery(dbObject);
        query.with(new Sort(Sort.Direction.DESC,"date"));
        return mongoTemplate.find(query,UserPoints.class);
    }

    public void updateUserAfterOrder(Order order)     {
        Assert.notNull(order);
        User user=order.getUser();
        Assert.notNull(user);
        List<Notify> notifies=new ArrayList<Notify>();
        Notify notify=new Notify();
        notify.setRead(false);
        notify.setToUser(user);
        notify.setContent("您的订单号为 "+order.getId()+" 的订单付款成功！");
        notify.setDate(new Date());
        notify.setNotifyType(NotifyTypeCodeEnum.SYSTEM.toCode());
        notify.setTitle("系统消息");
        notifies.add(notify);
        if (!user.isDirectSaleMember()){
            List<Order> orders=ServiceManager.orderService.findOrdersByUserId(user.getId());
            double totalOrderPrice=0d;
            for(Order userOrder:orders){
                totalOrderPrice+=userOrder.getTotalPrice();
            }
            if (totalOrderPrice>=directSalePairTouchMode.getMembershipLine()){
                user.setDirectSaleMember(true);
                user.setBecomeMemberDate(new Date());
                Notify notify2=new Notify();
                notify2.setToUser(user);
                notify2.setContent("恭喜您成为正式会员，您将获得每日红包和系统佣金奖励！");
                notify2.setDate(new Date());
                notify2.setNotifyType(NotifyTypeCodeEnum.SYSTEM.toCode());
                notify2.setTitle("系统消息");
                notifies.add(notify2);
                upsert(user);
            }
        }
        ServiceManager.notifyService.insertAll(notifies);
    }

    public User findDirectUpperUser(User memberUser) {
        List<User> users=findLowerOrUpperUsers(memberUser,-1);
        Assert.isTrue(users==null || users.size()==1);
        User user=users==null?null:users.get(0);
        return user;
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

    public Message isValidUpper(String upperPhone) {
        Message message=new Message();
        User user=findByPhone(upperPhone);
        if (user==null){
            message.setSuccess(false);
            message.setMessage("not_exists");
            return message;
        }
        if (!user.isDirectSaleMember()){
            message.setSuccess(false);
            message.setMessage("not_member");
            return message;
        }
        List<User> users= findLowerOrUpperUsers(user, 1);
        if (users!=null&&users.size()>=2){
            message.setSuccess(false);
            message.setMessage("market_full");
            return message;
        }
        if (users!=null&&users.size()!=0){
            Pair<User> userPair=new Pair<User>();
            userPair.setFirst(users.get(0));
//            userPair.setSecond(users.get(1));
            user.setDirectLowerUsers(userPair);
        }
        message.setSuccess(true);
        message.setData(user);
        return message;
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

    public User findBrotherUser(User user) {
        Assert.notNull(user);
        List<User> users=findLowerOrUpperUsers(user,1);
        Assert.notNull(users);
        Assert.isTrue(users.size() > 0&&users.size()<=2);
        if (users.size()==1)
            return null;
        if (users.get(0).getId().equalsIgnoreCase(user.getId())) return users.get(1);
        return null;
    }

    public boolean isInLittleAreaWhenRegister(User newMemberUser, User upperUser) {
        Assert.notNull(newMemberUser);
        Assert.notNull(upperUser);
        Assert.notNull(newMemberUser.getBecomeMemberDate());
        Assert.notNull(newMemberUser.getMembershipPath());
        if (newMemberUser.getMembershipPath().indexOf(upperUser.getId())<0) return false;
        List<User> directLowersOfUpperUser=findLowerOrUpperUsers(upperUser,1);
        if (directLowersOfUpperUser==null) return false;
        if (directLowersOfUpperUser.size()==1) return false;
        User aUser=directLowersOfUpperUser.get(0);
        User bUser=directLowersOfUpperUser.get(1);
        if (aUser.getId().equals(newMemberUser.getId())||bUser.getId().equals(newMemberUser.getId())){
            User self=aUser.getId().equals(newMemberUser.getId())?aUser:bUser;
            User brother=aUser.getId().equals(newMemberUser.getId())?bUser:aUser;
            if (self.getBecomeMemberDate().before(brother.getBecomeMemberDate())) {
                return false;
            }else return true;
        }
        long aCount=findAllLowerMemberUsersCountBeforeADate(aUser,newMemberUser.getBecomeMemberDate());
        long bCount=findAllLowerMemberUsersCountBeforeADate(bUser,newMemberUser.getBecomeMemberDate());
        if (newMemberUser.getMembershipPath().indexOf(aUser.getId())>0){//在a区
            if (aCount>=bCount) {
                return false;
            }else return true;
        }else{//在b区
            if (bCount>=aCount) return false;
            else return true;
        }

    }
}
