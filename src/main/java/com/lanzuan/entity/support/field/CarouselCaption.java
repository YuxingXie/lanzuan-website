package com.lanzuan.entity.support.field;

import com.lanzuan.common.code.InputType;
import com.lanzuan.entity.support.Editable;
import com.lanzuan.entity.support.LeafItem;
import com.lanzuan.entity.support.Naming;
import org.springframework.data.annotation.Transient;


public class CarouselCaption extends LeafItem {
    //暂时仅{link，text}，应该弄个枚举的
    @Naming(value = "标题类型")
    @Editable(inputType = InputType.SELECT,optionValues ={"{value:\"link\",text:\"链接\"}","{value:\"text\",text:\"文字\"}"} )
    private String type;
    /**
     * 如果type为link，则需要value表示链接地址，text表示链接文字，如果type为text,则没有value只有text
     */
    @Naming("标题链接")
    @Editable
    private String  value;
    @Naming("标题文字")
    @Editable()
    private String text;
    /**
     * 标题css样式名
     */
    private String captionClass;
    @Naming(value = "链接")
    @Editable
    private String link;
    @Transient
    private CarouselItem parent;

    public CarouselItem getParent() {
        return parent;
    }

    public void setParent(CarouselItem parent) {
        this.parent = parent;
    }

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCaptionClass() {
        return captionClass;
    }

    public void setCaptionClass(String captionClass) {
        this.captionClass = captionClass;
    }



    @Override
    public Integer repeatLimit() {
        return 1;
    }

    @Override
    public CarouselItem parent() {
        return parent;
    }
}
