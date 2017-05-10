package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    @Resource
    private IArticlesAndImagesService articlesAndImagesService;
    @Resource
    private IBrandIconGroupService brandIconGroupService;

    @RequestMapping(value = "/home")
    public String  index(ModelMap map,HttpServletRequest request) throws ServletException, IOException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        if (RequestUtil.isRobotRequest(request)){
            return "bot";
        }
        String agent=request.getHeader("User-Agent").toLowerCase();
        String browser= RequestUtil.getBrowserName(agent);
        if (browser.equals("ie7")||browser.equals("ie8")||browser.equals("ie9") ){
            map.addAttribute("browser",browser);
            return "browser";
        }
        WebPage webPage=webPageService.findByUri("/home");
        map.addAttribute("webPage",webPage);
        AngularEntityEditorBuilder builder=new AngularEntityEditorBuilder(webPage.getPageComponents());
        String js=builder.getWebsiteJavaScript();
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
    @RequestMapping(value = "/articles-images/home/data")
    public ResponseEntity<ArticlesAndImages> getArticlesAndImages(){
        ArticlesAndImages articlesAndImages=articlesAndImagesService.findByUri("/home");
        return new ResponseEntity<ArticlesAndImages>(articlesAndImages, HttpStatus.OK);
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
        PageComponent<Navbar> cond=new PageComponent<Navbar>();
        cond.setVar("navbar");
        PageComponent<Navbar> pageComponent=pageComponentService.findOne(cond);
        modelMap.addAttribute("article",article);
        modelMap.addAttribute("pageComponent",pageComponent);
        return "website/article/article";
    }
    @RequestMapping(value = "/article/list")
    public String articleList(){

        return "website/article/article-list";
    }
    @RequestMapping(value = "/article/list/data")
    public ResponseEntity<List<Article>> getAllArts(){
        List<Article> articles=articleService.findAllOrderBy("date",false);
        return new ResponseEntity<List<Article>>(articles,HttpStatus.OK);
    }
    @RequestMapping(value = "/article/praise/{id}")
    public ResponseEntity<Message> praise(@PathVariable String id,ModelMap modelMap){
        Message message=new Message();
        articleService.praise(id);
        message.setSuccess(true);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/file-download")
    public void fileDownload(@RequestParam String path,HttpServletRequest request,HttpServletResponse response) throws IOException {
        byte bb[];
        bb = path.getBytes("ISO-8859-1");
        path= new String(bb, "UTF-8");
        ServletContextResource resource=new ServletContextResource(request.getServletContext(),path);
        FileUtil.fileDownload(response,resource.getFile().getAbsolutePath());
    }
    @RequestMapping(value = "/resource/list")
    public String articleList(HttpServletRequest request) throws IOException {
        List<WebResource> webResourceList = getWebResources(request);
        request.setAttribute("webResourceList",webResourceList);
        return "website/resource-list";
    }


}