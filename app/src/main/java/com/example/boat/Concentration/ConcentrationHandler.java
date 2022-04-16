package com.example.boat.Concentration;


import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boat.BeginActivity;
import com.example.boat.elude.Elude;

/**
 * 计时器 的消息处理
 */
public class ConcentrationHandler extends Handler{
    private TextView mTv;


    ConcentrationHandler(){

    }

    @Override
    public void handleMessage(Message msg) {

        super.handleMessage(msg);
        if (msg.what == BeginActivity.CONCENTRATION) {
            //石油浓度更新
            mTv = (TextView) msg.obj;
            mTv.setText("石油浓度：" + BeginActivity.getBoatBehavior().getBoat().getConcentration() + "mg/L");
            //判断电量剩余
            if(BeginActivity.getBack() == false) {
                //判断石油浓度
                if(BeginActivity.getBoatBehavior().getBoat().getConcentration() < 0.1) {
                    //方向改变
                    Elude.Adjustment();
                    Toast.makeText(BeginActivity.getAc(),"石油浓度小于0.1mg/l，方向改变", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}