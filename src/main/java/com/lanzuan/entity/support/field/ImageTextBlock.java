package com.lanzuan.entity.support.field;

import com.lanzuan.entity.ImageTextBlockGroup;
import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.Naming;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Naming(value = "图文块")
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
