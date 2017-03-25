package com.lanzuan.website.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.CardGroup;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.ICardGroupService;
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
@RequestMapping("/admin/card-group")
public class AdminCardGroupController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "cardGroupService")
    ICardGroupService cardGroupService;


    @RequestMapping(value = "/update")
    public ResponseEntity<Message> update(@RequestBody CardGroup cardGroup){
        Message message=new Message();
        cardGroupService.update(cardGroup);
        message.setSuccess(true);
        message.setData(cardGroup);

        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/image/upload-input/{pageComponentId}")
    public String iconUploadInput(ModelMap modelMap,@PathVariable String pageComponentId){
        modelMap.addAttribute("pageComponentId", pageComponentId);
        return "admin/img-card-group-input";
    }
    @RequestMapping(value = "/image/new/{pageComponentId}")
    public String articleSectionAddImage(@RequestParam("file") MultipartFile file,@PathVariable String pageComponentId,HttpServletRequest request){

        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String type= FileUtil.getFileTypeByOriginalFilename(file.getOriginalFilename());
                String fileName=System.currentTimeMillis()+ type;
                String dir=request.getServletContext().getRealPath("/") + Constant.CARD_GROUP_IMAGE_DIR;
                String filePath = dir+"/"+fileName;
                File fileDir=new File(dir);
                if (!fileDir.exists()){
                    fileDir.mkdirs();
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
    @RequestMapping(value = "/images/data")
    public ResponseEntity<List<String>> getIcons(HttpServletRequest request) throws IOException {
        ServletContextResource resource=new ServletContextResource(request.getServletContext(), Constant.CARD_GROUP_IMAGE_DIR);
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
                    ServletContextResource fileResource=new ServletContextResource(request.getServletContext(),Constant.CARD_GROUP_IMAGE_DIR +"/"+file.getName());
                    strings.add(fileResource.getPath());
                }
            }
        }

        return new ResponseEntity<List<String>>(strings, HttpStatus.OK);
    }
    @RequestMapping(value = "/save-as")
    public ResponseEntity<Message> saveAs(@RequestBody CardGroup cardGroup,HttpSession session){
        CardGroup old=cardGroupService.findById(cardGroup.getId());
        Message message=new Message();
        cardGroup.setId(null);
        cardGroup.setEnabled(false);
//        cardGroup.setCreator(getLoginAdministrator(session));
        cardGroup.setCreateDate(new Date());
        cardGroupService.insert(cardGroup);
        message.setSuccess(true);
        message.setData(old);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/list-page/{pageComponentId}")
    public String remove(@PathVariable String pageComponentId,ModelMap modelMap){
        modelMap.addAttribute("pageComponentId",pageComponentId);
        return "admin/card-group-list";
    }
    @RequestMapping(value = "/list/data")
    public ResponseEntity<List<CardGroup>> getCardGroupList(){
        List<CardGroup> navbarList=cardGroupService.findAll();
        return new ResponseEntity<List<CardGroup>>(navbarList, HttpStatus.OK);
    }
    @RequestMapping(value = "/status-change")
    public ResponseEntity<List<CardGroup>> statusChange(@RequestBody CardGroup cardGroup){
        cardGroup.setEnabled(!cardGroup.isEnabled());
        cardGroupService.update(cardGroup, false);
        return getCardGroupList();
    }
    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<List<CardGroup>> remove(@PathVariable String id){
        cardGroupService.removeById(id);
        return getCardGroupList();
    }

}