package com.example.boat.time;


import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.boat.BeginActivity;

/**
 * 计时器 的消息处理
 */
public class TimeHandler extends Handler{
    private long i = 0;
    private TextView mTv;
    private long second = 0;
    TimeHandler(){

    }

    @Override
    public void handleMessage(Message msg) {

        super.handleMessage(msg);
        if (msg.what == BeginActivity.TIMER) {
            second ++ ;
            mTv = (TextView) msg.obj;
            mTv.setText("运行时间:"+changeTimerFormat(++i));
        }
    }

    /**
     * 转化成显示的格式
     * @param paramLong
     * @return 待显示的字符串
     */
    private String changeTimerFormat(long paramLong)
    {
        long day = paramLong / 86400;
        long hour = paramLong % 86400 / 3600;
        long minute = paramLong % 3600 / 60;
        long second = paramLong % 60;

        // 天数
        String str1 = String.valueOf(day);
        if (day == 0) {
            str1 = "";
        }else{
            str1 = str1 + "天";
        }
        // 小时数
        String str2 = String.valueOf(hour);
        if (hour == 0) {
            str2 = "";
        }else if (hour < 10) {
            str2 = "0" + str2 + ":";
        }else {
            str2 = str2 + ":";
        }
        // 分钟数
        String str3 = String.valueOf(minute);
        if (minute == 0) {
            str3 = "00" + ":";
        }else if (minute < 10) {
            str3 = "0" + str3 + ":";
        }else {
            str3 = str3 + ":";
        }
        // 秒钟数
        String str4 = String.valueOf(second);
        if (second == 0) {
            str4 = "";
        }else if (second < 10) {
            str4 = "0" + str4;
        }

        return str1 + str2 + str3 + str4;
    }
}