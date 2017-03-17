package com.lanzuan.entity.support.field;

import com.lanzuan.entity.support.Item;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
public class ImageTextBlock implements Item{
    private String name;
    private List<ImageTextItem> imageTextItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageTextItem> getImageTextItems() {
        return imageTextItems;
    }

    public void setImageTextItems(List<ImageTextItem> imageTextItems) {
        this.imageTextItems = imageTextItems;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String text() {
        return name();
    }

    @Override
    public String image() {
        return null;
    }

    @Override
    public String href() {
        return null;
    }

    @Override
    public String title() {
        return name();
    }

    @Override
    public List<ImageTextItem> childItems() {
        return imageTextItems;
    }

    @Override
    public Integer repeatLimit() {
        return null;
    }
}
