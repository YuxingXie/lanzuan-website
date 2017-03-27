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
import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.RootItem;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.*;
import com.lanzuan.website.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseRestSpringController {
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

    @RequestMapping(value = "/index")
    public String index(HttpSession session,ModelMap modelMap){

        return "forward:/WEB-INF/pages/admin/index.jsp";
    }
    @RequestMapping(value = "/home-page-admin")
    public String homePageAdmin(HttpSession session,ModelMap modelMap){
        WebPage webPage=webPageService.findByUri("/home");
        modelMap.addAttribute("webPage", webPage);
        return "admin/home-page-admin";
    }
    @RequestMapping(value = "/component/{componentId}")
    public String pageComponent(@PathVariable String componentId,ModelMap modelMap){
        PageComponent pageComponent=pageComponentService.findById(componentId);
        modelMap.addAttribute("pageComponent",pageComponent);





        return "forward:"+pageComponent.getTemplateUri();
    }
    @RequestMapping(value = "/sign_up")
    public String signUp(@ModelAttribute User user,final RedirectAttributes redirectAttributes,HttpSession session) throws UnsupportedEncodingException {
        Assert.notNull(user);
        Assert.isTrue(StringUtils.isNotBlank(user.getLoginName()));
        Assert.notNull(StringUtils.isNotBlank(user.getPassword()));
        User find=userService.findByLoginName(user.getLoginName());
        if (find==null){
            return "redirect:/admin";
        }
        if (MD5.convert(user.getPassword()).equalsIgnoreCase(find.getPassword())){
            session.setAttribute(Constant.LOGIN_ADMINISTRATOR,find);
            return "redirect:/admin/index";
        }

        return "redirect:/admin";

    }
    @RequestMapping(value = "/icons/data")
    public ResponseEntity<List<String>> getIcons(HttpServletRequest request) throws IOException {
        ServletContextResource resource=new ServletContextResource(request.getServletContext(), Constant.ICO_DIR);
        List<String> strings=new ArrayList<String>();
        if (!resource.exists()){
            File file=resource.getFile();
            file.mkdirs();
            return new ResponseEntity<List<String>>(strings, HttpStatus.OK);
        }else{
            if (resource.getFile().isDirectory()){
                File[] files= resource.getFile().listFiles();
                for (File file:files){
                    if (file.isDirectory()) continue;
                    ServletContextResource fileResource=new ServletContextResource(request.getServletContext(),Constant.ICO_DIR +"/"+file.getName());
                    strings.add(fileResource.getPath());
                }
            }
        }

        return new ResponseEntity<List<String>>(strings, HttpStatus.OK);
    }
    @RequestMapping(value = "/carousel-images/data")
    public ResponseEntity<List<String>> carouselImages(HttpServletRequest request) throws IOException {
        String uri=Constant.CAROUSEL_IMAGE_DIR;
        ServletContextResource resource=new ServletContextResource(request.getServletContext(), uri);
        List<String> strings=new ArrayList<String>();
        if (!resource.exists()){
            File file=resource.getFile();
            file.mkdirs();
            return new ResponseEntity<List<String>>(strings, HttpStatus.OK);
        }else{
            if (resource.getFile().isDirectory()){
                File[] files= resource.getFile().listFiles();
                for (File file:files){
                    if (file.isDirectory()) continue;
                    ServletContextResource fileResource=new ServletContextResource(request.getServletContext(),uri+"/"+file.getName());
                    strings.add(fileResource.getPath());
                }
            }
        }

        return new ResponseEntity<List<String>>(strings, HttpStatus.OK);
    }
    @RequestMapping(value = "/to_login")
    public String toLogin(ModelMap model,String to, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (to==null||"".equals(to.trim())){
            to=request.getRequestURI();
        }
        logger.trace(URLDecoder.decode(to, "UTF-8"));

        model.addAttribute("to", to);
        return "forward:/login.jsp";
    }

    @RequestMapping(value = "/logout")
    public ResponseEntity<Message> logout(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user=getLoginAdministrator(session);
        logger.info("用户 \""+user.getName()+"\" 退出");
        session.setAttribute(Constant.LOGIN_ADMINISTRATOR, null);
        session.removeAttribute(Constant.LOGIN_ADMINISTRATOR);
        CookieTool.removeCookie(request, response, "loginStr");
//        System.out.println("清除cookie name");
        CookieTool.removeCookie(request, response, "password");
//        System.out.println("清除cookie password");
        Message message=new Message();
        message.setSuccess(true);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/page_component/new")
    public String newPageComponent() {
        return "admin/page-component/new";
    }

    @RequestMapping(value = "/page-component/edit/{pageComponentId}")
    public String editPageComponent(@PathVariable String pageComponentId,ModelMap model,HttpServletRequest request) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        PageComponent pageComponent=pageComponentService.findById(pageComponentId);
        model.addAttribute("pageComponent", pageComponent);

        AngularEntityEditorBuilder angularEntityEditorBuilder=new AngularEntityEditorBuilder(pageComponent);
        angularEntityEditorBuilder.buildHtml();
        String html= angularEntityEditorBuilder.getEditHtml();
        String js=angularEntityEditorBuilder.getAdminJavaScript();
        model.addAttribute("edit_html", html);
        model.addAttribute("js", js);

        return "admin/page-component-edit";
    }



    @RequestMapping(value = "/article_section/image/input/{pageComponentId}/{articleSectionId}")
    public String articleSectionInputImage(@PathVariable String pageComponentId,@PathVariable String articleSectionId,ModelMap modelMap){

       modelMap.addAttribute("pageComponentId",pageComponentId);
       modelMap.addAttribute("articleSectionId",articleSectionId);
        return "admin/img-article-section";
    }


    @RequestMapping(value = "/icon/upload-input/{pageComponentId}")
    public String iconUploadInput(ModelMap modelMap,@PathVariable String pageComponentId){
        modelMap.addAttribute("pageComponentId",pageComponentId);
        return "admin/img-nav-icon-input";
    }
    @RequestMapping(value = "/icons/add/{pageComponentId}")
    public String articleSectionAddImage(@RequestParam("file") MultipartFile file,@PathVariable String pageComponentId,HttpServletRequest request){

        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String type= FileUtil.getFileTypeByOriginalFilename(file.getOriginalFilename());
                String fileName=System.currentTimeMillis()+ type;
                String dir=request.getServletContext().getRealPath("/") +Constant.ICO_DIR;
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
        AngularEntityEditorBuilder builder=new AngularEntityEditorBuilder(pageComponentService.findById(pageComponentId));
        String html=builder.getListOperationHtml();
        String js=builder.getListOperationJavascript();
        modelMap.addAttribute("html",html);
        modelMap.addAttribute("js",js);
        return "admin/component-project-list";
    }


}