package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.entity.*;
import com.lanzuan.website.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping()
public class IndexController extends BaseRestSpringController {
    @Resource(name = "pageComponentService")
    IPageComponentService pageComponentService;
    @Resource(name = "pageTemplateService")
    IPageTemplateService pageTemplateService;
    @Resource(name = "articleService")
    IArticleService articleService;
    @Resource(name = "articleSectionService")
    IArticleSectionService articleSectionService;
    @Resource(name = "carouselService")
    ICarouselService carouselService;
    @Resource(name = "imageCardGroupService")
    IImageCardGroupService imageCardGroupService;
    @Resource(name = "imageTextBlockGroupService")
    IImageTextBlockGroupService imageTextBlockGroupService;
    @Resource(name = "navbarService")
    INavbarService navbarService;
    @RequestMapping(value = "/home")
    public String  index(ModelMap map,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
        String uri=request.getRequestURI();
        PageTemplate pageTemplate=pageTemplateService.findByUri(uri);
        map.addAttribute("pageTemplate",pageTemplate);
        return "index";
    }
    @RequestMapping(value = "/navbar/home/data")
    public ResponseEntity<Navbar> getNavbar(){
        Navbar navbar=navbarService.findByUri("/home");
        return new ResponseEntity<Navbar>(navbar, HttpStatus.OK);
    }
    @RequestMapping(value = "/card-group/home/data")
    public ResponseEntity<ImageCardGroup> homeImageCardGroup(){
        ImageCardGroup imageCardGroup=imageCardGroupService.findByUri("/home");
        return new ResponseEntity<ImageCardGroup>(imageCardGroup, HttpStatus.OK);
    }
    @RequestMapping(value = "/image-text-block-group/home/data")
    public ResponseEntity<ImageTextBlockGroup> homeImageTextBlockGroup(){
        ImageTextBlockGroup imageCardGroup=imageTextBlockGroupService.findByUri("/home");
        return new ResponseEntity<ImageTextBlockGroup>(imageCardGroup, HttpStatus.OK);
    }
    @RequestMapping(value = "/articleSection/data")
    public ResponseEntity<List<ArticleSection>> articleSectionData() throws ServletException, IOException {

//        List<ArticleSection> articleSections=articleSectionService.findFields(dbObject,fields,limit,"createDate",false);
        List<ArticleSection> articleSections=articleSectionService.findHomePageArticleSections();
        return new ResponseEntity<List<ArticleSection>>(articleSections, HttpStatus.OK);
    }
    @RequestMapping(value = "/article/{id}")
    public String article(@PathVariable String id,ModelMap modelMap){
        Article article=articleService.findById(id);
        List<ArticleSection> sections=articleSectionService.findArticleSectionByArticleId(id);
        article.setArticleSections(sections);
        modelMap.addAttribute("article",article);
        return "website/article/article";
    }
    @RequestMapping(value = "/carousel/home/data")
    public ResponseEntity<Carousel> carouselHomeData(){
        Carousel carousel=carouselService.findCarouselByUri("/home");
        return new ResponseEntity<Carousel>(carousel, HttpStatus.OK);
    }
}