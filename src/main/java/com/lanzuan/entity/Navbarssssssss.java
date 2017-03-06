package com.lanzuan.entity;

import com.lanzuan.entity.entityfield.NavItem;
import com.lanzuan.entity.entityfield.NavbarBrand;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 db.navbar.insert({"navbarBrand":{"type":"image","value":"/statics/image/lanzuan/icons/ico.jpg"},"navItems":[{"name":"首页","link":"/","faClass":"a-home","navItemCass":"col-xs-3 col-md-2 col-lg-1 margin-0 padding-left-0 padding-right-0 padding-top-10 text-center"},{"name":"智慧城市","link":"/statics/page/business/b1.html","faClass":"fa-wifi","navItemCass":"col-xs-3 col-md-2 col-lg-1 margin-0 padding-left-0 padding-right-0 padding-top-10 text-center"},{"name":"三农服务","link":"/statics/page/business/b2.html","faClass":"fa-tree","navItemCass":"col-xs-3 col-md-2 col-lg-1 margin-0 padding-left-0 padding-right-0 padding-top-10 text-center"},{"name":"软件开发","link":"/statics/page/business/b3.html","faClass":"fa-safari","navItemCass":"col-xs-3 col-md-2 col-lg-1 margin-0 padding-left-0 padding-right-0 padding-top-10 text-center"},{"name":"关于我们","link":"/statics/page/about-us.html","faClass":"fa-male","navItemCass":"col-xs-3 col-md-2 col-lg-1 margin-0 padding-left-0 padding-right-0 padding-top-10 text-center"}]})
 */
@Document(collection = "navbar")
public class Navbarssssssss {
    @Id
    private String id;
    @Field
   private NavbarBrand navbarBrand;
    @Field
    private List<NavItem> navItems ;

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
