package com.lanzuan.entity.support.field;

import com.lanzuan.entity.ImageTextBlockGroup;
import com.lanzuan.entity.support.Item;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
public class ImageTextBlock implements Item {
    private String name;
    private List<ImageTextItem> imageTextItems;
    @Transient
    private ImageTextBlockGroup parent;

    public ImageTextBlockGroup getParent() {
        return parent;
    }

    public void setParent(ImageTextBlockGroup parent) {
        this.parent = parent;
    }

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
    public String naming() {
        return "图文块";
    }

    @Override
    public Integer repeatLimit() {
        return null;
    }

    @Override
    public List<? extends Item> children() {
        return imageTextItems;
    }

    @Override
    public ImageTextBlockGroup parent() {
        return parent;
    }
}
