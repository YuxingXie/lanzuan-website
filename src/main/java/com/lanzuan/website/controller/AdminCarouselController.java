package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.code.CarouselItemTypeEnum;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.Carousel;
import com.lanzuan.entity.CarouselItem;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.*;
import com.lanzuan.website.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
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
    @RequestMapping(value = "/image/input/{pageComponentId}/{carouselItemId}")
    public String articleSectionInputImage(@PathVariable String pageComponentId,@PathVariable String carouselItemId,ModelMap modelMap){

        modelMap.addAttribute("pageComponentId",pageComponentId);
        modelMap.addAttribute("carouselItemId",carouselItemId);
        return "admin/img-carousel-input";
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
        carousel.setEnabled(false);
        carouselService.update(carousel);
        carousel.setId(null);
        carousel.setEnabled(true);
        carouselService.insert(carousel);
        message.setSuccess(true);
        message.setData(carousel);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/image/new/{pageComponentId}/{carouselItemId}")
    public String articleSectionAddImage(@RequestParam("file") MultipartFile file,@PathVariable String pageComponentId,@PathVariable String carouselItemId,HttpServletRequest request){

        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String type= FileUtil.getFileTypeByOriginalFilename(file.getOriginalFilename());
//                org.springframework.core.io.Resource resource=new ServletContextResource(request.getServletContext(),"statics/upload/"+System.currentTimeMillis()+ type);
                String fileName=System.currentTimeMillis()+ type;
                String filePath = request.getServletContext().getRealPath("/") + "statics/upload/"+fileName;
                // 转存文件
                file.transferTo(new File(filePath));
                CarouselItem carouselItem=carouselItemService.findById(carouselItemId);
                carouselItem.setValue("/statics/upload/"+fileName);
                carouselItem.setType(CarouselItemTypeEnum.IMAGE.toCode());
                carouselItemService.update(carouselItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(pageComponentId)){
            return "redirect:/admin/page_component/edit/"+pageComponentId;
        }
        return "redirect:/admin/index";
    }

    @RequestMapping(value = "/list_page/{pageComponentId}")
    public String listPage(ModelMap modelMap,@PathVariable String pageComponentId){
//        List<Carousel> carousels=carouselService.findAll();
//        modelMap.addAttribute("carousels",carousels);
        modelMap.addAttribute("pageComponentId",pageComponentId);
        return "admin/carousel-list";
    }
    @RequestMapping(value = "/list/data")
    public ResponseEntity<List<Carousel>> getAllCarousels(){
        List<Carousel> carousels=carouselService.findAll();
        return new ResponseEntity<List<Carousel>>(carousels,HttpStatus.OK);
    }
    @RequestMapping(value = "/update")
    public ResponseEntity<List<Carousel>> update(@RequestBody Carousel carousel){
        carousel.setEnabled(!carousel.getEnabled());
        carouselService.update(carousel, false);
        return getAllCarousels();
    }
    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<List<Carousel>> remove(@PathVariable String id){
        carouselService.removeById(id);
        return getAllCarousels();
    }
}