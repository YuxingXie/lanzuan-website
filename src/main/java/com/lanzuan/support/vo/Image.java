package com.lanzuan.support.vo;

import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.support.Editable;
import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.Naming;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */
public class Image implements Item {
    @Naming(value = "图片路径")
    @Editable(inputType = InputType.IMAGE)
    private String uri;
    private String name;
    private String suffix;
    private String caption;
    private String text;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Integer repeatLimit() {
        return null;
    }

    @Override
    public List<? extends Item> children() {
        return null;
    }

    @Override
    public Item parent() {
        return null;
    }
}
