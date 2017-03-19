package com.lanzuan.entity.support;

import java.util.List;

/**
 * Created by Administrator on 2017/3/19.
 */
public abstract class RootItem implements Item{
    public abstract List<String> remarks();
    public Item parent(){
        return null;
    }
    public boolean mustExists(){
        return true;

    }
    public Integer repeatLimit(){ return 1;}
}
