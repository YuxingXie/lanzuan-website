package com.lanzuan.entity.entityfield;

import com.lanzuan.entity.entityfield.CarouselCaption;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Administrator on 2017/3/10.
 */
public class CarouselItem {
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
}
