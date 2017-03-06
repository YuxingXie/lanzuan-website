package com.lanzuan.website.service.impl;

import com.lanzuan.common.constant.Constant;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.PageTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StartOnLoadService {
    @Resource(name = "pageTemplateService")
    private PageTemplateService pageTemplateService;
    @Resource(name = "pageComponentService")
    private PageComponentService pageComponentService;
    /**
     * Spring 容器初始化时加载
     */
    public void loadData() {
        System.out.println("容器初始化完成，载入初始化数据。。。。。。");
        PageTemplate pageTemplate = pageTemplateService.findByUri("/home");
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
            pageComponent6.setName("全屏宽度图片模板1");
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
            pageTemplate.setUri("/home");
            pageTemplate.setActive(true);
            pageComponentService.insertAll(pageComponentList);

            pageTemplateService.insert(pageTemplate);
        }
        if (Constant.pageTemplateMap==null)
            Constant.pageTemplateMap=new HashMap<String, PageTemplate>();
        Constant.pageTemplateMap.put("/home",pageTemplate);
    }


}