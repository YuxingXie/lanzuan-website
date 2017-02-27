package com.lanzuan.support.vo;

import com.lanzuan.common.code.WrongCodeEnum;

/**
 * Created by Administrator on 2015/12/30.
 */
public class Message {
    private boolean success;
    private String message;
    private Object data;
    private int wrongCode;
    private String locationPath;
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getWrongCode() {
        return wrongCode;
    }

    public void setWrongCode(int wrongCode) {
        this.wrongCode = wrongCode;
    }

    public String getLocationPath() {
        return locationPath;
    }

    public void setLocationPath(String locationPath) {
        this.locationPath = locationPath;
    }

    public static void main(String[] args){
        System.out.println(WrongCodeEnum.NO_PERMISSION.toCode());
        System.out.println(WrongCodeEnum.NOT_LOGIN.toCode());
        System.out.println(WrongCodeEnum.fromCode(1));
        System.out.println(WrongCodeEnum.fromCode(2));
    }
}
