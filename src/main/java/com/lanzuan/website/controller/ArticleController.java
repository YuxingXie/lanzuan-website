package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.entity.Article;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.website.service.*;
import com.lanzuan.website.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseRestSpringController {
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
    @RequestMapping(value = "/{id}")
    public String article(@PathVariable String id,ModelMap modelMap){
        Article article=articleService.findById(id);
        modelMap.addAttribute("article",article);
        return "website/article/article";
    }
}