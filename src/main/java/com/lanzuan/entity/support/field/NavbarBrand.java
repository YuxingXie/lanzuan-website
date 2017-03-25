package com.lanzuan.entity.support.field;

import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.Navbar;
import com.lanzuan.entity.support.Editable;
import com.lanzuan.entity.support.LeafItem;
import com.lanzuan.entity.support.Naming;
import org.springframework.data.annotation.Transient;

/**
 * Created by Administrator on 2017/3/6.
 */
@Naming(value = "导航条标签")
public class NavbarBrand extends LeafItem{
//    @Naming("类型")
//    @Editable(inputType = InputType.SELECT,optionValues = {"image","text"})
    private String type;

    @Naming("值")
    @Editable(inputType = InputType.IMAGE)
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
    public Integer repeatLimit() {
        return 1;
    }

    @Override
    public Navbar parent() {
        return parent;
    }
}
