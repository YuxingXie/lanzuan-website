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
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pageComponent")
public class PageComponent {
    @Id
    private String id;
    private String name;
    private String templateUri;
    private String editUri;
    private String dataUri;
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

     */
    private String jsonFunctionName;
    /**
     * 在视图中无法知晓js中的变量命名，因为js文件无法用el表达式，所以必须在视图页面指定json数据在js中的变量名，主要在编辑页面使用
     */
    private String jsonVariableName;

    public String getJsonVariableName() {
        return jsonVariableName;
    }

    public void setJsonVariableName(String jsonVariableName) {
        this.jsonVariableName = jsonVariableName;
    }

    public String getJsonFunctionName() {
        return jsonFunctionName;
    }

    public void setJsonFunctionName(String jsonFunctionName) {
        this.jsonFunctionName = jsonFunctionName;
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




}
