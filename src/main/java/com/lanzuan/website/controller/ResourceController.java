package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.Article;
import com.lanzuan.entity.WebResource;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Controller
@RequestMapping("/admin/resource")
public class ResourceController extends BaseRestSpringController {
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
    public String articleList(HttpServletRequest request,ModelMap modelMap) throws IOException {
        List<WebResource> webResourceList = getWebResources(request);
        modelMap.addAttribute("webResourceList", webResourceList);
        String msg=modelMap.get("msg")==null?null:modelMap.get("msg").toString();
        if(msg!=null) modelMap.addAttribute("msg",msg);
        return "admin/resource-list";
    }
    @RequestMapping(value = "/upload")
    public String uploadResource(@RequestParam("file") MultipartFile file,@RequestParam("type") String type,HttpServletRequest request,RedirectAttributes redirectAttributes) throws IOException {

        // 判断文件是否为空
        if (!file.isEmpty()) {
                String suffix= FileUtil.getFileTypeByOriginalFilename(file.getOriginalFilename());
                String fileName=file.getOriginalFilename();
                String dir=request.getServletContext().getRealPath("/") +Constant.ICO_DIR;
                if (StringUtils.isBlank(type)){
                    dir=request.getServletContext().getRealPath("/")+Constant.DOCUMENT_DIR;
                }else if(type.equals("image")){
                    dir=request.getServletContext().getRealPath("/")+Constant.DOCUMENT_IMAGE_DIR;
                }else if(type.equals("video")){
                    dir=request.getServletContext().getRealPath("/")+Constant.DOCUMENT_VIDEO_DIR;
                }else if(type.equals("file")){
                    dir=request.getServletContext().getRealPath("/")+Constant.DOCUMENT_FILE_DIR;
                }else{
                    redirectAttributes.addFlashAttribute("msg","没有选定资源类型或资源类型不正确，请重新上传!");
                    return "redirect:/admin/resource/list";
                }
                String filePath = dir+"/"+fileName;
                File fileDir=new File(dir);
                if (!fileDir.exists()){
                    fileDir.mkdirs();
                }
                // 转存文件
                file.transferTo(new File(filePath));
        }
        return "redirect:/admin/resource/list";
    }

    @RequestMapping(value = "/remove")
    public String removeResource(@RequestParam String path,HttpServletRequest request,RedirectAttributes redirectAttributes) throws IOException {
        byte bb[];
        bb = path.getBytes("ISO-8859-1");
        path= new String(bb, "UTF-8");
        ServletContextResource resource=new ServletContextResource(request.getServletContext(),path);
        File file=resource.getFile();
        if (file.exists())
            file.delete();
        return "redirect:/admin/resource/list";
    }


}