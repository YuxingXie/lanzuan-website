package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.entity.Article;
import com.lanzuan.entity.ArticleSection;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.PageTemplate;
import com.lanzuan.website.service.IArticleSectionService;
import com.lanzuan.website.service.IArticleService;
import com.lanzuan.website.service.IPageComponentService;
import com.lanzuan.website.service.IPageTemplateService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import org.bson.types.ObjectId;
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
import java.util.ArrayList;
import java.util.HashMap;
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
    @RequestMapping(value = "/home")
    public String  index(ModelMap map,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
        String uri=request.getRequestURI();
        map.addAttribute("pageTemplate",Constant.pageTemplateMap.get(uri));
        return "index";
    }
    @RequestMapping(value = "/articleSection/data")
    public ResponseEntity<List<ArticleSection>> articleSectionData() throws ServletException, IOException {
        DBObject dbObject=new BasicDBObject();
        dbObject.put("enabled",true);
        List<String> fields=new ArrayList<String>();
        fields.add("id");
        fields.add("name");
        fields.add("image");
        fields.add("articles");
        int limit=Constant.articleSectionNum;
        List<ArticleSection> articleSections=articleSectionService.findFields(dbObject,fields,limit,"createDate",false);
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
}