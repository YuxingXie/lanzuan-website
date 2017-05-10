package com.lanzuan.admin.controller;

import com.lanzuan.common.base.BaseRestSpringController;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.common.util.FileUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.BrandIconGroup;
import com.lanzuan.entity.BrandIconGroup;
import com.lanzuan.entity.User;
import com.lanzuan.support.vo.Message;
import com.lanzuan.website.service.IBrandIconGroupService;
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
@RequestMapping("/admin/brand-icon-group")
public class BrandIconGroupController extends BaseRestSpringController {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "brandIconGroupService")
    IBrandIconGroupService brandIconGroupService;


    @RequestMapping(value = "/update")
    public ResponseEntity<Message> update(@RequestBody BrandIconGroup brandIconGroup,HttpSession session){
        Message message=new Message();
        Date now=new Date();
        User user=getLoginUser(session);
        if (brandIconGroup.getId()==null){
            brandIconGroup.setCreator(user);
        }
        brandIconGroup.setLastModifyDate(now);
        brandIconGroup.setLastModifyUser(user);
        brandIconGroupService.upsert(brandIconGroup);
        message.setSuccess(true);
        message.setData(brandIconGroup);

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
                String dir=request.getServletContext().getRealPath("/") + Constant.BRAND_ICON_GROUP_IMAGE_DIR;
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
    @RequestMapping(value = "/image/data")
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
    public ResponseEntity<Message> saveAs(@RequestBody BrandIconGroup brandIconGroup,HttpSession session){
        BrandIconGroup old=brandIconGroupService.findById(brandIconGroup.getId());
        Message message=new Message();
        brandIconGroup.setId(null);
        brandIconGroup.setEnabled(false);
//        BrandIconGroup.setCreator(getLoginAdministrator(session));
        brandIconGroup.setCreateDate(new Date());
        brandIconGroupService.insert(brandIconGroup);
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
    public ResponseEntity<List<BrandIconGroup>> getBrandIconGroupList(){
        List<BrandIconGroup> navbarList=brandIconGroupService.findAll();
        return new ResponseEntity<List<BrandIconGroup>>(navbarList, HttpStatus.OK);
    }
    @RequestMapping(value = "/status-change")
    public ResponseEntity<List<BrandIconGroup>> statusChange(@RequestBody BrandIconGroup brandIconGroup){
        brandIconGroup.setEnabled(!brandIconGroup.isEnabled());
        brandIconGroupService.update(brandIconGroup, false);
        return getBrandIconGroupList();
    }
    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<List<BrandIconGroup>> remove(@PathVariable String id){
        brandIconGroupService.removeById(id);
        return getBrandIconGroupList();
    }

}