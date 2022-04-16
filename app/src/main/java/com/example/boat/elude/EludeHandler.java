package com.example.boat.elude;


import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.boat.BeginActivity;

import android.widget.Toast;
/**
 * 计时器 的消息处理
 */
public class EludeHandler extends Handler{
    private final static int ANGLE = 30;
    private TextView mTv;

    private int direction;

    EludeHandler(){

    }

    @Override
    public void handleMessage(Message msg) {

        super.handleMessage(msg);
        if (msg.what == BeginActivity.ELUDE) {
            //判断是否有障碍物
            if(BeginActivity.getBoatBehavior().getElude().isReturn()) {
                Toast.makeText(BeginActivity.getAc(),"遇到障碍物",Toast.LENGTH_LONG).show();
            }
            mTv = (TextView) msg.obj;
            direction = BeginActivity.getBoatBehavior().getBoat().getDirection();

            if(direction == 0 || direction == 360) {
                mTv.setText("方向：正北");
            } else if(direction < 90) {
                mTv.setText("方向：由北向东" + direction + "°");
            } else if(direction == 90) {
                mTv.setText("方向：正东");
            }else if(direction > 90 && direction < 180) {
                direction -= 90;
                mTv.setText("方向：由东向南" + direction + "°");
            } else if(direction == 180) {
                mTv.setText("方向：正南");
            }else if(direction > 180 && direction < 270) {
                direction -= 180;
                mTv.setText("方向：由南向西" + direction + "°");
            } else if(direction == 270) {
                mTv.setText("方向：正西");
            }else if(direction > 270 && direction < 360) {
                direction -= 270;
                mTv.setText("方向：由西向北" + direction + "°");
            }
        }
    }
}