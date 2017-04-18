package com.lanzuan.entity.support.field;


import com.lanzuan.common.code.Expression;
import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.Navbar;
import com.lanzuan.common.base.annotation.entity.FormAttributes;
import com.lanzuan.entity.support.LeafItem;
import com.lanzuan.common.base.annotation.entity.Naming;
import org.springframework.data.annotation.Transient;

import java.util.List;

public class NavItem  extends LeafItem {
    @Naming(value = "导航项名")
    @FormAttributes
    private String name;

    @Naming(value = "导航项类型")
    @FormAttributes(inputType = InputType.SELECT,optionValues ={"{value:\"link\",text:\"链接\"}","{value:\"menu\",text:\"菜单\"}"} )
    private String navItemType;

    @FormAttributes(inputType = InputType.URL)
    @Naming(value = "导航项链接到" ,when ="navItemType",expression = Expression.IN,params= {"link",""})
    private String link;

    @Naming(value = "导航项菜单" ,when ="navItemType",expression = Expression.EQ,params= {"menu"})
    private List<Link> menuItems;
    private String faClass;
    private String navItemCass;
    @Transient
    private Navbar parent;

    public Navbar getParent() {
        return parent;
    }

    public void setParent(Navbar parent) {
        this.parent = parent;
    }

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
    public Integer repeatLimit() {
        return null;
    }

    @Override
    public Navbar parent() {
        return parent;
    }

    public String getNavItemType() {
        return navItemType;
    }

    public void setNavItemType(String navItemType) {
        this.navItemType = navItemType;
    }

    public List<Link> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Link> menuItems) {
        this.menuItems = menuItems;
    }
}
