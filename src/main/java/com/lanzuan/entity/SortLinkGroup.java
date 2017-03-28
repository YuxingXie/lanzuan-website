package com.lanzuan.entity;

import com.lanzuan.common.base.annotation.entity.ListColumn;
import com.lanzuan.common.base.annotation.entity.Naming;
import com.lanzuan.entity.support.RootItem;
import com.lanzuan.entity.support.field.SortLink;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
@Naming(value = "分类链接组")
@Document(collection = "sortLinkGroup")
public class SortLinkGroup extends RootItem {
    @Id
    private String id;
    @Naming(value = "方案名")
    @ListColumn(columnName = "方案名")
    private String name;
//    @Naming(value = "创建日期")
    @ListColumn(columnName = "创建日期")
    private Date createDate;
    @ListColumn(columnName = "开启状态")
    private boolean enabled;
    private int indexOfPage;//如果页面有多个同类组件，用此字段分别
    private String uri;

    //约定字段
    @Naming(value = "文章分类列表")
    @ListColumn(columnName = "文章分类列表",fieldOfValue = "sortName")
    private List<SortLink> items;
    @ListColumn(columnName = "最后修改日期")
    private Date lastModifyDate;
    @DBRef
    @ListColumn(columnName = "创建人",fieldOfValue = "name")
    private User creator;
    @DBRef
    @ListColumn(columnName = "最后修改人",fieldOfValue = "name")
    private User lastModifyUser;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    @Override
    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public User getLastModifyUser() {
        return lastModifyUser;
    }

    @Override
    public void setLastModifyUser(User lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<SortLink> getItems() {
        return items;
    }

    public void setItems(List<SortLink> items) {
        this.items = items;
    }

    public int getIndexOfPage() {
        return indexOfPage;
    }

    public void setIndexOfPage(int indexOfPage) {
        this.indexOfPage = indexOfPage;
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
    public List<SortLink> children() {
            return items;
    }
}
