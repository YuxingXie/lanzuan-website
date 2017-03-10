package com.lanzuan.entity;

import com.lanzuan.entity.entityfield.CarouselCaption;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Administrator on 2017/3/10.
 */
@Document(collection = "carouselItem")
public class CarouselItem {
    @Id
    private String id;
    /**
     * text,link,image
     */
    private String type;
    private String value;
    private CarouselCaption carouselCaption;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
