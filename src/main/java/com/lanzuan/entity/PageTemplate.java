package com.lanzuan.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 */
@Document(collection = "pageTemplate")
public class PageTemplate {
    @Id
    private String id;
    @Field
    private String uri;
    @Field
    private boolean active;
    @DBRef
    private List<PageComponent> pageComponents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<PageComponent> getPageComponents() {
        return pageComponents;
    }

    public void setPageComponents(List<PageComponent> pageComponents) {
        this.pageComponents = pageComponents;
    }
}
