package com.lanzuan.entity;

import com.lanzuan.entity.entityfield.ImageCardGroupItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
@Document(collection = "carouselItem")
public class ImageCardGroup {
    @Id
    private String id;
    private String name;
    private String uri;
    private List<ImageCardGroupItem> imageCardGroupItems;
    private boolean enabled;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<ImageCardGroupItem> getImageCardGroupItems() {
        return imageCardGroupItems;
    }

    public void setImageCardGroupItems(List<ImageCardGroupItem> imageCardGroupItems) {
        this.imageCardGroupItems = imageCardGroupItems;
    }
}
