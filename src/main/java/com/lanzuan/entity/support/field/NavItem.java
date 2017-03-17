package com.lanzuan.entity.support.field;


import com.lanzuan.entity.support.Item;

import java.util.List;

public class NavItem implements Item{
    private String name;
    private String link;
    //    font-awesome class
    private String faClass;
    private String navItemCass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFaClass() {
        return faClass;
    }

    public void setFaClass(String faClass) {
        this.faClass = faClass;
    }

    public String getNavItemCass() {
        return navItemCass;
    }

    public void setNavItemCass(String navItemCass) {
        this.navItemCass = navItemCass;
    }

    @Override
    public List<? extends Item> childItems() {
        return null;
    }

    @Override
    public Integer repeatLimit() {
        return 20;
    }
}
