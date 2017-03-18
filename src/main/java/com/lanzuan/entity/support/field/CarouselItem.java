package com.lanzuan.entity.support.field;

import com.lanzuan.entity.support.Item;

import java.util.ArrayList;
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
    private String link;

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
    public String name() {
        return carouselCaption==null?null:carouselCaption.getText();
    }

    @Override
    public String text() {
        return carouselCaption==null?null:carouselCaption.getText();
    }

    @Override
    public String image() {
        return value;
    }

    @Override
    public String href() {
        return link==null?(carouselCaption==null?null:carouselCaption.getLink()):link;
    }

    @Override
    public String title() {
        return text();
    }

    @Override
    public List<CarouselCaption> childItems() {
        if (carouselCaption!=null){
            List<CarouselCaption> items=new ArrayList<CarouselCaption>();
            items.add(carouselCaption);
            return items;
        }
        return null;
    }

    @Override
    public Integer repeatLimit() {
        return 20;
    }
}
