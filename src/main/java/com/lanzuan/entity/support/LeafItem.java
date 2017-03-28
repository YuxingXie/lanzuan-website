package com.lanzuan.entity.support;

import com.lanzuan.common.base.annotation.entity.Item;

import java.util.List;

/**
 * Created by Administrator on 2017/3/19.
 */
public abstract class LeafItem extends AbstractItem{

    @Override
    public List<Item> children(){
        return null;
    }


}
