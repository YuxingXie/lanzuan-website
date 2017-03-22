package com.lanzuan.entity;

import com.lanzuan.entity.support.Editable;
import com.lanzuan.entity.support.Naming;
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
    @Naming("方案名")
    private String name;
    private boolean enabled;
    @Naming(value = "大标题")
    @Editable()
    private String text;
    //约定字段名
    @Naming(value = "图文块",ngRepeatVar = "imageTextBlock")
    private List<ImageTextBlock> items;
    @DBRef
    private User creator;
    private Date createDate;
    @DBRef
    private PageComponent pageComponent;

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
