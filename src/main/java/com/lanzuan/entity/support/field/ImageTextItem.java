package com.lanzuan.entity.support.field;

import com.lanzuan.entity.support.Item;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
public class ImageTextItem implements Item {
    private String image;
    private String text;
    private String link ;
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
