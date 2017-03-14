package com.lanzuan.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
/*
db.articleSection.update({},{"$set":{"enabled":true}},false,true)
db.articleSection.update({"name":"活动专题"},{"$set":{"enabled":true}},false,true)
db.articleSection.update({"name":"新闻动态"},{"$set":{"articles":null}},false,true)
db.articleSection.update({"name":"企业文化"},{"$set":{"articles":null}},false,true)
 */
@Document(collection = "articleSection")
public class ArticleSection {
    @Id
    private String id;
    private String name;
    private Date createDate;
    private boolean enabled;
    /**
     *     当图片也要放入文章版块的位置，直接读取image属性即可
     *     此属性和文章列表属性articles并不互相排斥，可以图文共存
     *     文章封面是文章的属性，而该图片是文章块的属性，虽然表现上可能类似
     */
    private String image;
    @DBRef
    private List<Article> articles;
    @DBRef
    private User creator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
