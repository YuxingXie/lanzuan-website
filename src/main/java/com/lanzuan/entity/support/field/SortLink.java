package com.lanzuan.entity.support.field;

import com.lanzuan.entity.support.Item;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class SortLink implements Item{
    private String sortName;
    private String maxLink;
    private String image;
    private String imageHref;
    private List<Link> links;

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
    public String name() {
        return name();
    }

    @Override
    public String text() {
        return name();
    }

    @Override
    public String image() {
        return image;
    }

    @Override
    public String href() {
        return imageHref;
    }

    @Override
    public String title() {
        return text();
    }

    @Override
    public List<? extends Item> childItems() {
        return links;
    }

    @Override
    public Integer repeatLimit() {
        return 20;
    }
}
