package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.Carousel;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.IArticleService;
import com.lanzuan.website.service.ICarouselService;
import com.lanzuan.website.service.IPageComponentService;
import com.lanzuan.website.service.ISortLinkGroupService;
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
    @Resource(name = "sortLinkGroupService")
    ISortLinkGroupService sortLinkGroupService;
    @Resource(name = "articleService")
    IArticleService articleService;
    @Resource(name = "carouselService")
    ICarouselService carouselService;
    @RequestMapping(value = "/image/input/{pageComponentId}")
    public String articleSectionInputImage(@PathVariable String pageComponentId,ModelMap modelMap){

        modelMap.addAttribute("pageComponentId",pageComponentId);

        return "admin/img-carousel-input";
    }


    @RequestMapping(value = "/insert-all")
    public ResponseEntity<Message> insertAll(@RequestBody Carousel carousel){
        Message message=new Message();
//        List<CarouselItem> carouselItems=carousel.getItems();
//        if(carouselItems!=null&&carouselItems.size()>0){
//            for (CarouselItem carouselItem:carouselItems){
//                if (carouselItem.getId()==null){
//                    carouselItemService.insert(carouselItem);
//                }else{
//                    carouselItemService.update(carouselItem);
//                }
//            }
//        }else {
//            carousel.setItems(null);
//        }
        if (carousel.getId()==null){
            carouselService.insert(carousel);
        }else{
            carouselService.update(carousel);
        }
        message.setSuccess(true);
        message.setData(carousel);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/save-as")
    public ResponseEntity<Message> saveAs(@RequestBody Carousel carousel){
        Carousel old=carouselService.findById(carousel.getId());
        Message message=new Message();
//        List<CarouselItem> carouselItems=carousel.getItems();
//        if(carouselItems!=null&&carouselItems.size()>0){
//            for (CarouselItem carouselItem:carouselItems){
//                carouselItem.setId(null);
//                carouselItemService.insert(carouselItem);
//            }
//        }else {
//            carousel.setItems(null);
//        }
        carousel.setEnabled(false);
        carousel.setId(null);
        carouselService.insert(carousel);
        message.setSuccess(true);
        message.setData(old);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/image/new/{pageComponentId}")
    public String articleSectionAddImage(@RequestParam("file") MultipartFile file,@PathVariable String pageComponentId,HttpServletRequest request){

        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String type= FileUtil.getFileTypeByOriginalFilename(file.getOriginalFilename());
                String fileName=System.currentTimeMillis()+ type;
                String dir=request.getServletContext().getRealPath("/") + Constant.CAROUSEL_IMAGE_DIR;
                String filePath = dir+"/"+fileName;
                File fileDir=new File(dir);
                if (!fileDir.exists()){
                    fileDir.mkdirs();
                }
                // 转存文件
                file.transferTo(new File(filePath));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(pageComponentId)){
            return "redirect:/admin/page-component/edit/"+pageComponentId;
        }
        return "redirect:/admin/index";
    }

    @RequestMapping(value = "/list-page/{pageComponentId}")
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
        carousel.setEnabled(!carousel.isEnabled());
        carouselService.update(carousel, false);
        return getAllCarousels();
    }
    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<List<Carousel>> remove(@PathVariable String id){
        carouselService.removeById(id);
        return getAllCarousels();
    }
}