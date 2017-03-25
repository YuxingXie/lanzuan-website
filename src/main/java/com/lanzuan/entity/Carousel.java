package com.lanzuan.entity;

import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.support.ListColumn;
import com.lanzuan.entity.support.Naming;
import com.lanzuan.entity.support.RootItem;
import com.lanzuan.entity.support.field.CarouselItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "carousel")
@Naming(value = "轮播图")
public class Carousel  extends RootItem {
    @Id
    private String id;
    /**
     * 轮播方案名
     */
    @Naming(value="方案名")
    @ListColumn(columnName = "方案名")
    private String name;
    @DBRef
    @ListColumn(columnName = "创建人", fieldOfValue = "name")
    private User creator;
    @ListColumn(columnName = "创建日期")
    private Date date;
    @ListColumn(columnName = "最后修改日期")
    private Date lastModifyDate;
    private String uri;
    /**
     * 相同uri只有一个carousel enabled为true
     * 程序上并不限制其唯一性，但如果查询到多个，则取其中一个
     */
    @ListColumn(columnName = "开启")
    private boolean enabled;
    @DBRef
    @ListColumn(columnName = "最后修改人",fieldOfValue = "name")
    private User lastModifyUser;
    @Naming(value = "轮播项列表",ngRepeatVar = "carouselItem")
    @ListColumn(columnName = "轮播项", fieldOfValue = "value",inputType = InputType.IMAGE)
    private List<CarouselItem> items;
    @Transient
    private PageComponent pageComponent;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PageComponent getPageComponent() {
        return pageComponent;
    }

    public void setPageComponent(PageComponent pageComponent) {
        this.pageComponent = pageComponent;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CarouselItem> getItems() {
        return items;
    }

    public void setItems(List<CarouselItem> items) {
        this.items = items;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public User getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(User lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }



    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
    public List<CarouselItem> children() {
        return items;
    }
}
