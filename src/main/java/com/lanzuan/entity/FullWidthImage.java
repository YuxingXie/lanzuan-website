package com.lanzuan.entity;

import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.Naming;
import com.lanzuan.entity.support.RootItem;
import com.lanzuan.support.vo.Image;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Naming(value = "全屏大图")
@Document(collection = "fullWidthImage")
public class FullWidthImage extends RootItem{
    @Id
    private String id;
    @Naming("图片信息")
    private Image image;
    private String name;
    private boolean enabled;
    private String uri;
    @DBRef
    private PageComponent pageComponent;

    public PageComponent getPageComponent() {
        return pageComponent;
    }

    public void setPageComponent(PageComponent pageComponent) {
        this.pageComponent = pageComponent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public List<String> remarks() {
        return null;
    }

    @Override
    public String projectName() {
        return name;
    }


    @Override
    public List<? extends Item> children() {
        return null;
    }
}
