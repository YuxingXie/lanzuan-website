package com.lanzuan.entity.support;

import java.util.List;

/**
 * Created by Administrator on 2017/3/19.
 */
public abstract class LeafItem implements Item{

    @Override
    public List<Item> children(){
        return null;
    }

}
