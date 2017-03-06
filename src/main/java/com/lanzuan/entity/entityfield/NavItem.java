package com.lanzuan.entity.entityfield;

/**
 * Created by Administrator on 2017/3/6.
 * {"name":"首页","link":"/","faClass":"a-home","navItemCass":"col-xs-3 col-md-2 col-lg-1 margin-0 padding-left-0 padding-right-0 padding-top-10 text-center"},
 */
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
