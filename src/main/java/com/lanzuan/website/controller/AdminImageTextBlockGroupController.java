package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.ImageTextBlockGroup;
import com.lanzuan.entity.User;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.IImageTextBlockGroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/admin/image-text-block-group")
public class AdminImageTextBlockGroupController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "imageTextBlockGroupService")
    IImageTextBlockGroupService imageTextBlockGroupService;


    @RequestMapping(value = "/update")
    public ResponseEntity<Message> update(@RequestBody ImageTextBlockGroup imageTextBlockGroup,HttpSession session){
        Message message=new Message();
        Date now=new Date();
        User user=getLoginUser(session);
        if (imageTextBlockGroup.getId()==null){
            imageTextBlockGroup.setCreator(user);
        }
        imageTextBlockGroup.setLastModifyDate(now);
        imageTextBlockGroup.setLastModifyUser(user);

        imageTextBlockGroupService.update(imageTextBlockGroup);
        message.setSuccess(true);
        message.setData(imageTextBlockGroup);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/save-as")
    public ResponseEntity<Message> saveAs(@RequestBody ImageTextBlockGroup imageTextBlockGroup,HttpSession session){
        Message message=new Message();
        ImageTextBlockGroup old=imageTextBlockGroupService.findById(imageTextBlockGroup.getId());
        imageTextBlockGroup.setId(null);
        imageTextBlockGroup.setEnabled(false);
//        imageTextBlockGroup.setCreator(getLoginAdministrator(session));
        imageTextBlockGroup.setCreateDate(new Date());
        imageTextBlockGroupService.insert(imageTextBlockGroup);
        message.setSuccess(true);
        message.setData(old);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/list-page/{pageComponentId}")
    public String remove(@PathVariable String pageComponentId,ModelMap modelMap){
        modelMap.addAttribute("pageComponentId", pageComponentId);
        return "admin/image-text-block-group-list";
    }
    @RequestMapping(value = "/list/data")
    public ResponseEntity<List<ImageTextBlockGroup>> getCardGroupList(){
        List<ImageTextBlockGroup> groups=imageTextBlockGroupService.findAll();
        return new ResponseEntity<List<ImageTextBlockGroup>>(groups, HttpStatus.OK);
    }
    @RequestMapping(value = "/status-change")
    public ResponseEntity<List<ImageTextBlockGroup>> statusChange(@RequestBody ImageTextBlockGroup imageTextBlockGroup){
        imageTextBlockGroup.setEnabled(!imageTextBlockGroup.isEnabled());
        imageTextBlockGroupService.update(imageTextBlockGroup, false);
        return getCardGroupList();
    }
    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<List<ImageTextBlockGroup>> remove(@PathVariable String id){
        imageTextBlockGroupService.removeById(id);
        return getCardGroupList();
    }
    @RequestMapping(value = "/image/upload-input/{pageComponentId}")
    public String iconUploadInput(ModelMap modelMap,@PathVariable String pageComponentId){
        modelMap.addAttribute("pageComponentId",pageComponentId);
        return "admin/img-text-block-group-item-image-input";
    }
    @RequestMapping(value = "/image/add/{pageComponentId}")
    public String articleSectionAddImage(@RequestParam("file") MultipartFile file,@PathVariable String pageComponentId,HttpServletRequest request){

        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String type= FileUtil.getFileTypeByOriginalFilename(file.getOriginalFilename());
                String fileName=System.currentTimeMillis()+ type;
                String dir=request.getServletContext().getRealPath("/") + Constant.IMAGE_TEXT_BLOCK_GROUP_ITEM_IMAGE_DIR;
                String filePath =dir +"/"+fileName;
                File dirFile=new File(dir);
                if (!dirFile.exists()){
                    dirFile.mkdirs();
                }
                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(pageComponentId)){
            return "redirect:/admin/page-component/edit/"+pageComponentId;
        }
        return "redirect:/admin/index";
    }
    @RequestMapping(value = "/image/data")
    public ResponseEntity<List<String>> getIcons(HttpServletRequest request) throws IOException {
        String fileDirectory=Constant.IMAGE_TEXT_BLOCK_GROUP_ITEM_IMAGE_DIR;
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