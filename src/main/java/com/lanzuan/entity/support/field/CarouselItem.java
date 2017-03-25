package com.lanzuan.entity.support.field;

import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.Carousel;
import com.lanzuan.entity.support.Editable;
import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.Naming;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Naming(value = "轮播项")
public class CarouselItem implements Item {
    /**
     * text,link,image
     */

    private String type;
    @Naming("图片路径")
    @Editable(inputType = InputType.IMAGE)
    private String value;

    @Naming("图片链接")
    @Editable(inputType = InputType.URL)
    private String imageLink;
    @Naming(value = "轮播项标题")
    private CarouselCaption carouselCaption;
    @Transient
    private Carousel parent;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Carousel getParent() {
        return parent;
    }

    public void setParent(Carousel parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CarouselCaption getCarouselCaption() {
        return carouselCaption;
    }

    public void setCarouselCaption(CarouselCaption carouselCaption) {
        this.carouselCaption = carouselCaption;
    }



    @Override
    public Integer repeatLimit() {
        return null;
    }

    @Override
    public List<? extends Item> children() {
        if (carouselCaption==null) return null;
        List<CarouselCaption> list=new ArrayList<CarouselCaption>();
        list.add(carouselCaption);
        return list;
    }

    @Override
    public Carousel parent() {
        return parent;
    }
}
