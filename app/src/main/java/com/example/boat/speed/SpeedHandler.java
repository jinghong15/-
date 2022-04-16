package com.example.boat.speed;


import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boat.BeginActivity;
import com.example.boat.begin.BoatBehavior;

/**
 * 计时器 的消息处理
 */
public class SpeedHandler extends Handler{
    private TextView mTv;

    private Double speed;
    private Double concentration;

    SpeedHandler(){

    }

    @Override
    public void handleMessage(Message msg) {

        super.handleMessage(msg);
        if (msg.what == BeginActivity.SPEED) {
            //速度更新
            mTv = (TextView) msg.obj;
            concentration = BeginActivity.getBoatBehavior().getBoat().getConcentration();
            speed = BeginActivity.getBoatBehavior().getBoat().getSpeed();
            //石油浓度和小船移动速度成反比
            if(concentration < 50) {
                if(speed != BoatBehavior.v5) {
                    Toast.makeText(BeginActivity.getAc(),"石油浓度小于50mg/L，速度调整", Toast.LENGTH_LONG).show();
                    BeginActivity.getBoatBehavior().getBoat().setSpeed(BoatBehavior.v5);
                }
            } else if(concentration < 100) {
                if(speed != BoatBehavior.v4) {
                    Toast.makeText(BeginActivity.getAc(),"石油浓度大于50mg/L小于100mg/L，速度调整", Toast.LENGTH_LONG).show();
                    BeginActivity.getBoatBehavior().getBoat().setSpeed(BoatBehavior.v4);
                }
            } else if(concentration < 150) {
                if(speed != BoatBehavior.v3) {
                    Toast.makeText(BeginActivity.getAc(),"石油浓度大于100mg/L小于150mg/L，速度调整", Toast.LENGTH_LONG).show();
                    BeginActivity.getBoatBehavior().getBoat().setSpeed(BoatBehavior.v3);
                }
            } else if(concentration < 200) {
                if(speed != BoatBehavior.v2) {
                    Toast.makeText(BeginActivity.getAc(),"石油浓度大于150mg/L小于200mg/L，速度调整", Toast.LENGTH_LONG).show();
                    BeginActivity.getBoatBehavior().getBoat().setSpeed(BoatBehavior.v2);
                }
            } else if(concentration >= 200) {
                if(speed != BoatBehavior.v1) {
                    Toast.makeText(BeginActivity.getAc(),"石油浓度大于200mg/L，速度调整", Toast.LENGTH_LONG).show();
                    BeginActivity.getBoatBehavior().getBoat().setSpeed(BoatBehavior.v1);
                }
            }

            mTv.setText("速度："+BeginActivity.getBoatBehavior().getBoat().getSpeed() + "m/s");
        }
    }
}