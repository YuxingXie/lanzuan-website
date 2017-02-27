package com.lanzuan.task;

import com.lanzuan.website.service.impl.UserMeasureService;
import com.lanzuan.website.service.impl.UserPointsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimerTask {
    public TimerTask(int pointsPerDay) {
        this.pointsPerDay = pointsPerDay;
        logger.info("每日红包赠送数量为" + pointsPerDay);
    }

    @Resource
    private UserPointsService userPointsService;
    @Resource
    private UserMeasureService userMeasureService;
//    @Value(value = "${app.yexin.pointsPerDay}")
//    @Value(value = "#{configProperties ['app.yexin.pointsPerDay']}")

    private int pointsPerDay;
    private static Logger logger = LogManager.getLogger();

    public int getPointsPerDay() {
        return pointsPerDay;
    }

    public void setPointsPerDay(int pointsPerDay) {

        this.pointsPerDay = pointsPerDay;
    }

    public void printTimeStamp(){
        Calendar ca= Calendar.getInstance();
        ca.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS ", Locale.CHINA);
        //显示当前时间 精确到毫秒
        logger.info(sdf.format(ca.getTime()));
    }

    public void doTask(){
        logger.info("每日任务开始......");
        printTimeStamp();
        logger.info("开始发放每日红包......");
        userPointsService.addPointsToAllUser(pointsPerDay);
        printTimeStamp();
        logger.info("开始每日结算佣金......");
        userMeasureService.measureSettlementPerDay();
    }
    public static void main(String[] args){
        String longStr="1000*60*60*24*7";

    }
}