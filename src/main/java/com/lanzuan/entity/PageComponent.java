package com.lanzuan.entity;
/*
db.pageComponent.update({"name":"文章块组件1"},{"$set":{"editUri":"/statics/page/included/lanzuan/article-section-1-edit.jsp"}},false,true)
db.pageComponent.update({"name":"响应式导航条模板1"},{"$set":{"editUri":"/statics/page/included/component/navbar/navbar-md-down-fix-bottom-edit.html"}},false,true)
db.pageComponent.update({"name":"响应式轮播图"},{"$set":{"editUri":"/statics/page/included/component/carousel/carousel-full-width-1-edit.jsp"}},false,true)
db.pageComponent.update({"name":"图文卡片组模板1"},{"$set":{"editUri":"/statics/page/included/component/card-group/img-card-group-1-edit.html"}},false,true)
db.pageComponent.update({"name":"蓝钻鼠标掠过类似手风琴模板1"},{"$set":{"editUri":"/statics/page/included/lanzuan/collapse-image-title-text-1-edit.html"}},false,true)
db.pageComponent.update({"name":"全屏宽度图片模板1"},{"$set":{"editUri":"/statics/page/included/lanzuan/full-width-image-1-edit.html"}},false,true)
db.pageComponent.update({"name":"分类链接模板1"},{"$set":{"editUri":"/statics/page/included/lanzuan/sort-link-section-1-edit.html"}},false,true)
  */
import com.lanzuan.common.util.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pageComponent")
public class PageComponent {
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
    @Transient
    private WebPage webPage;
    //素材上传uri
    private String materialUploadUri;
    /**
     *     详细说明：
     *     此变量名代表该组件数据在js中数据的变量名
     *     如果客户端采用javaScript异步数据狂框架,我们用此字段表示的javascript变量名存储数据
     *     本系统前端使用的是angularjs,无论任何框架，拿到的数据总要保存在一个javascript变量中
     *     假设我们用一个js函数getExampleData(dataUri){
     *         variable={ a json or json array data}
     *     }
     *     在前端可以类似于如下调用函数：
     *     ${pageComponent.jsonFunctionName}('${pageComponent.dataUri}'})
     *     实际上相当于getter方法名
     * 在视图中无法知晓js中的变量命名，因为js文件无法用el表达式，所以必须在视图页面指定json数据在js中的变量名，主要在编辑页面使用
     */
    private String jsonVariableName;
    private String variableFirstUpper;
    public String getJsonVariableName() {
        return jsonVariableName;
    }
    public String getVariableFirstUpper(){
        return StringUtils.firstUpperCase(jsonVariableName);
    }

    public void setJsonVariableName(String jsonVariableName) {
        this.jsonVariableName = jsonVariableName;
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

    private String remark;


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
