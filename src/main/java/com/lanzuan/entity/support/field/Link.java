package com.lanzuan.entity.support.field;

import com.lanzuan.entity.support.Item;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class Link implements Item{
    private String href;
    private String text;
    private String date;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String name() {
        return text;
    }

    @Override
    public String text() {
        return text;
    }

    @Override
    public String image() {
        return image;
    }

    @Override
    public String href() {
        return href;
    }

    @Override
    public String title() {
        return text();
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
