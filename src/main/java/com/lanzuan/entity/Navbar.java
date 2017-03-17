package com.lanzuan.entity;

import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.field.NavItem;
import com.lanzuan.entity.support.field.NavbarBrand;
import com.lanzuan.entity.support.PageComponentData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
@Document(collection = "navbar")
public class Navbar  implements PageComponentData,Item {
    @Id
    private String id;
    private String uri;
    private String name;
    private boolean enabled;
    private NavbarBrand navbarBrand;
    private List<NavItem> items;
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

    public NavbarBrand getNavbarBrand() {
        return navbarBrand;
    }

    public void setNavbarBrand(NavbarBrand navbarBrand) {
        this.navbarBrand = navbarBrand;
    }

    public List<NavItem> getItems() {
        return items;
    }

    public void setItems(List<NavItem> items) {
        this.items = items;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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
}
