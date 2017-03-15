package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.entity.CardGroup;
import com.lanzuan.entity.CardGroup;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.ICardGroupService;
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
@RequestMapping("/admin/card-group")
public class AdminCardGroupController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "cardGroupService")
    ICardGroupService cardGroupService;


    @RequestMapping(value = "/update")
    public ResponseEntity<Message> update(@RequestBody CardGroup cardGroup){
        Message message=new Message();
        cardGroupService.update(cardGroup);
        message.setSuccess(true);
        message.setData(cardGroup);

        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/save-as")
    public ResponseEntity<Message> saveAs(@RequestBody CardGroup cardGroup,HttpSession session){
        CardGroup old=cardGroupService.findById(cardGroup.getId());
        Message message=new Message();
        cardGroup.setId(null);
        cardGroup.setEnabled(false);
//        cardGroup.setCreator(getLoginAdministrator(session));
        cardGroup.setCreateDate(new Date());
        cardGroupService.insert(cardGroup);
        message.setSuccess(true);
        message.setData(old);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/list-page/{pageComponentId}")
    public String remove(@PathVariable String pageComponentId,ModelMap modelMap){
        modelMap.addAttribute("pageComponentId",pageComponentId);
        return "admin/card-group-list";
    }
    @RequestMapping(value = "/list/data")
    public ResponseEntity<List<CardGroup>> getCardGroupList(){
        List<CardGroup> navbarList=cardGroupService.findAll();
        return new ResponseEntity<List<CardGroup>>(navbarList, HttpStatus.OK);
    }
    @RequestMapping(value = "/status-change")
    public ResponseEntity<List<CardGroup>> statusChange(@RequestBody CardGroup cardGroup){
        cardGroup.setEnabled(!cardGroup.isEnabled());
        cardGroupService.update(cardGroup, false);
        return getCardGroupList();
    }
    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<List<CardGroup>> remove(@PathVariable String id){
        cardGroupService.removeById(id);
        return getCardGroupList();
    }
}