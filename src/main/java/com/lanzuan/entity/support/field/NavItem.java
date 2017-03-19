package com.lanzuan.entity.support.field;


import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.Navbar;
import com.lanzuan.entity.support.Editable;
import com.lanzuan.entity.support.LeafItem;
import com.lanzuan.entity.support.Naming;
import org.springframework.data.annotation.Transient;
@Naming(value = "导航项")
public class NavItem  extends LeafItem {
    @Naming(value = "导航项名")
    @Editable
    private String name;

    @Editable(inputType = InputType.URL)
    @Naming("导航项链接")
    private String link;

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
}
