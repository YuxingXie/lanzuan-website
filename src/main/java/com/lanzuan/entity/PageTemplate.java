package com.lanzuan.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Administrator on 2017/3/4.
 */
@Document()
public class PageTemplate {
    @Id
    private String id;

}
