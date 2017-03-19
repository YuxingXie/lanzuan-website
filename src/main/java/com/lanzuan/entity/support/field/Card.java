package com.lanzuan.entity.support.field;

import com.lanzuan.entity.CardGroup;
import com.lanzuan.entity.support.LeafItem;
import org.springframework.data.annotation.Transient;

public class Card extends LeafItem {
    private String image;
    private String text;
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
    public String naming() {
        return "卡片";
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
