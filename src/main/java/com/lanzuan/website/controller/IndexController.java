package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.website.service.IPageComponentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    @RequestMapping(value = "/home")
    public String  index(ModelMap map,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
        if (Constant.pageComponentList==null||Constant.pageComponentList.size()==0){
            Constant.pageComponentList=pageComponentService.findHomePageComponents();
        }
        if (Constant.pageComponentList==null||Constant.pageComponentList.size()==0){
            PageComponent pageComponent1=new PageComponent();
            pageComponent1.setId("1");
            pageComponent1.setUrl("/statics/page/included/component/navbar/navbar-md-down-fix-bottom.html");

            PageComponent pageComponent2=new PageComponent();
            pageComponent2.setId("2");
            pageComponent2.setUrl("/statics/page/included/component/carousel/carousel-full-width-1.html");

            PageComponent pageComponent3=new PageComponent();
            pageComponent3.setId("3");
            pageComponent3.setUrl("/statics/page/included/component/card-group/img-card-group-1.html");

            PageComponent pageComponent4=new PageComponent();
            pageComponent4.setId("4");
            pageComponent4.setUrl("/statics/page/included/lanzuan/collapse-image-title-text-1.html");

            PageComponent pageComponent5=new PageComponent();
            pageComponent5.setId("5");
            pageComponent5.setUrl("/statics/page/included/lanzuan/article-section-1.html");

            PageComponent pageComponent6=new PageComponent();
            pageComponent6.setId("6");
            pageComponent6.setUrl("/statics/page/included/lanzuan/full-width-image-1.html");

            PageComponent pageComponent7=new PageComponent();
            pageComponent7.setId("7");
            pageComponent7.setUrl("/statics/page/included/lanzuan/sort-link-section-1.html");

            Constant.pageComponentList.add(pageComponent1);
            Constant.pageComponentList.add(pageComponent2);
            Constant.pageComponentList.add(pageComponent3);
            Constant.pageComponentList.add(pageComponent4);
            Constant.pageComponentList.add(pageComponent5);
            Constant.pageComponentList.add(pageComponent6);
            Constant.pageComponentList.add(pageComponent7);

        }
        map.addAttribute("pageComponentList",Constant.pageComponentList);
        return "index";
    }
}