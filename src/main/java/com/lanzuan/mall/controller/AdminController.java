package com.lanzuan.mall.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.code.NotifyTypeCodeEnum;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.helper.service.ServiceManager;
import com.lanzuan.common.util.MD5;
import com.lanzuan.entity.*;
import com.lanzuan.mall.service.IProductSeriesService;
import com.lanzuan.mall.service.IUserService;
import com.lanzuan.mall.service.impl.INotifyService;
import com.lanzuan.support.vo.Message;
import com.lanzuan.support.vo.NotifySearch;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    protected static final String DEFAULT_SORT_COLUMNS = null;
    protected static final String REDIRECT_ACTION = "";

    @Resource private IProductSeriesService productSeriesService;
    @Resource private IUserService userService;
    @Resource private INotifyService notifyService;

    @InitBinder("productSeries")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        session.setAttribute(Constant.LOGIN_ADMINISTRATOR,null);
        session.removeAttribute(Constant.LOGIN_ADMINISTRATOR);
        return "redirect:/admin";
    }
    @RequestMapping(value="/login")
    public ResponseEntity<Message> login(@RequestBody Administrator administrator,HttpSession session) {
        Message message=new Message();
        if (administrator.getName()==null||administrator.getName().trim().equals("")){
            message.setMessage("用户名不能为空");
            message.setSuccess(false);
            return new ResponseEntity<Message>(message,HttpStatus.OK);
        }
        if (administrator.getPassword()==null||administrator.getName().trim().equals("")){
            message.setMessage("密码不能为空");
            message.setSuccess(false);
            return new ResponseEntity<Message>(message,HttpStatus.OK);
        }
        Administrator dbAdmin= ServiceManager.administratorService.findByNameAndPassword(administrator.getName(), MD5.convert(administrator.getPassword()));
        if (dbAdmin==null){
            message.setMessage("用户名密码错误");
            message.setSuccess(false);
            return new ResponseEntity<Message>(message,HttpStatus.OK);
        }
        message.setSuccess(true);
        message.setMessage("登陆成功!!");
        session.setAttribute(Constant.LOGIN_ADMINISTRATOR, dbAdmin);
        message.setData(dbAdmin);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }

    @RequestMapping(value="/register_first_member")
    public ResponseEntity<Message> register_first_member(@RequestBody User user,HttpSession session) {
        Message message=new Message();
        Administrator administrator=getLoginAdministrator(session);
        if (administrator==null) {
            message.setSuccess(false);
            message.setMessage("登录超时，请重新登录!!");
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        user.setDirectSaleMember(true);
        user.setPassword(MD5.convert(user.getPassword()));
        Date now=new Date();
        user.setRegisterTime(now);
        user.setBecomeMemberDate(now);
        user.setActivated(true);
        userService.insertUser(user);

        message.setSuccess(true);
        message.setMessage("会员添加成功!!");

        message.setData(user);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value="/upgrade_user")
    public ResponseEntity<Message> upgrade_user(@RequestBody User user,HttpSession session) {
        Message message=new Message();
        Administrator administrator=getLoginAdministrator(session);
        if (administrator==null) {
            message.setSuccess(false);
            message.setMessage("登录超时，请重新登录!!");
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        message.setSuccess(true);
        user.setDirectSaleMember(true);
        Date now=new Date();
        if (user.getRegisterTime()==null){
            user.setRegisterTime(now);
        }
        user.setBecomeMemberDate(now);
        userService.update(user);
        message.setMessage("该会员已经提升为正式会员!!");
        message.setData(user);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value="/delete_user")
    public ResponseEntity<Message> delete_user(@RequestBody User user,HttpSession session) {
        Message message=new Message();
        Administrator administrator=getLoginAdministrator(session);
        if (administrator==null) {
            message.setSuccess(false);
            message.setMessage("登录超时，请重新登录!!");
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        userService.removeById(user.getId());
        message.setSuccess(true);
        message.setMessage("该会员已被删除!!");
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value="/get_admin")
    public ResponseEntity<Message> getAdministrator(HttpSession session) {
        Administrator administrator=getLoginAdministrator(session);
        Message message=new Message();
        if (administrator==null) {
            message.setSuccess(false);
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        message.setSuccess(true);
        message.setData(administrator);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }

    @RequestMapping(value="/unfinished_trans_list")
    public ResponseEntity<Message> unfinished_trans_list(HttpSession session) {
        Administrator administrator=getLoginAdministrator(session);
        Message message=new Message();
        if (administrator==null) {
            message.setSuccess(false);
            message.setMessage("登录超时，请重新登录!!");
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        List<AlipayTrans> alipayTransList=ServiceManager.alipayTransService.findSubmittedAndNotSendToAlipayTrans();
        message.setSuccess(true);
        message.setData(alipayTransList);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value="/finished_trans_list")
    public ResponseEntity<Message> finished_trans_list(HttpSession session) {
        Administrator administrator=getLoginAdministrator(session);
        Message message=new Message();
        if (administrator==null) {
            message.setSuccess(false);
            message.setMessage("登录超时，请重新登录!!");
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        List<AlipayTrans> alipayTransList=ServiceManager.alipayTransService.findAlipayTransFinished();
        message.setSuccess(true);
        message.setData(alipayTransList);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value="/member_list")
    public ResponseEntity<Message> member_list(HttpSession session) {
        Administrator administrator=getLoginAdministrator(session);
        Message message=new Message();
        if (administrator==null) {
            message.setSuccess(false);
            message.setMessage("登录超时，请重新登录!!");
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        List<User> users=ServiceManager.userService.findAllMembers();
        message.setSuccess(true);
        message.setData(users);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value="/first_member")
    public ResponseEntity<Message> first_member(HttpSession session) {
        Administrator administrator=getLoginAdministrator(session);
        Message message=new Message();
        if (administrator==null) {
            message.setSuccess(false);
            message.setMessage("登录超时，请重新登录!!");
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        User user=ServiceManager.userService.findFirstMember();
        message.setSuccess(true);
        message.setData(user);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }


    @RequestMapping(value=" /member_detail/{id}")
    public  ResponseEntity<Message> member_detail(@PathVariable String id){
        User user=ServiceManager.userService.findById(id);
        User upper=userService.getDirectUpperUser(user);
        List<User> lowerUsers=userService.findLowerOrUpperUsers(user,9);
        Message message=new Message();
        Map<String,Object> data=new HashMap<String, Object>();
        data.put("user",user);
        data.put("upper",upper);
        data.put("lowerUsers",lowerUsers);
        message.setData(data);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }




   @RequestMapping(value="/notify/remove")
    public ResponseEntity<Map<String,Object>> removeProduct( @RequestBody Notify notify,HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        ServiceManager.notifyService.removeById(notify.getId());
        DBObject dbObject=new BasicDBObject();
        Administrator administrator=getLoginAdministrator(session);
        dbObject.put("fromAdministrator",new DBRef("administrator",new ObjectId(administrator.getId())));
        List<Notify> notifies=ServiceManager.notifyService.findAll(dbObject);
        map.put("list",notifies);
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }
    @RequestMapping(value="/notify")
    public ResponseEntity<Message> notify( @RequestBody Notify notify,HttpSession session){
        Message message=new Message();
        Administrator administrator=getLoginAdministrator(session);
        if (administrator==null){
            message.setSuccess(false);
            message.setMessage("登录超时，请先登录!");
        }
        notify.setFromAdministrator(administrator);
        notify.setNotifyType(NotifyTypeCodeEnum.ADMIN.toCode());
        notify.setDate(new Date());
        notifyService.insert(notify);
        message.setSuccess(true);
        message.setMessage("消息发送成功!");
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value="/product_series/remove")
    public ResponseEntity<Map<String,Object>> removeProduct( @RequestBody ProductSeries productSeries,HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        Message message=new Message();
        //查询是否生成订单
        long useInOrder=ServiceManager.orderService.findOrdersCountByProductSeries(productSeries);
        if (useInOrder==0){
            //是否有加入购物车
            List<User> users=ServiceManager.userService.findUsersByProductSeriesInCart(productSeries);
            List<Interest> interests=ServiceManager.interestService.findByProductSeries(productSeries);
            Date now=new Date();
            if (interests!=null&&interests.size()>0){
                for(Interest interest:interests){
                    User user=interest.getUser();
                    Notify notify=new Notify();
                    notify.setToUser(user);
                    notify.setContent("我们很遗憾的通知您，您关注的商品\"" + productSeries.getName() + "\"被系统删除，因此我们将此商品从您的关注列表中移除了。感谢您对大坝的支持！");
                    notify.setDate(now);
                    notify.setTitle("系统通知");
                    notify.setFromAdministrator(getLoginAdministrator(session));
                    ServiceManager.notifyService.insert(notify);
                    ServiceManager.interestService.removeById(interest.getId());
                }
            }
            if(users!=null&&users.size()>0){
                for (User user:users){
                    Assert.notNull(user.getCart());
                    Assert.notNull(user.getCart().getProductSelectedList());
                    List<ProductSelected> productSelectedList=user.getCart().getProductSelectedList();
                    List<ProductSelected> newProductSelectedList=new ArrayList<ProductSelected>();
                    for (ProductSelected productSelected:productSelectedList){
                        if (!productSelected.getProductSeries().getId().equalsIgnoreCase(productSeries.getId())){
                            newProductSelectedList.add(productSelected);
                        }
                    }
                    User updateUser=new User();
                    updateUser.setId(user.getId());
                    Cart cart=new Cart();
                    cart.setProductSelectedList(newProductSelectedList);
                    updateUser.setCart(cart);
                    ServiceManager.userService.update(updateUser);

                    Notify notify=new Notify();
                    notify.setToUser(user);
                    notify.setContent("我们很遗憾的通知您，您购物车中的商品\"" + productSeries.getName() + "\"被系统删除，因此我们将此商品从您的购物车移除了。感谢您对大坝的支持！");
                    notify.setDate(now);
                    notify.setTitle("系统通知");
                    notify.setFromAdministrator(getLoginAdministrator(session));
                    ServiceManager.notifyService.insert(notify);
                }
            }

            //删除关联的DBRef,包括productSubCategory,productCategory,productProperty,productPropertyValue,productEvaluate,salesCampaign,homePageBlock
            ServiceManager.productPropertyService.removeByProductSeries(productSeries);
            ServiceManager.productEvaluateService.removeByProductSeries(productSeries);
            ServiceManager.salesCampaignService.removeProductSeries(productSeries);
            ServiceManager.homePageBlockService.removeProductSeries(productSeries);
            ServiceManager.productSeriesService.removeProductSeriesAndPictures(productSeries);
            message.setSuccess(true);
            message.setMessage("删除成功!");
            List<ProductSeries> list=productSeriesService.findAll();
            map.put("list",list);
        }else{
            message.setSuccess(false);
            message.setMessage("已有订单使用该产品，删除失败!");
        }
        map.put("message",message);
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }


    @RequestMapping(value="/notifies/data")
    public ResponseEntity<List<Notify>> notifies(ModelMap map, @RequestBody NotifySearch notifySearch,HttpSession session){
        DBObject dbObject=new BasicDBObject();
        String fromTo=notifySearch.getFromToMe();
        String read=notifySearch.getRead();
        Administrator administrator=getLoginAdministrator(session);
        if (fromTo!=null&&fromTo.equals("toMe")){
            dbObject.put("toAdministrator",new DBRef("administrator",new ObjectId(administrator.getId())));
        }else if (fromTo!=null&&fromTo.equals("fromMe")){
            dbObject.put("fromAdministrator",new DBRef("administrator",new ObjectId(administrator.getId())));
        }
        if (read!=null&&read.equals("read")){
            dbObject.put("read",true);
        }else if (read!=null&&read.equals("unread")){
            BasicDBList dbList=new BasicDBList();
            dbList.add(new BasicDBObject("read",false));
            dbList.add(new BasicDBObject("read",new BasicDBObject("$exists",false)));
            dbObject.put("$or",dbList);
        }
        List<Notify> notifies=ServiceManager.notifyService.findAll(dbObject);
        return new ResponseEntity<List<Notify>>(notifies,HttpStatus.OK);
    }

}
