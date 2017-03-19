package com.lanzuan.entity;

import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.RootItem;
import com.lanzuan.entity.support.field.NavItem;
import com.lanzuan.entity.support.field.NavbarBrand;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
@Document(collection = "navbar")
public class Navbar extends RootItem{
    @Id
    private String id;
    private String uri;
    private String name;
    private boolean enabled;
    private NavbarBrand navbarBrand;
    private List<NavItem> items;
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
    public String naming() {
        return "导航条";
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
