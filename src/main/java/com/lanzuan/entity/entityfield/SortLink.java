package com.lanzuan.entity.entityfield;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class SortLink {
    private String sortName;
    private String maxLink;
    private String image;
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
}
