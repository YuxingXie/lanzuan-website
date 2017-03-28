package com.lanzuan.entity;

import com.lanzuan.common.code.InputType;
import com.lanzuan.common.base.annotation.entity.FormAttributes;
import com.lanzuan.common.base.annotation.entity.ListColumn;
import com.lanzuan.common.base.annotation.entity.Naming;
import com.lanzuan.entity.support.RootItem;
import com.lanzuan.entity.support.field.ImageTextBlock;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Naming(value = "图文块组")
@Document(collection = "imageTextBlockGroup")
public class ImageTextBlockGroup extends RootItem{
    @Id
    private String id;
    private String uri;
    @Naming(value = "方案名")
    @ListColumn(columnName = "方案名")
    private String name;

    @ListColumn(columnName = "启用状态")
    private boolean enabled;
    @ListColumn(columnName = "大标题")
    @Naming(value = "大标题")
    @FormAttributes()
    private String text;
    //约定字段名
    @Naming(value = "图文块",ngRepeatVar = "imageTextBlock")
    @ListColumn(columnName = "块标题" ,fieldOfValue = "name")
    private List<ImageTextBlock> items;
    @ListColumn(columnName = "创建日期" ,inputType = InputType.DATE)
    private Date createDate;
    private Date lastModifyDate;
    @DBRef
    @ListColumn(columnName = "创建人" ,fieldOfValue = "name")
    private User creator;
    @DBRef
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<ImageTextBlock> getItems() {
        return items;
    }

    public void setItems(List<ImageTextBlock> items) {
        this.items = items;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public List<ImageTextBlock> children() {
        return items;
    }
}
