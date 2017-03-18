package com.lanzuan.entity;

import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.support.Item;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pageComponent")
public class PageComponent<T extends Item> {
    @Id
    private String id;
    private String name;
    /*
    模板按理应该是一个类型不限的静态资源，并使用某种模板语言渲染，但这种方式需要引入一门模板语言，所以jsp作为模板，
    jsp实际上可以看做专门针对web程序的专用模板语言
     */
    private String templateUri;
    private String editUri;
    private String dataUri;
    private String saveUri;
    private String saveAsUri;
    private String toggleUri;
    private String listOperationUri;
    private String deleteUri;
    private String listDataUri;
    private String previewUri;//预览页面采用angularjs获取数据，在客户端渲染的方式，配合编辑页(editUri)以获得所见即所得的编辑效果，与官网不一样
    private String websiteUri;//官网使用的uri，采用jstl和el展示和轮询数据，采用这种方式是在服务器渲染数据,主要是为了提高页面响应速度
    @DBRef
    private T data;
    @Transient
    private WebPage webPage;
    //素材上传uri
    private String materialUploadUri;

    public String getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(String websiteUri) {
        this.websiteUri = websiteUri;
    }

    private String remark;

    @Field(value = "jsonVariableName")
    private String var;
    @Transient
    private String varU;
    public String getVar() {
        return var;
    }
    public String getVarU(){
        return StringUtils.firstUpperCase(var);
    }

    public void setVar(String var) {
        this.var = var;
    }

    public WebPage getWebPage() {
        return webPage;
    }

    public void setWebPage(WebPage webPage) {
        this.webPage = webPage;
    }

    public String getDataUri() {
        return dataUri;
    }

    public void setDataUri(String dataUri) {
        this.dataUri = dataUri;
    }

    public String getPreviewUri() {
        return previewUri;
    }

    public void setVarU(String varU) {
        this.varU = varU;
    }

    public void setPreviewUri(String previewUri) {
        this.previewUri = previewUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTemplateUri() {
        return templateUri;
    }

    public void setTemplateUri(String templateUri) {
        this.templateUri = templateUri;
    }

    public String getEditUri() {
        return editUri;
    }

    public void setEditUri(String editUri) {
        this.editUri = editUri;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getSaveUri() {
        return saveUri;
    }

    public void setSaveUri(String saveUri) {
        this.saveUri = saveUri;
    }

    public String getSaveAsUri() {
        return saveAsUri;
    }

    public void setSaveAsUri(String saveAsUri) {
        this.saveAsUri = saveAsUri;
    }

    public String getToggleUri() {
        return toggleUri;
    }

    public void setToggleUri(String toggleUri) {
        this.toggleUri = toggleUri;
    }

    public String getListOperationUri() {
        return listOperationUri;
    }

    public void setListOperationUri(String listOperationUri) {
        this.listOperationUri = listOperationUri;
    }

    public String getDeleteUri() {
        return deleteUri;
    }

    public void setDeleteUri(String deleteUri) {
        this.deleteUri = deleteUri;
    }

    public String getListDataUri() {
        return listDataUri;
    }

    public void setListDataUri(String listDataUri) {
        this.listDataUri = listDataUri;
    }

    public String getMaterialUploadUri() {
        return materialUploadUri;
    }

    public void setMaterialUploadUri(String materialUploadUri) {
        this.materialUploadUri = materialUploadUri;
    }
}
