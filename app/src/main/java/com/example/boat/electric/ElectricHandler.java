package com.example.boat.electric;


import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boat.BeginActivity;
import com.example.boat.begin.BoatBehavior;

/**
 * 计时器 的消息处理
 */
public class ElectricHandler extends Handler{
    private TextView mTv;
    private int electric;

    ElectricHandler(){

    }

    @Override
    public void handleMessage(Message msg) {

        super.handleMessage(msg);
        if (msg.what == BeginActivity.ELECTRIC) {
            //电量更新
            mTv = (TextView) msg.obj;
            electric = BeginActivity.getBoatBehavior().getBoat().getElectric();
            mTv.setText("电量"+BeginActivity.getBoatBehavior().getBoat().getElectric());

            if(electric < 30) {
                Toast.makeText(BeginActivity.getAc(),"电量低于30%，返回中...", Toast.LENGTH_LONG).show();
                if(BeginActivity.getBack() == false) {
                    BeginActivity.setBack(true);
                    //返回
                    BeginActivity.getBoatBehavior().getBoat().setDirection(BeginActivity.getBoatBehavior().getBoat().getDirection() - 180);
                }
            }
        }
    }
}