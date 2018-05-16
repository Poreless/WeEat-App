package com.client.weeat.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Date;

public class ShowTiemTextView extends TextView implements Runnable {
    // 时间变量
    private static final long msInDay = 1000*60*60*24;
    private static final long msInHour = 1000*60*60;
    private static final long msInMin = 1000*60;
    private static final long msIns = 1000;
    private long ms;
    private int i;
    private String actname;
    private Date thedate;
    // 当前计时器是否运行  
    private boolean isRun = false;

    public ShowTiemTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ShowTiemTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowTiemTextView(Context context) {
        super(context);
    }


    public void setTimes(String actname,Date thedate) {
        //将毫秒数转化为时间  
 /*       this.second = (int) (time / 1000) % 60;
        this.minute = (int) (time / (60 * 1000) % 60);
        this.hour = (int) (time / (60 * 60 * 1000) % 24);
        this.day = (int) (time / (24 * 60 * 60 * 1000));*/
        this.actname = actname;
        this.thedate = thedate;
    }


    /**
     * 显示当前时间 
     *
     * @return
     */
    public String showTime() {

        String time = new String();
        if(i > 0) {
            time = "活动:#" + actname + "#已经进行了:" +
                    ms / msInDay + "天" + (ms % msInDay) / msInHour +
                    "小时" + (ms % msInHour) / msInMin + "分钟"
            +(ms % msInMin) / msIns+"秒";
        }else{
            time  = "距离:#"+actname+"#开始还有:"+
                    ms/msInDay + "天" + (ms%msInDay)/msInHour +
                    "小时"+(ms%msInHour)/msInMin+"分钟"
                    +(ms % msInMin) / msIns+"秒";
        }
        return time.toString();
    }

    /**
     * 实时计时
     */
    private void countdown() {
        Date now = new Date();    //获取现在的日期对象
        this.i = now.compareTo(thedate);
        this.ms = Math.abs(now.getTime() - thedate.getTime());
    }

    public boolean isRun() {
        return isRun;
    }

    /**
     * 开始计时 
     */
    public void start() {
        isRun = true;
        run();
    }

    /**
     * 结束计时 
     */
    public void stop() {
        isRun = false;
    }

    /**
     * 实现计时循环 
     */
    @Override
    public void run() {
        if (isRun) {
            // Log.d(TAG, "Run");  
            countdown();
            this.setText(showTime());
            postDelayed(this, 1000);
        } else {
            removeCallbacks(this);
        }
    }

}  