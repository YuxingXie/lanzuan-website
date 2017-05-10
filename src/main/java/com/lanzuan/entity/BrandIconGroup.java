package com.lanzuan.entity;

import com.lanzuan.common.base.annotation.entity.ListColumn;
import com.lanzuan.common.base.annotation.entity.Naming;
import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.RootItem;
import com.lanzuan.entity.support.field.BrandIcon;
import com.lanzuan.entity.support.field.NavItem;
import com.lanzuan.entity.support.field.NavbarBrand;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
@Document(collection = "brandIconGroup")
@Naming(value = "品牌标签组")
public class BrandIconGroup extends RootItem{
    @Id
    private String id;

    @Naming(value = "方案名")
    @ListColumn(columnName = "方案名")
    private String name;

    @ListColumn(columnName = "开启状态")
    private boolean enabled;

    private Date lastModifyDate;
    private String uri;
    @DBRef
    private User creator;
    @DBRef
    private User lastModifyUser;

    @Naming(value = "品牌标签组",ngRepeatVar = "brandIcon")
    private List<BrandIcon> items;
    @DBRef
    private PageComponent pageComponent;
    private Date createDate;
    @Override
    public List<String> remarks() {
        return null;
    }

    @Override
    public String projectName() {
        return null;
    }

    @Override
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    @Override
    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    @Override
    public User getCreator() {
        return creator;
    }

    @Override
    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
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

    public List<BrandIcon> getItems() {
        return items;
    }

    public void setItems(List<BrandIcon> items) {
        this.items = items;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public List<? extends Item> children() {
        return null;
    }
}
