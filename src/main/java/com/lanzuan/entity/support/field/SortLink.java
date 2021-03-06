package com.lanzuan.entity.support.field;

import com.lanzuan.common.code.Expression;
import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.SortLinkGroup;
import com.lanzuan.common.base.annotation.entity.FormAttributes;
import com.lanzuan.entity.support.Item;
import com.lanzuan.common.base.annotation.entity.Naming;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class SortLink implements Item{
    @Naming(value = "分类名称")
    @FormAttributes
    private String sortName;
    private String maxLink;
    @Naming(value = "封面" ,when = "links" ,expression = Expression.WITHOUT_LENGTH)
    @FormAttributes(inputType = InputType.IMAGE)
    private String image;
    @Naming(value = "封面链接",when = "links" ,expression = Expression.WITHOUT_LENGTH)
    @FormAttributes(inputType = InputType.URL)
    private String imageHref;
    @Naming(value = "链接项列表",ngRepeatVar = "links")
    private List<Link> links;
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
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
        return links;
    }

    @Override
    public SortLinkGroup parent() {
        return parent;
    }
}
