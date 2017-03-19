package com.lanzuan.entity.support;

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
}
