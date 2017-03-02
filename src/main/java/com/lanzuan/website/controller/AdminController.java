package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.IdentifyingCode;
import com.lanzuan.common.util.MD5;
import com.lanzuan.common.web.CookieTool;
import com.lanzuan.entity.User;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2015/6/11.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "userService")
    UserService userService;
    @RequestMapping(value = "/register_email")
    public String register_email(ModelMap model) {
        return "forward:/register_email/register_email.jsp";
    }
    @RequestMapping(value = "/register_phone")
    public String register_phone(ModelMap model) {
        return "forward:/register_phone/register_phone.jsp";
    }

    @RequestMapping(value = "/update/name")
    public ResponseEntity<User> updateName(ModelMap model,@RequestBody User user,HttpSession session) {
        Assert.notNull(user);
        String userName=user.getName();
        String userId=user.getId();
        Assert.notNull(userName);
        Assert.notNull(userId);
        ResponseEntity responseEntity = null;
        User updateUser=new User();
        updateUser.setId(userId);
        updateUser.setName(userName);
        userService.update(updateUser);
        user=userService.findById(userId);
        session.setAttribute(Constant.LOGIN_USER,user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @RequestMapping(value = "/update/password")
    public ResponseEntity<User> updatePassword(ModelMap model,@RequestBody User user,HttpSession session) {
        Assert.notNull(user);
        String password=user.getPassword();
        String userId=user.getId();
        Assert.notNull(password);
        Assert.notNull(userId);
        ResponseEntity responseEntity = null;
        User updateUser=new User();
        updateUser.setId(userId);
        updateUser.setPassword(MD5.convert(password));
        userService.update(updateUser);
        user=userService.findById(userId);
        session.setAttribute(Constant.LOGIN_USER,user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    /**
     *更新用户邮箱
     * @param model
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/update/email")
    public ResponseEntity<User> updateEmail(ModelMap model,@RequestBody User user,HttpSession session) {
        Assert.notNull(user);
        String userId=user.getId();
        Assert.notNull(userId);
        Assert.notNull(user.getEmail());
        User updateUser=new User();
        updateUser.setId(userId);
        updateUser.setEmail(user.getEmail());
        userService.update(updateUser);

        user=userService.findById(userId);
        session.setAttribute(Constant.LOGIN_USER,user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    /**
     *更新用户手机
     * @param model
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/update/phone")
    public ResponseEntity<User> updatePhone(ModelMap model,@RequestBody User user,HttpSession session) {
        Assert.notNull(user);
        String userId=user.getId();
        Assert.notNull(userId);
        Assert.notNull(user.getPhone());
        User updateUser=new User();
        updateUser.setId(userId);
        updateUser.setEmail(user.getPhone());
        userService.update(updateUser);

        user=userService.findById(userId);
        session.setAttribute(Constant.LOGIN_USER,user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }



    @RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> get(ModelMap model, @PathVariable String id,HttpServletRequest request) {
        return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/sign_up")
    public String signUp(@ModelAttribute User user,ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Assert.notNull(user);
        Assert.notNull(user.getName());
        Assert.notNull(user.getPassword());
        User find=userService.findByName(user.getName());
        if (find==null){
            session.setAttribute(Constant.LOGIN_ADMINISTRATOR,user);
            return "admin/index";
        }
        if (MD5.convert(user.getPassword()).equalsIgnoreCase(find.getPassword())){
            session.setAttribute(Constant.LOGIN_ADMINISTRATOR,user);
            return "admin/index";
        }
        return "forward:/admin";
    }
    @RequestMapping(value = "/to_login")
    public String toLogin(ModelMap model,String to, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (to==null||"".equals(to.trim())){
            to=request.getRequestURI();
        }
        logger.trace(URLDecoder.decode(to, "UTF-8"));

        model.addAttribute("to", to);
        return "forward:/login.jsp";
    }



    @RequestMapping(value = "/logout")
    public ResponseEntity logout(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user=getLoginUser(session);
        logger.info("用户 \""+user.getName()+"\" 退出");

        session.setAttribute("loginUser", null);
        session.removeAttribute("loginUser");
        session.setAttribute(Constant.CART, null);
        session.removeAttribute(Constant.CART);
        CookieTool.removeCookie(request, response, "loginStr");
//        System.out.println("清除cookie name");
        CookieTool.removeCookie(request, response, "password");
//        System.out.println("清除cookie password");
        return new ResponseEntity("{}", HttpStatus.OK);
    }


    @RequestMapping(value = "/personal_message")
    public String personalMessage(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        return "forward:/personal_message/personal_message.jsp";
    }


    @RequestMapping(value = "/email/validate")
    public ResponseEntity checkValidateCode(ModelMap model,@RequestBody User user) {
        ResponseEntity responseEntity=null;
        Assert.notNull(user);
        Assert.notNull(user.getEmail());
        User dbUser=userService.findByEmail(user.getEmail());
        boolean codeValid=dbUser.getValidateCode().equals(user.getValidateCode());
        if (codeValid){
            return new ResponseEntity("{\"codeValid\":true}",HttpStatus.OK);
        }
        return new ResponseEntity("{\"codeValid\":false}",HttpStatus.OK);

    }


    /**
     * 验证手机验证码是否有效
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/phone/validate")
    public ResponseEntity checkPhoneValidateCode(ModelMap model,@RequestBody User user) {
        Assert.notNull(user);
        Assert.notNull(user.getPhone());
        User dbUser=userService.findByPhone(user.getPhone());
        boolean codeValid=dbUser.getValidateCode().equals(user.getValidateCode());
        if (codeValid){
            return new ResponseEntity("{\"codeValid\":true}",HttpStatus.OK);
        }
        return new ResponseEntity("{\"codeValid\":false}",HttpStatus.OK);

    }



    @RequestMapping(value = "/identify_image")
    public void identify_image(HttpSession session,HttpServletResponse response) throws IOException {
        //设置不缓存图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0) ;
        //指定生成的相应图片
        response.setContentType("image/jpeg") ;
        IdentifyingCode idCode = new IdentifyingCode();
        BufferedImage image =new BufferedImage(idCode.getWidth() , idCode.getHeight() , BufferedImage.TYPE_INT_BGR) ;
        Graphics2D g = image.createGraphics() ;
        //定义字体样式
        Font myFont = new Font("黑体" , Font.BOLD , 16) ;
        //设置字体
        g.setFont(myFont) ;

        g.setColor(idCode.getRandomColor(200 , 250)) ;
        //绘制背景
        g.fillRect(0, 0, idCode.getWidth() , idCode.getHeight()) ;

        g.setColor(idCode.getRandomColor(180, 200)) ;
        idCode.drawRandomLines(g, 160) ;
        String randCode=idCode.drawRandomString(4, g) ;
        session.setAttribute("pictureRandCode",randCode);
//        System.out.println("图片验证码："+randCode);
        g.dispose() ;
        ImageIO.write(image, "JPEG", response.getOutputStream()) ;
    }
    @RequestMapping(value = "/identify_image/match")
    public ResponseEntity<Message> identifyImageMatch(@RequestBody Message message,HttpServletResponse response,HttpSession session) throws IOException {
        String pictureRandCode=session.getAttribute("pictureRandCode")==null?"":session.getAttribute("pictureRandCode").toString();
        Assert.notNull(message.getMessage());
//        System.out.println(message.getMessage());
        if (message.getMessage().equalsIgnoreCase(pictureRandCode)){
            message.setSuccess(true);
        }
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
}