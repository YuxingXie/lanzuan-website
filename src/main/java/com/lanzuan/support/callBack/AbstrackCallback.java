package com.lanzuan.support.callBack;

/**
 * Created by Administrator on 2016/9/7.
 */
public abstract class AbstrackCallback implements CallBackInterface{
    @Override
    public boolean isSuccess() {
        return getMessage().isSuccess();
    }
}
