package com.lanzuan.entity;

import com.lanzuan.common.code.InputType;
import com.lanzuan.common.base.annotation.entity.ListColumn;
import com.lanzuan.common.base.annotation.entity.Naming;
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
@Naming(value = "卡片组")
@Document(collection = "cardGroup")
public class CardGroup extends RootItem {
    @Id
    private String id;
    @Naming(value = "方案名")
    @ListColumn(columnName = "方案名")
    private String name;
    private String uri;
    //约定字段
    @ListColumn(columnName = "卡片项列表",inputType = InputType.IMAGE,fieldOfValue = "image")
    @Naming(value = "卡片项列表",ngRepeatVar = "card")
    private List<Card> items;
    @ListColumn(columnName = "启用状态")
    private boolean enabled;

    private Date lastModifyDate;
    @DBRef
    private User lastModifyUser;

    @DBRef
    @ListColumn(columnName = "创建人",fieldOfValue = "name")
    private User creator;
    @ListColumn(columnName = "创建日期")
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
    public String projectName() {
        return name;
    }


    @Override
    public List<Card> children() {
        return items;
    }
}
