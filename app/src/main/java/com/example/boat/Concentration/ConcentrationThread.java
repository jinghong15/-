package com.example.boat.Concentration;

import android.os.Message;
import android.widget.TextView;

import com.example.boat.BeginActivity;


/**
 * 线程, 计时线程
 */
public class ConcentrationThread implements Runnable {
    private ConcentrationHandler mHandler = new ConcentrationHandler();
    private TextView mTv;

    public ConcentrationThread(TextView tv) {
        this.mTv = tv;
    }

    @Override
    public void run() {
        while (true) {   // 这句话 表明一直执行
            try {
                Thread.sleep(1000);    // 1000 ms
                Message msg = new Message();
                msg.what = BeginActivity.CONCENTRATION;
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

