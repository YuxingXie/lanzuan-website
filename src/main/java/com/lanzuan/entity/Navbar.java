package com.lanzuan.entity;

import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.support.Item;
import com.lanzuan.common.base.annotation.entity.ListColumn;
import com.lanzuan.common.base.annotation.entity.Naming;
import com.lanzuan.entity.support.RootItem;
import com.lanzuan.entity.support.field.NavItem;
import com.lanzuan.entity.support.field.NavbarBrand;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
@Document(collection = "navbar")
@Naming(value = "导航条")
public class Navbar extends RootItem{
    @Id
    private String id;
//    @Naming(value = "导航路径")
//    @Editable()
    private String uri;
    @Naming(value = "方案名")
    @ListColumn(columnName = "方案名")
    private String name;
//    @Naming(value = "开启状态")
//    @Editable(inputType = InputType.TOGGLE_BOOLEAN)
    @ListColumn(columnName = "开启状态")
    private boolean enabled;

    private Date lastModifyDate;
    @DBRef
    private User creator;
    @DBRef
    private User lastModifyUser;

    @Naming(value = "导航标签")
    @ListColumn(columnName = "导航标签",fieldOfValue = "value",inputType = InputType.IMAGE)
    private NavbarBrand navbarBrand;
    @Naming(value = "导航项",ngRepeatVar = "navItem")
    @ListColumn(columnName = "导航项",fieldOfValue = "name")
    private List<NavItem> items;
    @DBRef
    private PageComponent pageComponent;

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    @Override
    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public User getCreator() {
        return creator;
    }

    @Override
    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getLastModifyUser() {
        return lastModifyUser;
    }

    @Override
    public void setLastModifyUser(User lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

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

    public NavbarBrand getNavbarBrand() {
        return navbarBrand;
    }

    public void setNavbarBrand(NavbarBrand navbarBrand) {
        this.navbarBrand = navbarBrand;
    }

    public List<NavItem> getItems() {
        return items;
    }

    public void setItems(List<NavItem> items) {
        this.items = items;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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
        List<Item> itemList=new ArrayList<Item>();
        if (navbarBrand!=null)
            itemList.add(navbarBrand);
        if (items!=null){
            for(Item item:items){
                itemList.add(item);
            }
        }
        if (itemList.size()==0)
            return null;
        return itemList;
    }
}
