package com.lanzuan.entity.support;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
public interface Item {
    String name();
    String text();
    String image();
    String href();
    String title();
    List<? extends Item> childItems();
    Integer repeatLimit();
}
