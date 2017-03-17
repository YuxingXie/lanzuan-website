package com.lanzuan.entity.support.field;

import com.lanzuan.entity.support.Item;

import java.util.List;

public class Card implements Item{
    private String image;
    private String text;
    private String link;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String name() {
        return name();
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
        return link;
    }

    @Override
    public String title() {
        return text();
    }

    @Override
    public List<Item> childItems() {
        return null;
    }

    @Override
    public Integer repeatLimit() {
        return null;
    }
}
