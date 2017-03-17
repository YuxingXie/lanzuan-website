package com.lanzuan.entity.support.field;

import com.lanzuan.entity.support.Item;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */
public class NavbarBrand implements Item{
    private String type;
    private String value;

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
    public List<? extends Item> childItems() {
        return null;
    }

    @Override
    public Integer repeatLimit() {
        return 1;
    }
}
