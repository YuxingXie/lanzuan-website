package com.lanzuan.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "article")
public class Article {
    @Id
    private String id;
    private String title;
    private Date date;
    private String content;
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
    @Transient
    private List<ArticleSection> articleSections;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<ArticleSection> getArticleSections() {
        return articleSections;
    }

    public void setArticleSections(List<ArticleSection> articleSections) {
        this.articleSections = articleSections;
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
}
