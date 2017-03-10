package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.MD5;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.common.web.CookieTool;
import com.lanzuan.entity.*;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.*;
import com.lanzuan.website.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Controller
@RequestMapping("/admin/carousel")
public class AdminCarouselController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "userService")
    UserService userService;
    @Resource(name = "pageComponentService")
    IPageComponentService pageComponentService;
    @Resource(name = "articleSectionService")
    IArticleSectionService articleSectionService;
    @Resource(name = "articleService")
    IArticleService articleService;
    @Resource(name = "carouselService")
    ICarouselService carouselService;
    @Resource(name = "carouselItemService")
    ICarouselItemService carouselItemService;
    @RequestMapping(value = "/image/input/{pageComponentId}/{carouselId}")
    public String articleSectionInputImage(@PathVariable String pageComponentId,@PathVariable String articleSectionId,ModelMap modelMap){

        modelMap.addAttribute("pageComponentId",pageComponentId);
        modelMap.addAttribute("articleSectionId",articleSectionId);
        return "admin/img-article-section";
    }
    @RequestMapping(value = "/item/remove/{itemId}")
    public ResponseEntity<Message> removeItem(@PathVariable String itemId,@RequestBody Carousel carousel){
        Message message=new Message();
        List<CarouselItem> carouselItemsToSave=new ArrayList<CarouselItem>();
        List<CarouselItem> carouselItems=carousel.getCarouselItems();
        for (CarouselItem carouselItem:carouselItems){
            if (!carouselItem.getId().equals(itemId)){
                carouselItemsToSave.add(carouselItem);
            }
        }

        if(carouselItemsToSave.size()>0){
            carousel.setCarouselItems(carouselItemsToSave);

        }else {
            carousel.setCarouselItems(null);
        }
        carouselService.update(carousel,false);
        message.setSuccess(true);
        message.setData(carousel);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }

    @RequestMapping(value = "/insert-all")
    public ResponseEntity<Message> insertAll(@RequestBody Carousel carousel){
        Message message=new Message();
        List<CarouselItem> carouselItems=carousel.getCarouselItems();
        if(carouselItems!=null&&carouselItems.size()>0){
            for (CarouselItem carouselItem:carouselItems){
                if (carouselItem.getId()==null){
                    carouselItemService.insert(carouselItem);
                }else{
                    carouselItemService.update(carouselItem);
                }
            }
        }else {
            carousel.setCarouselItems(null);
        }
        if (carousel.getId()==null){
            carouselService.insert(carousel);
        }else{
            carouselService.update(carousel,false);
        }
        message.setSuccess(true);
        message.setData(carousel);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/save-as")
    public ResponseEntity<Message> saveAs(@RequestBody Carousel carousel){
        Message message=new Message();
        List<CarouselItem> carouselItems=carousel.getCarouselItems();
        if(carouselItems!=null&&carouselItems.size()>0){
            for (CarouselItem carouselItem:carouselItems){
                if (carouselItem.getId()==null){
                    carouselItemService.insert(carouselItem);
                }else{
                    carouselItemService.update(carouselItem);
                }
            }
        }else {
            carousel.setCarouselItems(null);
        }
        carousel.setId(null);
        carousel.setEnabled(false);
        carouselService.insert(carousel);
        message.setSuccess(true);
        message.setData(carousel);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
}