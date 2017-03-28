package com.lanzuan.entity.support.field;

import com.lanzuan.common.code.InputType;
import com.lanzuan.common.base.annotation.entity.FormAttributes;
import com.lanzuan.entity.support.LeafItem;
import com.lanzuan.common.base.annotation.entity.Naming;
import org.springframework.data.annotation.Transient;
@Naming(value = "图文块项")
public class ImageTextItem extends LeafItem{
    @Naming(value = "图片")
    @FormAttributes(inputType = InputType.IMAGE)
    private String image;
    @Naming(value = "图片文字")
    @FormAttributes()
    private String text;
    @Naming(value = "链接")
    @FormAttributes(inputType = InputType.URL)
    private String link ;
    @Naming("标题")
    @FormAttributes()
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
    public Integer repeatLimit() {
        return null;
    }

    @Override
    public ImageTextBlock parent() {
        return parent;
    }
}
