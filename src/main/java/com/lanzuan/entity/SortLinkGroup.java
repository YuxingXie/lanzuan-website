package com.lanzuan.entity;

import com.lanzuan.entity.support.RootItem;
import com.lanzuan.entity.support.field.SortLink;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "sortLinkGroup")
public class SortLinkGroup extends RootItem {
    @Id
    private String id;
    private String name;
    private Date createDate;
    private boolean enabled;
    private int indexOfPage;//如果页面有多个同类组件，用此字段分别
    private String uri;

    //约定字段
    private List<SortLink> items;
    @DBRef
    private User creator;
    @DBRef
    private PageComponent pageComponent;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<SortLink> getItems() {
        return items;
    }

    public void setItems(List<SortLink> items) {
        this.items = items;
    }

    public int getIndexOfPage() {
        return indexOfPage;
    }

    public void setIndexOfPage(int indexOfPage) {
        this.indexOfPage = indexOfPage;
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
    public String naming() {
        return "分类链接组";
    }

    @Override
    public List<SortLink> children() {
            return items;
    }
}
