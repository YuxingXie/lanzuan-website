package com.lanzuan.entity;

import com.lanzuan.entity.ImageTextBlockGroup;
import com.lanzuan.common.base.annotation.entity.FormAttributes;
import com.lanzuan.entity.support.Item;
import com.lanzuan.common.base.annotation.entity.Naming;
import com.lanzuan.entity.support.field.ImageTextItem;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Naming(value = "图文块")
@Document(collection = "imageTextBlock")
public class ImageTextBlock implements Item {
    @Naming(value = "块标题")
    @FormAttributes()
    private String name;
    @Naming(ngRepeatVar = "imageTextItem",value = "图文项")
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
