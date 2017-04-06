package com.lanzuan.common.base;


import com.lanzuan.common.constant.Constant;
import com.lanzuan.entity.User;
import com.lanzuan.entity.WebResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.support.ServletContextResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BaseRestSpringController  {
    protected static final String CREATED_SUCCESS = "创建成功";
    protected static final String UPDATE_SUCCESS = "更新成功";
    protected static final String DELETE_SUCCESS = "删除成功";
    
    protected static final String RESULT_STRING = "result";
    protected static final String TOTAL = "total";
    protected static final String RESULT_ACTION = "redirect:/result";
    @Value(value = "${app.httpPort}")
    protected String httpPort;

    private static Logger logger = LogManager.getLogger();
    public String getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(String httpPort) {
        this.httpPort = httpPort;
    }


    public static void copyProperties(Object target,Object source) {
        BeanUtils.copyProperties(target, source);
    }

    protected void printRequestParameters(HttpServletRequest request){
        Map<String,String[]> requestMap=request.getParameterMap();
        for (String key:requestMap.keySet()){
            String str=new String();
            str+=key + ":[";
            for (String val:requestMap.get(key)){
                str+=val + ",";
            }
            if (str.endsWith(","))  logger.info(str.substring(0,str.length()-1)+"]\n");
            else logger.info(str+"]\n");
        }
    }
    protected User getLoginUser(HttpSession session) {
        return session.getAttribute(Constant.LOGIN_USER)==null?null:((User)(session.getAttribute(Constant.LOGIN_USER)));
    }
    protected User getLoginAdministrator(HttpSession session) {
        return session.getAttribute(Constant.LOGIN_ADMINISTRATOR)==null?null:((User)(session.getAttribute(Constant.LOGIN_ADMINISTRATOR)));
    }

    public static <T> T getOrCreateRequestAttribute(HttpServletRequest request, String key,Class<T> clazz) {
        Object value = request.getAttribute(key);
        if(value == null) {
            try {
                value = clazz.newInstance();
            } catch (Exception e) {
                ReflectionUtils.handleReflectionException(e);
            }
            request.setAttribute(key, value);
        }
        return (T)value;
    }
    protected String getHttpUrlString(HttpServletRequest request,String vieName) {
        String path = request.getContextPath();
//        String basePath ="http://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String basePath ="http://"+request.getServerName()+":"+httpPort+path+"/";
        return basePath+vieName;
    }
    protected String getInputHttpUrlString(HttpServletRequest request) {
        String path = request.getContextPath();
//        String basePath ="http://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String basePath ="http://"+request.getServerName()+":"+httpPort+path+"/";
        String uri=request.getRequestURI();
        return basePath+uri;
    }
    protected String getHttpUrlStringFromUri(HttpServletRequest request,String uri) {
        String path = request.getContextPath();
//        String basePath ="http://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String url ="http://"+request.getServerName()+":"+httpPort+uri;
        return url;
    }
    protected List<WebResource> getWebResources(HttpServletRequest request) throws IOException {
        ServletContextResource dirResource=new ServletContextResource(request.getServletContext(), Constant.DOCUMENT_DIR);
        ServletContextResource fileResource=new ServletContextResource(request.getServletContext(), Constant.DOCUMENT_FILE_DIR);
        ServletContextResource imageResource=new ServletContextResource(request.getServletContext(), Constant.DOCUMENT_IMAGE_DIR);
        ServletContextResource videoResource=new ServletContextResource(request.getServletContext(), Constant.DOCUMENT_VIDEO_DIR);
        File dirFile=dirResource.getFile();
        File fileDirFile=fileResource.getFile();
        File imageDirFile=imageResource.getFile();
        File videoDirFile=videoResource.getFile();
        if (!dirFile.exists())dirFile.mkdirs();
        if (!fileDirFile.exists())fileDirFile.mkdirs();
        if (!imageDirFile.exists())imageDirFile.mkdirs();
        if (!videoDirFile.exists())videoDirFile.mkdirs();
        List<WebResource> webResourceList=new ArrayList<WebResource>();
        if(dirFile.listFiles()!=null &&dirFile.listFiles().length>0)
            for(File file:dirFile.listFiles()){
                if (file.isDirectory()) continue;
                WebResource webResource=new WebResource();
                webResource.setType("未分类");
                webResource.setName(file.getName());
                webResource.setPath(Constant.DOCUMENT_DIR +"/"+file.getName());
                webResourceList.add(webResource);
            }
        if(fileDirFile.listFiles()!=null &&fileDirFile.listFiles().length>0)
            for(File file:fileDirFile.listFiles()){
                if (file.isDirectory()) continue;
                WebResource webResource=new WebResource();
                webResource.setType("文件");
                webResource.setName(file.getName());
                webResource.setPath(Constant.DOCUMENT_FILE_DIR +"/"+file.getName());
                webResourceList.add(webResource);
            }
        if(imageDirFile.listFiles()!=null &&imageDirFile.listFiles().length>0)
            for(File file:imageDirFile.listFiles()){
                if (file.isDirectory()) continue;
                WebResource webResource=new WebResource();
                webResource.setType("图片");
                webResource.setName(file.getName());
                webResource.setPath(Constant.DOCUMENT_IMAGE_DIR +"/"+file.getName());
                webResourceList.add(webResource);
            }
        if(videoDirFile.listFiles()!=null &&videoDirFile.listFiles().length>0)
            for(File file:videoDirFile.listFiles()){
                if (file.isDirectory()) continue;
                WebResource webResource=new WebResource();
                webResource.setType("视频");
                webResource.setName(file.getName());
                webResource.setPath(Constant.DOCUMENT_VIDEO_DIR +"/"+file.getName());
                webResourceList.add(webResource);
            }
        if (webResourceList.size()==0) return null;
        return webResourceList;
    }

    /**
     * 根据useAgent判断浏览器类型
     * @param agent
     * @return
     */
    public String getBrowserName(String agent) {
        if (agent.indexOf("msie 7") > 0) {
            return "ie7";
        } else if (agent.indexOf("msie 8") > 0) {
            return "ie8";
        } else if (agent.indexOf("msie 9") > 0) {
            return "ie9";
        } else if (agent.indexOf("msie 10") > 0) {
            return "ie10";
        } else if (agent.indexOf("msie") > 0) {
            return "ie";
        } else if (agent.indexOf("opera") > 0) {
            return "opera";
        } else if (agent.indexOf("opera") > 0) {
            return "opera";
        } else if (agent.indexOf("firefox") > 0) {
            return "firefox";
        } else if (agent.indexOf("webkit") > 0) {
            return "webkit";
        } else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
            return "ie11";
        } else {
            return "Others";
        }
    }
}
