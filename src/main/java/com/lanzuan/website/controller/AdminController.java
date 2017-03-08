package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.IdentifyingCode;
import com.lanzuan.common.util.MD5;
import com.lanzuan.common.util.MongoDbUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.common.web.CookieTool;
import com.lanzuan.entity.*;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.IArticleSectionService;
import com.lanzuan.website.service.IArticleService;
import com.lanzuan.website.service.IPageComponentService;
import com.lanzuan.website.service.impl.UserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

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
    @Resource(name = "articleSectionService")
    IArticleSectionService articleSectionService;
    @Resource(name = "articleService")
    IArticleService articleService;

    @RequestMapping(value = "/index")
    public String index(HttpSession session,ModelMap modelMap){
        modelMap.addAttribute("pageTemplate", Constant.pageTemplateMap.get("/home"));
        return "forward:/WEB-INF/pages/admin/index.jsp";
    }
    @RequestMapping(value = "/sign_up")
    public String signUp(@ModelAttribute User user,final RedirectAttributes redirectAttributes,HttpSession session) throws UnsupportedEncodingException {
        Assert.notNull(user);
        Assert.isTrue(StringUtils.isNotBlank(user.getName()));
        Assert.notNull(StringUtils.isNotBlank(user.getPassword()));
        User find=userService.findByName(user.getName());
        if (find==null){
            session.setAttribute(Constant.LOGIN_ADMINISTRATOR,user);
            return "redirect:/admin/index";
        }
        if (MD5.convert(user.getPassword()).equalsIgnoreCase(find.getPassword())){
            session.setAttribute(Constant.LOGIN_ADMINISTRATOR,user);
            return "redirect:/admin/index";
        }

        return "admin";

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
    @RequestMapping(value = "/page_component/edit/{id}")
    public String editPageComponent(@PathVariable String id,ModelMap model) {
        PageComponent pageComponent=pageComponentService.findById(id);
        model.addAttribute("pageComponent",pageComponent);
        System.out.println("forward:/admin/page-component-edit");
        return "forward:/admin/page-component-edit";
    }

    @RequestMapping(value = "/file-editor/{id}")
    public String articleEditor(@PathVariable String id,ModelMap model, HttpSession session) {
        Article article=articleService.findById(id);
        model.addAttribute("article",article);
        List<ArticleSection> articleSections=articleSectionService.findArticleSectionByArticleId(id);
        model.addAttribute("articleSections",articleSections);
        return "admin/file-editor";
    }
    @RequestMapping(value = "/file-editor/in-section/{id}")
    public String addNewArticleInSection(@PathVariable String id,ModelMap model, HttpSession session) {
        ArticleSection articleSection=articleSectionService.findById(id);

        model.addAttribute("articleSection",articleSection);
        return "admin/file-editor";
    }
    @RequestMapping(value = "/file-editor")
    public String articleNew(ModelMap model, HttpSession session) {

        model.addAttribute("articleSections",Constant.articleSections);
        return "admin/file-editor";
    }
    @RequestMapping(value = "/article/upload")
    public String articleUpload(RedirectAttributes redirectAttributes,ModelMap model,@ModelAttribute Article article,String articleSectionId, HttpSession session) {
        Date now=new Date();
        article.setByEditor(true);
        if (article.getId()!=null&&!article.getId().trim().equals("")){
            article.setLastModifyDate(now);
//            article.setLastModifyUser(getLoginUser(session));
            articleService.update(article);
        }else {
            article.setId(null);
            article.setDate(now);
            article.setLastModifyDate(now);
//            article.setUploader(getLoginAdministrator(session));
            articleService.insert(article);
        }


        ArticleSection articleSection=null;
        if(articleSectionId!=null&&!articleSectionId.trim().equals("")){
            articleSection=articleSectionService.findById(articleSectionId);
            List<Article> articles=articleSection.getArticles();
            boolean exists=false;
            if (articles==null){
                articles=new ArrayList<Article>();
            }else{
                for (Article article1:articles){
                    if (article.getId().equals(article1.getId())){
                        exists=true;
                        break;
                    }
                }
            }
            if (!exists){
                articles.add(article);
                articleSection.setArticles(articles);
                articleSectionService.update(articleSection);
            }
        }

        redirectAttributes.addFlashAttribute("article",article);
        return "redirect:/admin/a";
    }

    @RequestMapping(value = "/ajaxTimeout")
    public ResponseEntity<Message> ajaxTimeout(@RequestBody ArticleSection articleSection){
        Message message=new Message();
        message.setMessage("登录超时了");
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/article_section/rename")
    public ResponseEntity<Message> renameArticleSection(@RequestBody ArticleSection articleSection){

        Message message=new Message();
        String name=articleSection.getName();
        articleSection=articleSectionService.findById(articleSection.getId());
        articleSection.setName(name);
        articleSectionService.update(articleSection);
        message.setSuccess(true);
        message.setData(articleSection);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }

    @RequestMapping(value = "/article/remove")
    public ResponseEntity<Message> removeArticle(@RequestBody Article article){
        Message message=new Message();
        articleService.removeById(article.getId());
        //TODO 还要删掉articleSection中的DBref引用,暂时不会有问题
        List<ArticleSection> articleSections=articleSectionService.findHomePageArticleSections();
        message.setSuccess(true);
        message.setData(articleSections);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
}