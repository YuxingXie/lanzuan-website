package com.lanzuan.common.bean;

import com.lanzuan.website.service.impl.StartOnLoadService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by Administrator on 2017/3/6.
 */
public class DBQueryPostProcessor implements BeanPostProcessor {

    public Object postProcessAfterInitialization(Object obj, String arg1)
            throws BeansException {
        if(obj instanceof StartOnLoadService) {
            ((StartOnLoadService)obj).loadData(); //调用方法加载数据
        }
        return obj;
    }

    public Object postProcessBeforeInitialization(Object arg0, String arg1)
            throws BeansException {
        return arg0;
    }

}
