package com.example.boat.distance;

import android.os.Message;
import android.widget.TextView;

import com.example.boat.BeginActivity;


/**
 * 线程, 计时线程
 */
public class DistanceThread implements Runnable {
    private DistanceHandler mHandler = new DistanceHandler();
    private TextView mTv;

    public DistanceThread(TextView tv) {
        this.mTv = tv;
    }

    @Override
    public void run() {
        while (true) {   // 这句话 表明一直执行
            try {
                Thread.sleep(1000);    // 1000 ms
                Message msg = new Message();
                msg.what = BeginActivity.DISTANCE;
                msg.obj = mTv;
                mHandler.sendMessage(msg);
                System.out.println("send...concentration");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("thread error...");
            }

        }
    }
}

