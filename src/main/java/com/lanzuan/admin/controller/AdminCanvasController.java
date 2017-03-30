package com.lanzuan.admin.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.Canvas;
import com.lanzuan.entity.CardGroup;
import com.lanzuan.entity.User;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.ICanvasService;
import com.lanzuan.website.service.ICardGroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
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
@RequestMapping("/admin/canvas")
public class AdminCanvasController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "canvasService")
    ICanvasService canvasService;

    @RequestMapping(value = "/list")
    public String canvasList(HttpServletRequest request,ModelMap modelMap) throws IOException {
        modelMap.addAttribute("canvasList", canvasService.findAll());
        return "admin/canvas-list";
    }
    @RequestMapping(value = "/input")
    public String input(String id,ModelMap modelMap){
        if (StringUtils.isNotBlank(id)){
            Canvas canvas=canvasService.findById(id);
            modelMap.addAttribute("canvas", canvas);
        }
        return "admin/canvas-input";
    }
    @RequestMapping(value = "/update")
    public String update(Canvas canvas,HttpSession session){
        Message message=new Message();
        Date now=new Date();
        User user=getLoginAdministrator(session);
        if (StringUtils.isBlank(canvas.getId())){
            canvas.setCreator(user);
        }
        canvas.setLastModifyDate(now);
        canvas.setLastModifyUser(user);
        canvasService.update(canvas);
        message.setSuccess(true);
        message.setData(canvas);

        return "redirect:/admin/canvas/list";
    }


    @RequestMapping(value = "/list/data")
    public ResponseEntity<List<Canvas>> getCanvasList(){
        List<Canvas> canvasList=canvasService.findAll();
        return new ResponseEntity<List<Canvas>>(canvasList, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}")
    public String remove(@PathVariable String id){
        canvasService.removeById(id);
        return "redirect:/admin/canvas/list";
    }

}