package com.lanzuan.entity;
/**
db.pageComponent.update({"name":"响应式导航条模板1"},{"$set":{"materialUri":"/admin/icons/data"}},false,true)
db.pageComponent.update({"name":"响应式轮播图"},{"$set":{"materialUri":"/admin/carousel-images/data"}},false,true)
db.pageComponent.update({"name":"图文卡片组模板1"},{"$set":{"materialUri":"/admin/card-group/images/data"}},false,true)
db.pageComponent.update({"name":"蓝钻鼠标掠过类似手风琴模板1"},{"$set":{"materialUri":"/admin/image-text-block-group/image/data"}},false,true)
db.pageComponent.update({"name":"文章块组件1"},{"$set":{"materialUri":"/admin/sort-link-group/image/data"}},false,true)
db.pageComponent.update({"name":"全屏宽度图片模板1"},{"$set":{"materialUri":"/admin/full-width-image//image/data"}},false,true)
db.pageComponent.update({"name":"分类链接模板1"},{"$set":{"materialUri":"/admin/sort-link-group/image/data"}},false,true)
db.pageComponent.insert({"uri":"/home","name":"新闻及图片展示模板","remark":"一组新闻(第一条带图片)及一组图片，中等以上设备左右排列，中等以下设备上下排列。","websiteUri":"/statics/page/included/lanzuan/image-title-text-block1.jsp","var":"services"，"dataUri":"/image-text-block-group/home/data"})
db.pageComponent.insert({"uri":"/home","var":"services"})
db.pageComponent.update({"var":"services"},{"$set":{"remark":"一组新闻(第一条带图片)及一组图片，中等以上设备左右排列，中等以下设备上下排列。"}},false,true)
db.pageComponent.update({"var":"services"},{"$set":{"websiteUri":"/statics/page/included/lanzuan/image-title-text-block1.jsp","dataUri":"/image-text-block-group/home/data"}},false,true)

db.pageComponent.insert({"uri":"/home","name":"品牌图标组模板","var":"articlesAndImages"})
db.pageComponent.update({"var":"articlesAndImages"},{"$set":{"websiteUri":"/statics/page/included/lanzuan/articles-and-images-1.jsp"}},false,true)
db.pageComponent.update({"var":"articlesAndImages"},{"$set":{"jsonVariableName":"articlesAndImages"}},false,true)
db.pageComponent.update({"var":"articlesAndImages"},{"$set":{"dataUri":"/articles-images/home/data"}},false,true)
db.pageComponent.update({"var":"articlesAndImages"},{"$set":{"remark":"多用于展示多个企业的一组图标"}},false,true)
db.pageComponent.update({"var":"articlesAndImages"},{"$set":{"articles":{},"images":{}}},false,true)
db.articlesAndImages.insert({"createDate":new Date()})
db.pageComponent.update({"jsonVariableName":"articlesAndImages"},{"$set":{"data":DBRef("articlesAndImages",ObjectId("590185a0cbab43aa1cbe9176"))}},false,true)
db.pageComponent.update({"jsonVariableName":"articlesAndImages"},{"$set":{"_class":"com.lanzuan.entity.PageComponent"}},false,true)
 db.pageComponent.remove({"jsonVariableName":"articlesAndImages"})
 */

import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.support.RootItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "pageComponent")
public class PageComponent<T extends RootItem> {
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
    //素材资源uri
    private String materialUri;
    //素材上传uri
    private String materialUploadUri;
    private String remark;
    @Field(value = "jsonVariableName")
    private String var;
    private List<String> editNotes;
    private List<String> listOperationNotes;
    @Transient
    private String varU;
    @Transient
    private String jsGetterMethods;
    public String getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(String websiteUri) {
        this.websiteUri = websiteUri;
    }

    public String getJsGetterMethods() {
        return jsGetterMethods;
    }

    public void setJsGetterMethods(String jsGetterMethods) {
        this.jsGetterMethods = jsGetterMethods;
    }

    public List<String> getEditNotes() {
        return editNotes;
    }

    public void setEditNotes(List<String> editNotes) {
        this.editNotes = editNotes;
    }

    public List<String> getListOperationNotes() {
        return listOperationNotes;
    }

    public void setListOperationNotes(List<String> listOperationNotes) {
        this.listOperationNotes = listOperationNotes;
    }

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


    public String getMaterialUri() {
        return materialUri;
    }

    public void setMaterialUri(String materialUri) {
        this.materialUri = materialUri;
    }


    public PageComponent() {


    }
}
