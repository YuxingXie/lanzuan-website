package com.lanzuan.admin.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.Article;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.SortLinkGroup;
import com.lanzuan.entity.User;
import com.lanzuan.entity.support.field.Link;
import com.lanzuan.entity.support.field.SortLink;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.IArticleService;
import com.lanzuan.website.service.IPageComponentService;
import com.lanzuan.website.service.impl.SortLinkGroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
 * Created by Administrator on 2017/3/15.
 */
@Controller
@RequestMapping("/admin/sort-link-group")
public class AdminSortLinkGroupController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "sortLinkGroupService")
    private SortLinkGroupService sortLinkGroupService;
    @Resource(name = "pageComponentService")
    IPageComponentService pageComponentService;
    @Resource(name = "articleService")
    IArticleService articleService;

    @RequestMapping(value = "/file-editor")
    public String articleNew(ModelMap model, HttpSession session) {
        SortLinkGroup sortLinkGroup =sortLinkGroupService.findByUri("/home");
        model.addAttribute("sortLinkGroup", sortLinkGroup);
        return "admin/file-editor";
    }
    @RequestMapping(value = "/article/upload")
    public String articleUpload(@ModelAttribute Article article,String articleSectionId,String pageComponentId, HttpSession session) {
        Date now=new Date();
        article.setByEditor(true);
        if (article.getId()!=null&&!article.getId().trim().equals("")){
            article.setLastModifyDate(now);
//            article.setLastModifyUser(getLoginUser(session));
            articleService.upsert(article);
        }else {
            article.setId(null);
            article.setDate(now);
            article.setLastModifyDate(now);
//            article.setUploader(getLoginAdministrator(session));
            articleService.insert(article);
        }


        SortLink articleSectionGroupItem =null;


        if (pageComponentId!=null){
            return "redirect:/admin/page-component/edit/"+pageComponentId;
        }
        return "redirect:/admin/index";
    }

    @RequestMapping(value = "/icon/new/{pageComponentId}")
    public String uploadIcon(@RequestParam("file") MultipartFile file,@PathVariable String pageComponentId,@PathVariable String articleSectionId,HttpServletRequest request){
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String type= FileUtil.getFileTypeByOriginalFilename(file.getOriginalFilename());
                String fileName=System.currentTimeMillis()+ type;
                String filePath = request.getServletContext().getRealPath("/") + "statics/upload/"+fileName;
                // 转存文件
                file.transferTo(new File(filePath));

//                SortLink articleSectionGroupItem =articleSectionService.findById(articleSectionId);
//                articleSectionGroupItem.setImage("/statics/upload/"+fileName);
//                articleSectionService.update(articleSectionGroupItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(pageComponentId)){
            return "redirect:/admin/page-component/edit/"+pageComponentId;
        }
        return "redirect:/admin/index";
    }
    @RequestMapping(value = "/sort-link/rename")
    public ResponseEntity<Message> renameSortLink(@RequestBody SortLinkGroup sortLinkGroup){
//  TODO
        Message message=new Message();
//        String name= articleSectionGroupItem.getName();
//        articleSectionGroupItem =articleSectionService.findById(articleSectionGroupItem.getId());
//        articleSectionGroupItem.setName(name);
//        articleSectionService.update(articleSectionGroupItem);
//        message.setSuccess(true);
//        message.setData(articleSectionService.findHomePageArticleSections());
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }

    @RequestMapping(value = "/sort-link/remove")
    public ResponseEntity<Message> removeArticleSection(@RequestBody SortLinkGroup sortLinkGroup){

        Message message=new Message();
        //TODO
//        if(sortLinkGroup !=null&&StringUtils.isNotBlank(sortLinkGroup.getId())){
//            sortLinkGroupService.removeById(sortLinkGroup.getId());
//        }
//        SortLinkGroup articleSectionGroup =sortLinkGroupService.findByUri("/");
//        message.setSuccess(true);
//        message.setData(articleSectionGroup);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }

    @RequestMapping(value = "/article/remove")
    public ResponseEntity<Message> removeArticle(@RequestBody Article article){
        Message message=new Message();
//        List<ArticleSectionGroupItem> articleSectionsIncludeArticleGroupItem =articleSectionService.findArticleSectionByArticleId(article.getId());
//        for(ArticleSectionGroupItem articleSectionGroupItem : articleSectionsIncludeArticleGroupItem){
//            if (articleSectionGroupItem.getArticles()==null) break;
//            List<Article> articles= articleSectionGroupItem.getArticles();
//            List<Article> articlesToSave=new ArrayList<Article>();
//            for(Article articleInSection :articles){
//                if (!articleInSection.getId().equalsIgnoreCase(article.getId())){
//                    articlesToSave.add(articleInSection);
//                }
//            }
//            if (articlesToSave.size()==0) articlesToSave=null;
//            articleSectionGroupItem.setArticles(articlesToSave);
//            articleSectionService.update(articleSectionGroupItem,false);
//        }
//
//        articleService.removeById(article.getId());
//
//        List<ArticleSectionGroupItem> articleSectionGroup =articleSectionService.findHomePageArticleSections();
        message.setSuccess(true);
        message.setData(null);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }


    /**
     * 编辑某articleSection中的某篇文章
     * @param pageComponentId
     * @param articleId
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/file-editor/{pageComponentId}/{articleId}")
    public String articleEditor(@PathVariable String pageComponentId, @PathVariable String articleId,ModelMap model, HttpSession session) {
//        Article article=articleService.findById(articleId);
//        PageComponent pageComponent=pageComponentService.findById(pageComponentId);
//        model.addAttribute("article",article);
//        model.addAttribute("pageComponent",pageComponent);
////        List<ArticleSection> articleSections=articleSectionService.findArticleSectionByArticleId(id);
////        model.addAttribute("articleSections",articleSections);
//        ArticleSectionGroupItem articleSectionGroupItem =articleSectionService.findById(articleSectionId);
//        model.addAttribute("articleSection", articleSectionGroupItem);
        return "admin/file-editor";
    }


    //*****************************************************  commons  method *****************************************************************************************************///

    /**
     * 上传图片素材表单页
     * @param modelMap
     * @param pageComponentId
     * @return
     */
    @RequestMapping(value = "/image/upload-input/{pageComponentId}")
    public String iconUploadInput(ModelMap modelMap,@PathVariable String pageComponentId){
        modelMap.addAttribute("pageComponentId",pageComponentId);
        return "admin/sort-link-img-input";
    }
    @RequestMapping(value = "/image/input/{pageComponentId}")
    public String articleSectionInputImage(@PathVariable String pageComponentId,ModelMap modelMap){

        modelMap.addAttribute("pageComponentId",pageComponentId);

        return "admin/img-article-cover-input";
    }
    @RequestMapping(value = "/update")
    public ResponseEntity<Message> update(@RequestBody SortLinkGroup sortLinkGroup,HttpSession session){
        Message message=new Message();
        Date now=new Date();
        User user=getLoginUser(session);
        if (sortLinkGroup.getId()==null){
            sortLinkGroup.setCreator(user);
        }
        sortLinkGroup.setLastModifyDate(now);
        sortLinkGroup.setLastModifyUser(user);
        if(sortLinkGroup!=null&&sortLinkGroup.getItems()!=null){
            for(SortLink sortLink:sortLinkGroup.getItems()){
                if (sortLink.getLinks()!=null){
                    for(Link link:sortLink.getLinks()){
                        if(link.getDate()==null){
                            link.setDate(now);
                        }
                    }
                }
            }
        }
        sortLinkGroupService.upsert(sortLinkGroup);
        message.setSuccess(true);
        message.setData(sortLinkGroup);

        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/save-as")
    public ResponseEntity<Message> saveAs(@RequestBody SortLinkGroup sortLinkGroup,HttpSession session){
        SortLinkGroup old=sortLinkGroupService.findById(sortLinkGroup.getId());
        Message message=new Message();
        sortLinkGroup.setId(null);
        sortLinkGroup.setEnabled(false);

        sortLinkGroup.setCreateDate(new Date());
        sortLinkGroupService.insert(sortLinkGroup);
        message.setSuccess(true);
        message.setData(old);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/list-page/{pageComponentId}")
    public String listPage(@PathVariable String pageComponentId,ModelMap modelMap){
        PageComponent pageComponent=pageComponentService.findById(pageComponentId);
        modelMap.addAttribute("pageComponentId",pageComponentId);
        modelMap.addAttribute("pageComponent",pageComponent);
        return "admin/sort-link-group-list";
    }
//    @RequestMapping(value = "/list-page/bottom/{pageComponentId}")
//    public String remove(@PathVariable String pageComponentId,ModelMap modelMap){
//        modelMap.addAttribute("pageComponentId",pageComponentId);
//        return "admin/sort-link-group-list";
//    }
    @RequestMapping(value = "/list/data")
    public ResponseEntity<List<SortLinkGroup>> getSortLinkGroupList(){
        List<SortLinkGroup> sortLinkGroups=sortLinkGroupService.findByUriAndIndex("/home", 0);
        return new ResponseEntity<List<SortLinkGroup>>(sortLinkGroups, HttpStatus.OK);
    }
    @RequestMapping(value = "/list/bottom/data")
    public ResponseEntity<List<SortLinkGroup>> getSortLinkGroupBottomList(){
        List<SortLinkGroup> sortLinkGroups=sortLinkGroupService.findByUriAndIndex("/home",1);
        return new ResponseEntity<List<SortLinkGroup>>(sortLinkGroups, HttpStatus.OK);
    }
    @RequestMapping(value = "/status-change")
    public ResponseEntity<List<SortLinkGroup>> statusChange(@RequestBody SortLinkGroup sortLinkGroup){
        sortLinkGroup.setEnabled(!sortLinkGroup.isEnabled());
        sortLinkGroupService.update(sortLinkGroup, false);
        return getSortLinkGroupList();
    }
    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<List<SortLinkGroup>> remove(@PathVariable String id){
        sortLinkGroupService.removeById(id);
        return getSortLinkGroupList();
    }
    @RequestMapping(value = "/image/data")
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
}
