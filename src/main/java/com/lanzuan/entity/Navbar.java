package com.lanzuan.entity;

import com.lanzuan.entity.entityfield.NavItem;
import com.lanzuan.entity.entityfield.NavbarBrand;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
@Document(collection = "navbar")
public class Navbar {
    @Id
    private String id;
    private String uri;
    private String name;
    private boolean enabled;
    private NavbarBrand navbarBrand;
    private List<NavItem> navbarItems;


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

    public List<NavItem> getNavbarItems() {
        return navbarItems;
    }

    public void setNavbarItems(List<NavItem> navbarItems) {
        this.navbarItems = navbarItems;
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
}
