package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.*;
import com.lanzuan.website.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping("/error")
public class ErrorController extends BaseRestSpringController {
    @Resource(name = "errorReportService")
    private IErrorReportService errorReportService;
    @RequestMapping(value = "/report")
    public String errorReport(@ModelAttribute ErrorReport errorReport,RedirectAttributes redirectAttributes){
        errorReportService.insert(errorReport);
        redirectAttributes.addFlashAttribute("errorReport", errorReport);
        return "redirect:/error/report/finish";
    }
    @RequestMapping(value = "/report/finish")
    public String reportFinish(ModelMap modelMap){

        modelMap.addAttribute("reportSuccess",true);
        return "error/500";
    }

}