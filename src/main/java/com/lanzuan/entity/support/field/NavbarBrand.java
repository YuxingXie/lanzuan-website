package com.lanzuan.entity.support.field;

import com.lanzuan.entity.Navbar;
import com.lanzuan.entity.support.LeafItem;
import org.springframework.data.annotation.Transient;

/**
 * Created by Administrator on 2017/3/6.
 */
public class NavbarBrand extends LeafItem{
    private String type;
    private String value;
    @Transient
    private Navbar parent;

    public Navbar getParent() {
        return parent;
    }

    public void setParent(Navbar parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String naming() {
        return "导航条标签";
    }

    @Override
    public Integer repeatLimit() {
        return 1;
    }

    @Override
    public Navbar parent() {
        return parent;
    }
}
