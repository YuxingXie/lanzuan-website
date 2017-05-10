package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.RequestUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.common.web.AngularEntityEditorBuilder;
import com.lanzuan.entity.*;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.ServletContextResource;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/api/website")
public class ApiController extends BaseRestSpringController {

    @Resource(name = "webPageService")
    IWebPageService webPageService;
    @Resource(name = "articleService")
    IArticleService articleService;
    @Resource(name = "sortLinkGroupService")
    ISortLinkGroupService sortLinkGroupService;
    @Resource(name = "carouselService")
    ICarouselService carouselService;
    @Resource(name = "cardGroupService")
    ICardGroupService cardGroupService;
    @Resource(name = "imageTextBlockGroupService")
    IImageTextBlockGroupService imageTextBlockGroupService;
    @Resource(name = "navbarService")
    INavbarService navbarService;
    @Resource(name = "fullWidthImageService")
    IFullWidthImageService fullWidthImageService;
    @Resource(name = "pageComponentService")
    private IPageComponentService pageComponentService;
    @Resource
    private IArticlesAndImagesService articlesAndImagesService;
    @Resource
    private IBrandIconGroupService brandIconGroupService;

    @RequestMapping(value = "/brand-icon-group")
    public ResponseEntity<BrandIconGroup> brandIconGroup(){
        BrandIconGroup carousel=brandIconGroupService.findCarouselByUri("/home");
        return new ResponseEntity<BrandIconGroup>(carousel, HttpStatus.OK);
    }


}