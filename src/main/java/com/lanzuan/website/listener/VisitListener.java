package com.lanzuan.website.listener;

import com.lanzuan.common.constant.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Administrator on 2015/7/29.
 */
public class VisitListener implements HttpSessionListener{
    private static Logger logger = LogManager.getLogger();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        Constant.totalVisit++;
        System.out.println("session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println(httpSessionEvent.getSource());
        System.out.println("session destroyed");
    }
}
