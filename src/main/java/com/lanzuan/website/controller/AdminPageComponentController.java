package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.MD5;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.common.web.AngularEntityEditorBuilder;
import com.lanzuan.common.web.CookieTool;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.User;
import com.lanzuan.entity.WebPage;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.IArticleService;
import com.lanzuan.website.service.IPageComponentService;
import com.lanzuan.website.service.ISortLinkGroupService;
import com.lanzuan.website.service.IWebPageService;
import com.lanzuan.website.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Controller
@RequestMapping("/admin/pageComponent")
public class AdminPageComponentController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "userService")
    UserService userService;
    @Resource(name = "pageComponentService")
    IPageComponentService pageComponentService;
    @Resource(name = "sortLinkGroupService")
    ISortLinkGroupService sortLinkGroupService;
    @Resource(name = "articleService")
    IArticleService articleService;
    @Resource(name = "webPageService")
    IWebPageService webPageService;

    @RequestMapping(value = "/list-data")
    public ResponseEntity<List<PageComponent>> carouselImages() throws IOException {
       List<PageComponent> pageComponentList=pageComponentService.findAll();

        return new ResponseEntity<List<PageComponent>>(pageComponentList, HttpStatus.OK);
    }
    @RequestMapping(value = "/build-page")
    public String buildPage(ModelMap modelMap){

        return "/admin/build-page";
    }
    @RequestMapping(value = "/build-page-js")
    public String buildPageJs(ModelMap modelMap){
        List<PageComponent> pageComponentList=pageComponentService.findAll();
        if(pageComponentList!=null)
        for (PageComponent pageComponent:pageComponentList){
            AngularEntityEditorBuilder angularEntityEditorBuilder=new AngularEntityEditorBuilder(pageComponent);
            pageComponent.setJsGetterMethods(angularEntityEditorBuilder.getGetterMethodsJavascript());
        }
        modelMap.addAttribute("pageComponentList",pageComponentList);

        return "/admin/build-page.js";
    }
}