package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.entity.ImageTextBlockGroup;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.IImageTextBlockGroupService;
import com.lanzuan.website.service.IImageTextBlockGroupService;
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
@RequestMapping("/admin/image-text-block-group")
public class AdminImageTextBlockGroupController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "imageTextBlockGroupService")
    IImageTextBlockGroupService imageTextBlockGroupService;


    @RequestMapping(value = "/update")
    public ResponseEntity<Message> update(@RequestBody ImageTextBlockGroup imageTextBlockGroup){
        Message message=new Message();
        imageTextBlockGroupService.update(imageTextBlockGroup);
        message.setSuccess(true);
        message.setData(imageTextBlockGroup);

        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/save-as")
    public ResponseEntity<Message> saveAs(@RequestBody ImageTextBlockGroup imageTextBlockGroup,HttpSession session){
        Message message=new Message();
        imageTextBlockGroup.setId(null);
        imageTextBlockGroup.setEnabled(false);
        imageTextBlockGroup.setCreator(getLoginAdministrator(session));
        imageTextBlockGroup.setCreateDate(new Date());
        imageTextBlockGroupService.insert(imageTextBlockGroup);
        message.setSuccess(true);
        message.setData(imageTextBlockGroup);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/list-page/{pageComponentId}")
    public String remove(@PathVariable String pageComponentId,ModelMap modelMap){
        modelMap.addAttribute("pageComponentId",pageComponentId);
        return "admin/card-group-list";
    }
    @RequestMapping(value = "/list/data")
    public ResponseEntity<List<ImageTextBlockGroup>> getCardGroupList(){
        List<ImageTextBlockGroup> navbarList=imageTextBlockGroupService.findAll();
        return new ResponseEntity<List<ImageTextBlockGroup>>(navbarList, HttpStatus.OK);
    }
    @RequestMapping(value = "/status-change")
    public ResponseEntity<List<ImageTextBlockGroup>> statusChange(@RequestBody ImageTextBlockGroup imageTextBlockGroup){
        imageTextBlockGroup.setEnabled(!imageTextBlockGroup.isEnabled());
        imageTextBlockGroupService.update(imageTextBlockGroup, false);
        return getCardGroupList();
    }
    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<List<ImageTextBlockGroup>> remove(@PathVariable String id){
        imageTextBlockGroupService.removeById(id);
        return getCardGroupList();
    }
}