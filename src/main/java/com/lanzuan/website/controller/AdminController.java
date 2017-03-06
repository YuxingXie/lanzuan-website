package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.IdentifyingCode;
import com.lanzuan.common.util.MD5;
import com.lanzuan.common.web.CookieTool;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.User;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.IPageComponentService;
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
    @Resource(name = "pageComponentService")
    IPageComponentService pageComponentService;
    @RequestMapping(value = "/sign_up")
    public String signUp(@ModelAttribute User user,ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Assert.notNull(user);
        Assert.notNull(user.getName());
        Assert.notNull(user.getPassword());
        User find=userService.findByName(user.getName());
        model.addAttribute("pageTemplate",Constant.pageTemplateMap.get("/home"));
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
    public ResponseEntity<Message> logout(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user=getLoginAdministrator(session);
        logger.info("用户 \""+user.getName()+"\" 退出");
        session.setAttribute(Constant.LOGIN_ADMINISTRATOR, null);
        session.removeAttribute(Constant.LOGIN_ADMINISTRATOR);
        CookieTool.removeCookie(request, response, "loginStr");
//        System.out.println("清除cookie name");
        CookieTool.removeCookie(request, response, "password");
//        System.out.println("清除cookie password");
        Message message=new Message();
        message.setSuccess(true);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/page_component/new")
    public String newPageComponent(ModelMap model, HttpSession session) {
        return "admin/page-component/new";
    }
    @RequestMapping(value = "/page_component/edit/{id}")
    public String editPageComponent(@PathVariable String id,ModelMap model, HttpSession session) {
        PageComponent pageComponent=pageComponentService.findById(id);
        pageComponent.setDataClass("com.lanzuan.entity.Navbar");
        model.addAttribute("pageComponent",pageComponent);
        return "admin/page-component/edit";
    }
    @RequestMapping(value = "/page_component/css/{id}")
    public String editPageComponentCss(@PathVariable String id,ModelMap model, HttpSession session) {
        return "admin/page-component/css";
    }
}