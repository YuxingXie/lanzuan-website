package com.lanzuan.entity.support.field;

import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.CardGroup;
import com.lanzuan.common.base.annotation.entity.FormAttributes;
import com.lanzuan.entity.support.LeafItem;
import com.lanzuan.common.base.annotation.entity.Naming;
import org.springframework.data.annotation.Transient;

@Naming(value = "卡片")
public class Card extends LeafItem {
    @Naming(value = "图片")
    @FormAttributes(inputType = InputType.IMAGE)
    private String image;
    @Naming(value = "文字")
    @FormAttributes()
    private String text;
    @FormAttributes(inputType = InputType.URL)
    @Naming("链接")
    private String link;

    @Transient
    private CardGroup parent;

    public CardGroup getParent() {
        return parent;
    }

    public void setParent(CardGroup parent) {
        this.parent = parent;
    }

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
    public Integer repeatLimit() {
        return 20;
    }

    @Override
    public CardGroup parent() {
        return parent;
    }


}
