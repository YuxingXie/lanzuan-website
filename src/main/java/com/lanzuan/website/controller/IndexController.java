package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.common.web.AngularEntityEditorBuilder;
import com.lanzuan.entity.*;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.ServletContextResource;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping()
public class IndexController extends BaseRestSpringController {

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
    @Resource(name = "configuration")
    private Configuration configuration;
    @RequestMapping(value = "/home")
    public String  index(ModelMap map,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        String uri=request.getRequestURI();
        WebPage webPage=webPageService.findByUri("/home");

//        map.addAttribute("webPage",webPage);
//        PageComponent pageComponent=pageComponentService.findById("58ccc1e489b6fb121cd55c4c");
        AngularEntityEditorBuilder builder=new AngularEntityEditorBuilder(webPage.getPageComponents());
        Map root=new HashMap();
        for (PageComponent pageComponent:webPage.getPageComponents()){
            root.put(pageComponent.getVar(),pageComponent);
            Template template=configuration.getTemplate("img-card-group-1.ftl");
        }

        StringBuffer templates=new StringBuffer();
//        for (PageComponent pageComponent:webPage.getPageComponents()){
//            String templateUri=pageComponent.getTemplateUri();
//            org.springframework.core.io.Resource resource=new ServletContextResource(request.getServletContext(),templateUri);
//            File file=resource.getFile();
//            BufferedReader br=new BufferedReader(new FileReader(file));
//            String temp=null;
//
//            temp=br.readLine();
//            while(temp!=null){
//
//                templates.append(temp);
//                temp=br.readLine();
//            }
//            templates.toString().replaceAll("pageComponent.varU",pageComponent.getVarU()).replaceAll("pageComponent.var",pageComponent.getVar());
//        }

        String js=builder.getWebsiteJavaScript();
//        map.addAttribute("templates",templates);
        map.addAttribute("js",js);
        return "index";
    }
    @RequestMapping(value = "/component/{componentId}")
    public String pageComponent(@PathVariable String componentId,ModelMap modelMap){
        PageComponent pageComponent=pageComponentService.findById(componentId);
        modelMap.addAttribute("pageComponent",pageComponent);
        return "forward:"+pageComponent.getWebsiteUri();
    }
    @RequestMapping(value = "/navbar/home/data")
    public ResponseEntity<Navbar> getNavbar(){
        Navbar navbar=navbarService.findByUri("/home");
        return new ResponseEntity<Navbar>(navbar, HttpStatus.OK);
    }
    @RequestMapping(value = "/full-width-image/home/data")
    public ResponseEntity<FullWidthImage> getFullWidthImage(){
        FullWidthImage fullWidthImage=fullWidthImageService.findByUri("/home");
        return new ResponseEntity<FullWidthImage>(fullWidthImage, HttpStatus.OK);
    }
    @RequestMapping(value = "/card-group/home/data")
    public ResponseEntity<CardGroup> homeImageCardGroup(){
        CardGroup imageCardGroup=cardGroupService.findByUri("/home");
        return new ResponseEntity<CardGroup>(imageCardGroup, HttpStatus.OK);
    }
    @RequestMapping(value = "/image-text-block-group/home/data")
    public ResponseEntity<ImageTextBlockGroup> homeImageTextBlockGroup(){
        ImageTextBlockGroup imageCardGroup=imageTextBlockGroupService.findByUri("/home");
        return new ResponseEntity<ImageTextBlockGroup>(imageCardGroup, HttpStatus.OK);
    }

    @RequestMapping(value = "/sort-link-group/data")
    public ResponseEntity<SortLinkGroup> sortLinkGroupData() throws ServletException, IOException {

        SortLinkGroup sortLinkGroup=sortLinkGroupService.findByUri("/home",0);
        return new ResponseEntity<SortLinkGroup>(sortLinkGroup, HttpStatus.OK);
    }
    @RequestMapping(value = "/sort-link-group/bottom/data")
    public ResponseEntity<SortLinkGroup> sortLinkGroupBottomData() throws ServletException, IOException {

        SortLinkGroup sortLinkGroup=sortLinkGroupService.findByUri("/home",1);
        return new ResponseEntity<SortLinkGroup>(sortLinkGroup, HttpStatus.OK);
    }
    @RequestMapping(value = "/app-js")
    public String app_js(ModelMap modelMap,String pageId,String componentId){
        if (StringUtils.isNotBlank(pageId)){
            WebPage page=webPageService.findById(pageId);

            modelMap.addAttribute("page",page);
        }
        if (StringUtils.isNotBlank(componentId)){
            PageComponent component=pageComponentService.findById(componentId);


            modelMap.addAttribute("component",component);
        }
        return "app.js";
    }
    @RequestMapping(value = "/carousel/home/data")
    public ResponseEntity<Carousel> carouselHomeData(){
        Carousel carousel=carouselService.findCarouselByUri("/home");
        return new ResponseEntity<Carousel>(carousel, HttpStatus.OK);
    }
    @RequestMapping(value = "/article/{id}")
    public String article(@PathVariable String id,ModelMap modelMap){
        Article article=articleService.increaseReadTimes(id);
        modelMap.addAttribute("article",article);
        return "website/article/article";
    }
    @RequestMapping(value = "/article/praise/{id}")
    public ResponseEntity<Message> praise(@PathVariable String id,ModelMap modelMap){
        Message message=new Message();
        articleService.praise(id);
        message.setSuccess(true);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
}