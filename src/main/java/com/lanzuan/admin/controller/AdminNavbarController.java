package com.lanzuan.admin.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.Navbar;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.INavbarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
    public ResponseEntity<Message> update(@RequestBody Navbar navbar,HttpSession session){
        Message message=new Message();
        navbar.setLastModifyUser(getLoginUser(session));
        navbar.setLastModifyDate(new Date());
        if(navbar.getId()==null)
            navbar.setCreator(getLoginUser(session));
        navbarService.update(navbar);
        message.setSuccess(true);
        message.setData(navbar);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/status-change")
    public ResponseEntity<List<Navbar>> statusChange(@RequestBody Navbar navbar){
        navbar.setEnabled(!navbar.isEnabled());
        navbarService.update(navbar, false);
        return getNavbars();
    }

    @RequestMapping(value = "/list/data")
    public ResponseEntity<List<Navbar>> getNavbars(){
        List<Navbar> navbarList=navbarService.findAll();
        return new ResponseEntity<List<Navbar>>(navbarList, HttpStatus.OK);
    }
    @RequestMapping(value = "/save-as")
    public ResponseEntity<Message> saveAs(@RequestBody Navbar navbar){

        Message message=new Message();
        if (StringUtils.isNotBlank(navbar.getId())){
            Navbar old=navbarService.findById(navbar.getId());
//            old.setEnabled(false);
            navbarService.update(old);
            navbar.setEnabled(false);
            navbar.setId(null);
            navbarService.insert(navbar);
            message.setData(old);

        }
        message.setSuccess(true);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<List<Navbar>> remove(@PathVariable String id){
        navbarService.removeById(id);
        return getNavbars();
    }
    @RequestMapping(value = "/list-page/{pageComponentId}")
    public String remove(@PathVariable String pageComponentId,ModelMap modelMap){
        modelMap.addAttribute("pageComponentId",pageComponentId);
        return "admin/navbar-list";
    }

}