package com.lanzuan.entity;

import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.field.ImageTextBlock;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
@Document(collection = "imageTextBlockGroup")
public class ImageTextBlockGroup  implements Item{
    @Id
    private String id;
    private String uri;
    private String name;
    private boolean enabled;
    private String text;
    //约定字段名
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
    public List<? extends Item> childItems() {
        return items;
    }

    @Override
    public Integer repeatLimit() {
        return 20;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String text() {
        return name;
    }

    @Override
    public String image() {
        return null;
    }

    @Override
    public String href() {
        return null;
    }

    @Override
    public String title() {
        return text();
    }
}
