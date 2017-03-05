package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.PageTemplate;
import com.lanzuan.website.service.IPageComponentService;
import com.lanzuan.website.service.IPageTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    @RequestMapping(value = "/home")
    public String  index(ModelMap map,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
        String uri=request.getRequestURI();

        if (Constant.pageTemplateMap==null){
           Constant.pageTemplateMap=new HashMap<String, PageTemplate>();
        }
        if (Constant.pageTemplateMap.get(uri)==null){
            PageTemplate pageTemplate=pageTemplateService.findByUri(uri);
            if (pageTemplate==null){
                System.out.println("没有找到页面模板，应用默认模板......");
                pageTemplate=new PageTemplate();
                PageComponent pageComponent1=new PageComponent();
                pageComponent1.setUri("/statics/page/included/component/navbar/navbar-md-down-fix-bottom.html");
                pageComponent1.setName("响应式导航条模板1");
                pageComponent1.setRemark("在中等及更小屏幕上会固定底部显示。");

                PageComponent pageComponent2=new PageComponent();
                pageComponent2.setUri("/statics/page/included/component/carousel/carousel-full-width-1.html");
                pageComponent2.setName("响应式轮播图");
                pageComponent2.setRemark("任何设备及屏幕都为全屏宽度。");

                PageComponent pageComponent3=new PageComponent();
                pageComponent3.setUri("/statics/page/included/component/card-group/img-card-group-1.html");
                pageComponent3.setName("图文卡片组模板1");
                pageComponent3.setRemark("一组带文字的图标组，在任何尺寸屏幕下皆保持一行。");

                PageComponent pageComponent4=new PageComponent();
                pageComponent4.setUri("/statics/page/included/lanzuan/collapse-image-title-text-1.html");
                pageComponent4.setName("蓝钻鼠标掠过类似手风琴模板1");
                pageComponent4.setRemark("非标准bootstrap组件，需要依赖angularjs，效果为鼠标掠过按钮，在下方显示相应系列图片。在中等及以上屏幕每行显示4张图片，图片无边框效果；中等以下显示2张图片，图片带圆角相框效果。");

                PageComponent pageComponent5=new PageComponent();
                pageComponent5.setUri("/statics/page/included/lanzuan/article-section-1.html");
                pageComponent5.setName("文章块组件1");
                pageComponent5.setRemark("文章块组件，中等及以下屏幕每行显示一列文字；中等以上每行显示3列新闻。");

                PageComponent pageComponent6=new PageComponent();
                pageComponent6.setUri("/statics/page/included/lanzuan/full-width-image-1.html");
                pageComponent6.setUri("全屏宽度图片模板1");
                pageComponent6.setRemark("简单的全屏宽度图片。");

                PageComponent pageComponent7=new PageComponent();
                pageComponent7.setUri("/statics/page/included/lanzuan/sort-link-section-1.html");
                pageComponent7.setName("分类链接模板1");
                pageComponent7.setRemark("分类链接模板，将许多链接分为多列排列，每列有个分类名称。在中等及以下屏幕每行显示2列；中等以上每行显示5列。");
                List<PageComponent> pageComponentList=new ArrayList<PageComponent>();
                pageComponentList.add(pageComponent1);
                pageComponentList.add(pageComponent2);
                pageComponentList.add(pageComponent3);
                pageComponentList.add(pageComponent4);
                pageComponentList.add(pageComponent5);
                pageComponentList.add(pageComponent6);
                pageComponentList.add(pageComponent7);
                pageTemplate.setPageComponents(pageComponentList);
                pageTemplate.setUri(uri);
                pageTemplate.setActive(true);
                pageComponentService.insertAll(pageComponentList);

                pageTemplateService.insert(pageTemplate);
            }
            Constant.pageTemplateMap.put(uri, pageTemplate);
        }
        map.addAttribute("pageTemplate",Constant.pageTemplateMap.get(uri));
        return "index";
    }
}