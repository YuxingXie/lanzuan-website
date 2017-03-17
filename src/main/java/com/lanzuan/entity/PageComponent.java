package com.lanzuan.entity;

import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.support.PageComponentData;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pageComponent")
public class PageComponent<T extends PageComponentData> {
    @Id
    private String id;
    private String name;
    private String templateUri;
    private String editUri;
    private String dataUri;
    private String saveUri;
    private String saveAsUri;
    private String toggleUri;
    private String listOperationUri;
    private String deleteUri;
    private String listDataUri;
    @DBRef
    private T data;
    @Transient
    private WebPage webPage;
    //素材上传uri
    private String materialUploadUri;

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
