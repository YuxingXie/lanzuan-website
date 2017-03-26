package com.lanzuan.entity.support.field;

import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.CardGroup;
import com.lanzuan.entity.User;
import com.lanzuan.entity.support.Editable;
import com.lanzuan.entity.support.LeafItem;
import com.lanzuan.entity.support.Naming;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Naming(value = "卡片")
public class Card extends LeafItem {
    @Naming(value = "图片")
    @Editable(inputType = InputType.IMAGE)
    private String image;
    @Naming(value = "文字")
    @Editable()
    private String text;
    @Editable(inputType = InputType.URL)
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
