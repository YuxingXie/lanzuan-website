package com.lanzuan.entity;

import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.LeafItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "article")
public class Article extends LeafItem {
    @Id
    private String id;
    private String title;
    private Date date;
    private String content;
    private int praises;
    private int readTimes;
    private String synopsis;//摘要
    private boolean sticky;//置顶
    /**
     * 可能某些文章不使用文字标题，而可能是一张图片作为封面
     * 如果仅仅为了展示封面图片，此文章可以没有内容、标题等等其它属性
     */
    private String cover;
    private String author;
    private boolean byEditor;//文章是否由系统文本编辑器生成
    @DBRef
    private User uploader;
    private Date lastModifyDate;
    @DBRef
    private User lastModifyUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPraises() {
        return praises;
    }

    public void setPraises(int praises) {
        this.praises = praises;
    }

    public int getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(int readTimes) {
        this.readTimes = readTimes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public User getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(User lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isByEditor() {
        return byEditor;
    }

    public void setByEditor(boolean byEditor) {
        this.byEditor = byEditor;
    }

    @Override
    public Integer repeatLimit() {
        return null;
    }

    @Override
    public Item parent() {
        return null;
    }
}
