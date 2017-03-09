package com.lanzuan.entity;

import com.lanzuan.entity.entityfield.NavItem;
import com.lanzuan.entity.entityfield.NavbarBrand;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "navBar")
public class Navbar {
    @Id
    private String id;
    @Field
    private NavbarBrand navbarBrand;
    @Field
    private List<NavItem> navItems;

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

    public List<NavItem> getNavItems() {
        return navItems;
    }

    public void setNavItems(List<NavItem> navItems) {
        this.navItems = navItems;
    }
}