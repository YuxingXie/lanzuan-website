package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.entity.ImageCardGroup;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.IImageCardGroupService;
import com.lanzuan.website.service.IUserService;
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
import java.awt.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Controller
@RequestMapping("/admin/card-group")
public class AdminCardGroupController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "imageCardGroupService")
    IImageCardGroupService imageCardGroupService;

    @RequestMapping(value = "/home/data")
    public ResponseEntity<ImageCardGroup> homeImageCardGroup(){
        ImageCardGroup imageCardGroup=imageCardGroupService.findByUri("/home");
        return new ResponseEntity<ImageCardGroup>(imageCardGroup, HttpStatus.OK);
    }
    @RequestMapping(value = "/update")
    public ResponseEntity<Message> update(@RequestBody ImageCardGroup cardGroup){
        Message message=new Message();
        imageCardGroupService.update(cardGroup);
        message.setSuccess(true);
        message.setData(cardGroup);

        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/save-as")
    public ResponseEntity<Message> saveAs(@RequestBody ImageCardGroup cardGroup,HttpSession session){
        Message message=new Message();
        cardGroup.setId(null);
        cardGroup.setEnabled(false);
        cardGroup.setCreator(getLoginAdministrator(session));
        cardGroup.setCreateDate(new Date());
        imageCardGroupService.insert(cardGroup);
        message.setSuccess(true);
        message.setData(cardGroup);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/list-page/{pageComponentId}")
    public String remove(@PathVariable String pageComponentId,ModelMap modelMap){
        modelMap.addAttribute("pageComponentId",pageComponentId);
        return "admin/card-group-list";
    }
    @RequestMapping(value = "/list/data")
    public ResponseEntity<List<ImageCardGroup>> getCardGroupList(){
        List<ImageCardGroup> navbarList=imageCardGroupService.findAll();
        return new ResponseEntity<List<ImageCardGroup>>(navbarList, HttpStatus.OK);
    }
    @RequestMapping(value = "/status-change")
    public ResponseEntity<List<ImageCardGroup>> statusChange(@RequestBody ImageCardGroup cardGroup){
        cardGroup.setEnabled(!cardGroup.isEnabled());
        imageCardGroupService.update(cardGroup, false);
        return getCardGroupList();
    }
    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<List<ImageCardGroup>> remove(@PathVariable String id){
        imageCardGroupService.removeById(id);
        return getCardGroupList();
    }
}