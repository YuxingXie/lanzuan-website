package com.lanzuan.entity.support.field;

import com.lanzuan.entity.support.LeafItem;
import org.springframework.data.annotation.Transient;

/**
 * Created by Administrator on 2017/3/12.
 */
public class ImageTextItem extends LeafItem{
    private String image;
    private String text;
    private String link ;
    private String title;
    @Transient
    private ImageTextBlock parent;
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

    public ImageTextBlock getParent() {
        return parent;
    }

    public void setParent(ImageTextBlock parent) {
        this.parent = parent;
    }

    @Override
    public String naming() {
        return "图文块项";
    }

    @Override
    public Integer repeatLimit() {
        return null;
    }

    @Override
    public ImageTextBlock parent() {
        return parent;
    }
}
