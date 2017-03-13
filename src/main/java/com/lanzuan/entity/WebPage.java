package com.lanzuan.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


/**
 * 此类对应网站的骨架页面(我把它叫单实例页，有统一url的)，比如首页等，可以是html也可以是jsp，或restful uri，但不可以带参数
 * 如果把一个网站比喻成一座房子，那么首页可以类比成惟一的客厅
 * 因为此网站采用了restful风格，所以uri属性都统一定为restful uri；
 * 对于使用参数的页面，我定义uri为其视图对应的jsp使其具有唯一性
 * 本类用于生成网页模板，并且模板与渲染的数据结合的契入点就是它们有共同的uri
 *
 */
@Document(collection = "webPage")
public class WebPage {
    @Id
    private String id;
    @Field
    private String uri;
    @Field
    private boolean active;
    @DBRef
    private List<PageComponent> pageComponents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<PageComponent> getPageComponents() {
        return pageComponents;
    }

    public void setPageComponents(List<PageComponent> pageComponents) {
        this.pageComponents = pageComponents;
    }
}
