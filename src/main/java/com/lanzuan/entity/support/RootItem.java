package com.lanzuan.entity.support;

import com.lanzuan.common.base.annotation.entity.Item;
import com.lanzuan.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/19.
 */
public abstract class RootItem extends AbstractItem{
    public abstract List<String> remarks();
    public Item parent(){
        return null;
    }
    public abstract String projectName();
    public Integer repeatLimit(){ return 1;}
    public abstract String getId();
    public abstract void setLastModifyDate(Date date);
    public abstract void setCreator(User user);
    public abstract void setLastModifyUser(User user);
    public abstract Date getLastModifyDate();
    public abstract User getCreator();
    public abstract User getLastModifyUser();
}
