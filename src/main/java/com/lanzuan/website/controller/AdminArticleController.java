package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.code.CarouselItemTypeEnum;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.Article;
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
@RequestMapping("/admin/article")
public class AdminArticleController extends BaseRestSpringController {
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
    @RequestMapping(value = "/list/data")
    public ResponseEntity<List<Article>> getAllArts(){
        List<Article> articles=articleService.findAll();
        return new ResponseEntity<List<Article>>(articles,HttpStatus.OK);
    }
    @RequestMapping(value = "/list")
    public String articleList(){

        return "admin/article-list";
    }

}