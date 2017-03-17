package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.Article;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Controller
@RequestMapping("/admin/resource")
public class ResourceController extends BaseRestSpringController {
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

    @RequestMapping(value = "/list/data")
    public ResponseEntity<List<Article>> getAllArts(){
        List<Article> articles=articleService.findAll();
        return new ResponseEntity<List<Article>>(articles,HttpStatus.OK);
    }
    @RequestMapping(value = "/list")
    public String articleList(){

        return "admin/resource-list";
    }

}