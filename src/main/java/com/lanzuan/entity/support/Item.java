package com.lanzuan.entity.support;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
public interface Item {

    Integer repeatLimit();
    List<? extends Item> children();
    Item parent();




}
