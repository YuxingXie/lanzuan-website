package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
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

    @RequestMapping(value = "/home")
    public String  index(ModelMap map,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        String uri=request.getRequestURI();
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
    @RequestMapping(value = "/file-download")
    public void fileDownload(@RequestParam String path,HttpServletRequest request,HttpServletResponse response) throws IOException {
        ServletContextResource resource=new ServletContextResource(request.getServletContext(),path);
        FileUtil.fileDownload(response,resource.getFile().getAbsolutePath());
    }
    @RequestMapping(value = "/resource/list")
    public String articleList(HttpServletRequest request) throws IOException {
        ServletContextResource dirResource=new ServletContextResource(request.getServletContext(), Constant.DOCUMENT_DIR);
        ServletContextResource fileResource=new ServletContextResource(request.getServletContext(), Constant.DOCUMENT_FILE_DIR);
        ServletContextResource imageResource=new ServletContextResource(request.getServletContext(), Constant.DOCUMENT_IMAGE_DIR);
        ServletContextResource videoResource=new ServletContextResource(request.getServletContext(), Constant.DOCUMENT_VIDEO_DIR);
        File dirFile=dirResource.getFile();
        File fileDirFile=fileResource.getFile();
        File imageDirFile=imageResource.getFile();
        File videoDirFile=videoResource.getFile();
        if (!dirFile.exists())dirFile.mkdirs();
        if (!fileDirFile.exists())fileDirFile.mkdirs();
        if (!imageDirFile.exists())imageDirFile.mkdirs();
        if (!videoDirFile.exists())videoDirFile.mkdirs();
        List<WebResource> webResourceList=new ArrayList<WebResource>();
        if(dirFile.listFiles()!=null &&dirFile.listFiles().length>0)
            for(File file:dirFile.listFiles()){
                if (file.isDirectory()) continue;
                WebResource webResource=new WebResource();
                webResource.setType("未分类");
                webResource.setName(file.getName());
                webResource.setPath(Constant.DOCUMENT_DIR +"/"+file.getName());
                webResourceList.add(webResource);
            }
        if(fileDirFile.listFiles()!=null &&fileDirFile.listFiles().length>0)
            for(File file:fileDirFile.listFiles()){
                if (file.isDirectory()) continue;
                WebResource webResource=new WebResource();
                webResource.setType("文件");
                webResource.setName(file.getName());
                webResource.setPath(Constant.DOCUMENT_FILE_DIR +"/"+file.getName());
                webResourceList.add(webResource);
            }
        if(imageDirFile.listFiles()!=null &&imageDirFile.listFiles().length>0)
            for(File file:imageDirFile.listFiles()){
                if (file.isDirectory()) continue;
                WebResource webResource=new WebResource();
                webResource.setType("图片");
                webResource.setName(file.getName());
                webResource.setPath(Constant.DOCUMENT_IMAGE_DIR +"/"+file.getName());
                webResourceList.add(webResource);
            }
        if(videoDirFile.listFiles()!=null &&videoDirFile.listFiles().length>0)
            for(File file:videoDirFile.listFiles()){
                if (file.isDirectory()) continue;
                WebResource webResource=new WebResource();
                webResource.setType("视频");
                webResource.setName(file.getName());
                webResource.setPath(Constant.DOCUMENT_VIDEO_DIR +"/"+file.getName());
                webResourceList.add(webResource);
            }
        request.setAttribute("webResourceList",webResourceList);
        return "website/resource-list";
    }
}