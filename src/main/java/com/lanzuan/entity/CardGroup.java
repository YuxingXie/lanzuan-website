package com.lanzuan.entity;

import com.lanzuan.entity.support.RootItem;
import com.lanzuan.entity.support.field.Card;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
@Document(collection = "cardGroup")
public class CardGroup extends RootItem {
    @Id
    private String id;
    private String name;
    private String uri;
    //约定字段
    private List<Card> items;
    private boolean enabled;
    @DBRef
    private User creator;
    private Date createDate;
    @DBRef
    private PageComponent pageComponent;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Card> getItems() {
        return items;
    }

    public void setItems(List<Card> items) {
        this.items = items;
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

    public PageComponent getPageComponent() {
        return pageComponent;
    }

    public void setPageComponent(PageComponent pageComponent) {
        this.pageComponent = pageComponent;
    }

    @Override
    public List<String> remarks() {
        return null;
    }

    @Override
    public String naming() {
        return "卡片组";
    }

    @Override
    public List<Card> children() {
        return items;
    }
}
