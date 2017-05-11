package com.lanzuan.entity.support.field;

import com.lanzuan.common.base.annotation.entity.FormAttributes;
import com.lanzuan.common.base.annotation.entity.Naming;
import com.lanzuan.common.code.Expression;
import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.Article;
import com.lanzuan.entity.SortLinkGroup;
import com.lanzuan.entity.support.Item;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class SortArticle implements Item{
    @Naming(value = "分类名称")
    @FormAttributes
    private String sortName;
    private String maxLink;
    @Naming(value = "封面" ,when = "articles" ,expression = Expression.WITHOUT_LENGTH)
    @FormAttributes(inputType = InputType.IMAGE)
    private String image;
    @Naming(value = "封面链接",when = "articles" ,expression = Expression.WITHOUT_LENGTH)
    @FormAttributes(inputType = InputType.URL)
    private String imageHref;
    @DBRef
    private List<Article> articles;
    @Transient
    private SortLinkGroup parent;

    public SortLinkGroup getParent() {
        return parent;
    }

    public void setParent(SortLinkGroup parent) {
        this.parent = parent;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getMaxLink() {
        return maxLink;
    }

    public void setMaxLink(String maxLink) {
        this.maxLink = maxLink;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }


    @Override
    public Integer repeatLimit() {
        return 20;
    }

    @Override
    public List<? extends Item> children() {
        return articles;
    }

    @Override
    public SortLinkGroup parent() {
        return parent;
    }
}
