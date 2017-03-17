package com.lanzuan.entity.support.field;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
public class ImageTextBlock {
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
}
