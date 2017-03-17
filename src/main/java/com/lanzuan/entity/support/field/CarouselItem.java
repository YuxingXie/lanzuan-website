package com.lanzuan.entity.support.field;

import com.lanzuan.entity.support.Item;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public class CarouselItem implements Item{
    /**
     * text,link,image
     */
    private String type;
    private String value;
    private CarouselCaption carouselCaption;



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
    public List<Item> childItems() {
        return null;
    }

    @Override
    public Integer repeatLimit() {
        return 20;
    }
}
