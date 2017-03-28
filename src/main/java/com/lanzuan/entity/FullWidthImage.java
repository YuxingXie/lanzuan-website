package com.lanzuan.entity;

import com.lanzuan.common.code.InputType;
import com.lanzuan.common.base.annotation.entity.Item;
import com.lanzuan.common.base.annotation.entity.ListColumn;
import com.lanzuan.common.base.annotation.entity.Naming;
import com.lanzuan.entity.support.RootItem;
import com.lanzuan.support.vo.Image;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Naming(value = "全屏大图")
@Document(collection = "fullWidthImage")
public class FullWidthImage extends RootItem{
    @Id
    private String id;
    @Naming("图片信息")
    @ListColumn(columnName = "图片",fieldOfValue = "uri",inputType = InputType.IMAGE)
    private Image image;
    private String name;
    @ListColumn(columnName = "开启状态")
    private boolean enabled;
    private String uri;
    @ListColumn(columnName = "最后修改日期" ,inputType = InputType.DATE)
    private Date lastModifyDate;
    @DBRef
    @ListColumn(columnName = "创建人" ,fieldOfValue = "name")
    private User creator;
    @DBRef
    @ListColumn(columnName = "最后修改人" ,fieldOfValue = "name")
    private User lastModifyUser;
    @DBRef
    private PageComponent pageComponent;

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    @Override
    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public User getCreator() {
        return creator;
    }

    @Override
    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getLastModifyUser() {
        return lastModifyUser;
    }

    @Override
    public void setLastModifyUser(User lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public PageComponent getPageComponent() {
        return pageComponent;
    }

    public void setPageComponent(PageComponent pageComponent) {
        this.pageComponent = pageComponent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public List<String> remarks() {
        return null;
    }

    @Override
    public String projectName() {
        return name;
    }


    @Override
    public List<? extends Item> children() {
        return null;
    }
}
