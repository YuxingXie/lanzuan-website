package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.MD5;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.common.web.CookieTool;
import com.lanzuan.entity.*;
import com.lanzuan.entity.ArticleSection;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.IArticleSectionService;
import com.lanzuan.website.service.IArticleService;
import com.lanzuan.website.service.IPageComponentService;
import com.lanzuan.website.service.IWebPageService;
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
    @Resource(name = "articleSectionService")
    IArticleSectionService articleSectionService;
    @Resource(name = "articleService")
    IArticleService articleService;
    @Resource(name = "webPageService")
    IWebPageService webPageService;
    @RequestMapping(value = "/index")
    public String index(HttpSession session,ModelMap modelMap){
        WebPage webPage=webPageService.findByUri("/home");
        modelMap.addAttribute("webPage", webPage);

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
    @RequestMapping(value = "/icons/data")
    public ResponseEntity<List<String>> getIcons(HttpServletRequest request) throws IOException {
        ServletContextResource resource=new ServletContextResource(request.getServletContext(), Constant.icoUri);
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
                    ServletContextResource fileResource=new ServletContextResource(request.getServletContext(),Constant.icoUri+"/"+file.getName());
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
    @RequestMapping(value = "/page_component/edit/{pageComponentId}")
    public String editPageComponent(@PathVariable String pageComponentId,ModelMap model) {
        PageComponent pageComponent=pageComponentService.findById(pageComponentId);
        model.addAttribute("pageComponent", pageComponent);
        return "forward:/WEB-INF/pages/admin/page-component-edit.jsp";
    }

    /**
     * 编辑某articleSection中的某篇文章
     * @param pageComponentId
     * @param articleSectionId
     * @param articleId
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/file-editor/{pageComponentId}/{articleSectionId}/{articleId}")
    public String articleEditor(@PathVariable String pageComponentId,@PathVariable String articleSectionId, @PathVariable String articleId,ModelMap model, HttpSession session) {
        Article article=articleService.findById(articleId);
        PageComponent pageComponent=pageComponentService.findById(pageComponentId);
        model.addAttribute("article",article);
        model.addAttribute("pageComponent",pageComponent);
//        List<ArticleSection> articleSections=articleSectionService.findArticleSectionByArticleId(id);
//        model.addAttribute("articleSections",articleSections);
        ArticleSection articleSection=articleSectionService.findById(articleSectionId);
        model.addAttribute("articleSection",articleSection);
        return "admin/file-editor";
    }

    /**
     * 为文章块撰文(新增)
     * @param pageComponentId
     * @param articleSectionId
     * @param model
     * @return
     */

    @RequestMapping(value = "/file-editor/in-section/{pageComponentId}/{articleSectionId}")
    public String addNewArticleInSection(@PathVariable String pageComponentId,@PathVariable String articleSectionId,ModelMap model) {
        PageComponent pageComponent=pageComponentService.findById(pageComponentId);
        ArticleSection articleSection=articleSectionService.findById(articleSectionId);

        model.addAttribute("pageComponent",pageComponent);
        model.addAttribute("articleSection",articleSection);
        return "admin/file-editor";
    }
    @RequestMapping(value = "/file-editor")
    public String articleNew(ModelMap model, HttpSession session) {
        List<ArticleSection> articleSections=articleSectionService.findHomePageArticleSections();
        model.addAttribute("articleSections",articleSections);
        return "admin/file-editor";
    }
    @RequestMapping(value = "/article/upload")
    public String articleUpload(@ModelAttribute Article article,String articleSectionId,String pageComponentId, HttpSession session) {
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

        if (pageComponentId!=null){
            return "redirect:/admin/page_component/edit/"+pageComponentId;
        }
        return "redirect:/admin/index";
    }

    @RequestMapping(value = "/ajaxTimeout")
    public ResponseEntity<Message> ajaxTimeout(@RequestBody ArticleSection articleSection){
        Message message=new Message();
        message.setMessage("登录超时了");
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/article_section/image/input/{pageComponentId}/{articleSectionId}")
    public String articleSectionInputImage(@PathVariable String pageComponentId,@PathVariable String articleSectionId,ModelMap modelMap){

       modelMap.addAttribute("pageComponentId",pageComponentId);
       modelMap.addAttribute("articleSectionId",articleSectionId);
        return "admin/img-article-section";
    }

    @RequestMapping(value = "/icon/new/{pageComponentId}")
    public String uploadIcon(@RequestParam("file") MultipartFile file,@PathVariable String pageComponentId,@PathVariable String articleSectionId,HttpServletRequest request){
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String type=FileUtil.getFileTypeByOriginalFilename(file.getOriginalFilename());
//                org.springframework.core.io.Resource resource=new ServletContextResource(request.getServletContext(),"statics/upload/"+System.currentTimeMillis()+ type);
                String fileName=System.currentTimeMillis()+ type;
                String filePath = request.getServletContext().getRealPath("/") + "statics/upload/"+fileName;
                // 转存文件
                file.transferTo(new File(filePath));
                ArticleSection articleSection=articleSectionService.findById(articleSectionId);
                articleSection.setImage("/statics/upload/"+fileName);
                articleSectionService.update(articleSection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(pageComponentId)){
            return "redirect:/admin/page_component/edit/"+pageComponentId;
        }
        return "redirect:/admin/index";
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
                String filePath = request.getServletContext().getRealPath("/") +Constant.icoUri+"/"+fileName;
                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(pageComponentId)){
            return "redirect:/admin/page_component/edit/"+pageComponentId;
        }
        return "redirect:/admin/index";
    }

    @RequestMapping(value = "/article_section/rename")
    public ResponseEntity<Message> renameArticleSection(@RequestBody ArticleSection articleSection){

        Message message=new Message();
        String name=articleSection.getName();
        articleSection=articleSectionService.findById(articleSection.getId());
        articleSection.setName(name);
        articleSectionService.update(articleSection);
        message.setSuccess(true);
        message.setData(articleSectionService.findHomePageArticleSections());
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/article_section/new")
    public ResponseEntity<Message> saveNewArticleSections(@RequestBody List<ArticleSection> articleSections){

        Message message=new Message();
        List<ArticleSection> articleSectionsToSave=new ArrayList<ArticleSection>();
        Date now=new Date();
        for(ArticleSection articleSection:articleSections){
            if(articleSection.getId()!=null&&!articleSection.getId().trim().equals("")){
                continue;
            }
            articleSection.setEnabled(true);
            articleSection.setCreateDate(now);
            articleSectionsToSave.add(articleSection);
        }
        if (articleSectionsToSave.size()!=0){
            articleSectionService.insertAll(articleSectionsToSave);
        }
        message.setSuccess(true);
        articleSections=articleSectionService.findHomePageArticleSections();
        message.setData(articleSections);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/article_section/remove")
    public ResponseEntity<Message> removeArticleSection(@RequestBody ArticleSection articleSection){

        Message message=new Message();
        if(articleSection!=null&&StringUtils.isNotBlank(articleSection.getId())){
            articleSectionService.removeById(articleSection.getId());
        }
        List<ArticleSection> articleSections=articleSectionService.findHomePageArticleSections();
        message.setSuccess(true);
        message.setData(articleSections);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }

    @RequestMapping(value = "/article/remove")
    public ResponseEntity<Message> removeArticle(@RequestBody Article article){
        Message message=new Message();
        List<ArticleSection> articleSectionsIncludeArticle=articleSectionService.findArticleSectionByArticleId(article.getId());
        for(ArticleSection articleSection:articleSectionsIncludeArticle){
            if (articleSection.getArticles()==null) break;
            List<Article> articles=articleSection.getArticles();
            List<Article> articlesToSave=new ArrayList<Article>();
            for(Article articleInSection :articles){
                if (!articleInSection.getId().equalsIgnoreCase(article.getId())){
                    articlesToSave.add(articleInSection);
                }
            }
            if (articlesToSave.size()==0) articlesToSave=null;
            articleSection.setArticles(articlesToSave);
            articleSectionService.update(articleSection,false);
        }

        articleService.removeById(article.getId());

        List<ArticleSection> articleSections=articleSectionService.findHomePageArticleSections();
        message.setSuccess(true);
        message.setData(articleSections);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
}