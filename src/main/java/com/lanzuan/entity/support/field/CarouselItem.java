package com.lanzuan.entity.support.field;

import com.lanzuan.entity.Carousel;
import com.lanzuan.entity.support.Item;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public class CarouselItem implements Item {
    /**
     * text,link,image
     */
    private String type;
    private String value;
    private CarouselCaption carouselCaption;
    private String link;
    @Transient
    private Carousel parent;
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
    public String naming() {
        return "轮播项";
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
