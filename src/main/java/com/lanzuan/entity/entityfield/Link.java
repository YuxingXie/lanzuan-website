package com.lanzuan.entity.entityfield;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/15.
 */
public class Link {
    private String href;
    private String text;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
