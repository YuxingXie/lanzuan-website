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
@RequestMapping("/admin/article")
public class ArticleController extends BaseRestSpringController {
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

        return "admin/article-list";
    }
    @RequestMapping(value = "/cover-images/data")
    public ResponseEntity<List<String>> getIcons(HttpServletRequest request) throws IOException {
        String fileDirectory= Constant.ARTICLE_COVER_IMAGE_DIR;
        ServletContextResource resource=new ServletContextResource(request.getServletContext(), fileDirectory);
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
                    ServletContextResource fileResource=new ServletContextResource(request.getServletContext(),fileDirectory+"/"+file.getName());
                    strings.add(fileResource.getPath());
                }
            }
        }

        return new ResponseEntity<List<String>>(strings, HttpStatus.OK);
    }


    @RequestMapping(value = "/remove/{id}")
    public ResponseEntity<Message> removeArticle(@PathVariable String id){
        Message message=new Message();
        articleService.removeById(id);
        List<Article> articles=articleService.findAll();
        message.setSuccess(true);
        message.setData(articles);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/cover-image/new/{pageComponentId}")
    public String articleSectionAddImage(@RequestParam("file") MultipartFile file,@PathVariable String pageComponentId,HttpServletRequest request){

        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String type= FileUtil.getFileTypeByOriginalFilename(file.getOriginalFilename());
                String fileName=System.currentTimeMillis()+ type;
                String dir=request.getServletContext().getRealPath("/") + Constant.ARTICLE_COVER_IMAGE_DIR;
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
            return "redirect:/admin/page_component/edit/"+pageComponentId;
        }
        return "redirect:/admin/index";
    }


    @RequestMapping(value = "/save")
    public String uploadArticle(@ModelAttribute Article article,HttpSession session){
        if (article==null)
            return "redirect:/admin/article/list";
        if (StringUtils.isBlank(article.getId()))
            article.setId(null);
        Date now=new Date();
        article.setDate(now);
        article.setLastModifyUser(getLoginUser(session));
        article.setLastModifyDate(now);
        article.setByEditor(true);
        article.setUploader(getLoginAdministrator(session));
        articleService.update(article);

        return "redirect:/admin/article/list";
    }

    @RequestMapping(value = "/file-editor/{articleId}")
    public String addNewArticleInSection(@PathVariable String articleId,ModelMap model) {

        Article article=articleService.findById(articleId);


        model.addAttribute("article", article);
        return "admin/file-editor";
    }
}