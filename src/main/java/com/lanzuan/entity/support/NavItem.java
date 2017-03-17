package com.lanzuan.entity.support;


public class NavItem {
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
}
