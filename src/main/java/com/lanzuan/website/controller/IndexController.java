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
        map.addAttribute("pageTemplate",Constant.pageTemplateMap.get(uri));
        return "index";
    }

}