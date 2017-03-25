package com.lanzuan.entity.support.field;

import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.support.Editable;
import com.lanzuan.entity.support.LeafItem;
import com.lanzuan.entity.support.Naming;
import org.springframework.data.annotation.Transient;

/**
 * Created by Administrator on 2017/3/15.
 */
public class Link extends LeafItem {
    @Naming("链接")
    @Editable(inputType = InputType.URL)
    private String href;
    @Naming("链接文字")
    @Editable
    private String text;
    @Naming("日期")
    @Editable(inputType = InputType.DATE)
    private String date;
//    @Naming("图片链接")
//    @Editable(inputType = InputType.IMAGE)
    private String image;
    @Transient
    private SortLink parent;

    public SortLink getParent() {
        return parent;
    }

    public void setParent(SortLink parent) {
        this.parent = parent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public Integer repeatLimit() {
        return 20;
    }

    @Override
    public SortLink parent() {
        return parent;
    }
}
