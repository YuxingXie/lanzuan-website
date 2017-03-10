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
    private String uri;
    private String editUri;
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



    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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
