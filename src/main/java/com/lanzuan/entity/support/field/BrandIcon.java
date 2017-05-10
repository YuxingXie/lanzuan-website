package com.lanzuan.entity.support.field;

import com.lanzuan.common.base.annotation.entity.FormAttributes;
import com.lanzuan.common.base.annotation.entity.Naming;
import com.lanzuan.common.code.InputType;

/**
 * Created by Administrator on 2017/4/27.
 */
public class BrandIcon {
    @Naming("品牌名称")
    @FormAttributes()
    private String brandName;
    @Naming("品牌链接(可能是外部链接)")
    @FormAttributes(inputType = InputType.URL)
    private String brandUri;
    @Naming("品牌图标")
    @FormAttributes(inputType = InputType.IMAGE)
    private String iconUri;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandUri() {
        return brandUri;
    }

    public void setBrandUri(String brandUri) {
        this.brandUri = brandUri;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }
}
