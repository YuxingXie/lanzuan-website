package com.lanzuan.entity;

import com.lanzuan.entity.entityfield.SortLink;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
/*
db.articleSection.update({},{"$set":{"enabled":true}},false,true)
db.articleSection.update({"name":"活动专题"},{"$set":{"enabled":true}},false,true)
db.articleSection.update({"name":"新闻动态"},{"$set":{"articles":null}},false,true)
db.articleSection.update({"name":"企业文化"},{"$set":{"articles":null}},false,true)
 */
@Document(collection = "sortLinkGroup")
public class SortLinkGroup {
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
}
