package com.example.boat.elude;

import android.os.Message;
import android.widget.TextView;

import com.example.boat.BeginActivity;
import com.example.boat.time.TimeHandler;


/**
 * 线程, 计时线程
 */
public class EludeThread implements Runnable {
    private EludeHandler mHandler = new EludeHandler();
    private TextView mTv;

    public EludeThread(TextView tv) {
        this.mTv = tv;
    }

    @Override
    public void run() {
        while (true) {   // 这句话 表明一直执行
            try {
                Thread.sleep(100);    // 每100 ms检测一次碰撞
                Message msg = new Message();
                msg.what = BeginActivity.ELUDE;
                msg.obj = mTv;
                mHandler.sendMessage(msg);
                System.out.println("send...");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("thread error...");
            }

        }
    }
}

