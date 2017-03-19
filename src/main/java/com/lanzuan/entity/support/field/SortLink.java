package com.lanzuan.entity.support.field;

import com.lanzuan.entity.SortLinkGroup;
import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.Naming;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
@Naming(value="链接分类")
public class SortLink implements Item{
    private String sortName;
    private String maxLink;
    private String image;
    private String imageHref;
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
