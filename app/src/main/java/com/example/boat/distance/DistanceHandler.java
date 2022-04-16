package com.example.boat.distance;


import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boat.BeginActivity;
import com.example.boat.elude.Elude;

import java.text.DecimalFormat;

/**
 * 计时器 的消息处理
 */
public class DistanceHandler extends Handler{
    private TextView mTv;


    DistanceHandler(){

    }

    @Override
    public void handleMessage(Message msg) {

        super.handleMessage(msg);
        if (msg.what == BeginActivity.DISTANCE) {
            //离岸距离更新
            mTv = (TextView) msg.obj;
            if (BeginActivity.beginOrNot == true && BeginActivity.getBoatBehavior().getBoat().getPosition_x() != null && BeginActivity.getBoatBehavior().getBoat().getLongitude() != null) {
                BeginActivity.getBoatBehavior().getBoat().setDistance(Distance_Calabrate());
                mTv.setText("离岸距离：" + BeginActivity.getBoatBehavior().getBoat().getDistance() + "m");
            }
            if(BeginActivity.getBoatBehavior().getBoat().getDistance() != null && BeginActivity.getBoatBehavior().getBoat().getDistance() >= 20.0) {
                Toast.makeText(BeginActivity.getAc(),"离岸距离超过20m，行进方向改变", Toast.LENGTH_LONG).show();
                Elude.Adjustment();
            }
        }
    }

    //离岸距离
    private static Double Distance_Calabrate() {
        Double C_angle=0.0;
        Double Distance=0.0;
        double Mlon_A=BeginActivity.getBoatBehavior().getBoat().getPosition_x();
        double Mlat_A=BeginActivity.getBoatBehavior().getBoat().getPosition_y();
        double Mlon_B=BeginActivity.getBoatBehavior().getBoat().getLongitude();
        double Mlat_B=BeginActivity.getBoatBehavior().getBoat().getLatitude();
        double Earth_R=234234;
        C_angle=Math.sin((90-Mlat_A)/180*3.1415926)*Math.sin((90-Mlat_B)/180*3.1415926)*Math.cos((Mlon_A-Mlon_B)/180*3.1415926)+Math.cos((90-Mlat_A)/180*3.1415926)*Math.cos((90-Mlat_B)/180*3.1415926);
        Distance=Earth_R*Math.acos(C_angle);
        DecimalFormat df = new DecimalFormat("#.###");
        Distance *= 20;
        return Double.parseDouble(df.format(Distance));
    }
}