package com.lanzuan.entity;

import com.lanzuan.entity.entityfield.ImageTextBlock;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
@Document(collection = "imageTextBlockGroup")
public class ImageTextBlockGroup {
    @Id
    private String id;
    private String uri;
    private String name;
    private boolean enabled;
    private String text;

    public List<ImageTextBlock> getImageTextBlocks() {
        return imageTextBlocks;
    }

    public void setImageTextBlocks(List<ImageTextBlock> imageTextBlocks) {
        this.imageTextBlocks = imageTextBlocks;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private List<ImageTextBlock> imageTextBlocks;

}
