package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.entity.Navbar;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.IArticleSectionService;
import com.lanzuan.website.service.IArticleService;
import com.lanzuan.website.service.INavbarService;
import com.lanzuan.website.service.IPageComponentService;
import com.lanzuan.website.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Controller
@RequestMapping("/admin/navbar")
public class AdminNavbarController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();

    @Resource(name = "navbarService")
    INavbarService navbarService;

    @RequestMapping(value = "/update")
    public ResponseEntity<Message> update(@RequestBody Navbar navbar){
        Message message=new Message();
        navbarService.update(navbar);
        message.setSuccess(true);
        message.setData(navbar);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/home/data")
    public ResponseEntity<Navbar> getNavbar(){
        Navbar navbar=navbarService.findByUri("/home");
        return new ResponseEntity<Navbar>(navbar, HttpStatus.OK);
    }

}