package com.example.boat.elude;

import com.example.boat.BeginActivity;

public class Elude {
    private static boolean flag;
    //遇到障碍物，执行逆时针偏移60°
    final static int ANGLE = 60;
    //方向
    private static int direction;
    public Elude(int d) {
        flag = false;
        direction = d;
    }
    public static boolean getFlag() {
        return flag;
    }
    public static void setFlag(boolean e) {
        flag = e;
    }

    //flag变量记录遇障情况
    public boolean isReturn() {
        if(flag == false) {
            return false; //没遇到障碍
        } else {//遇到障碍
            Adjustment();//调整方向
            return true;
        }
    }

    public static void Adjustment() {
        if(BeginActivity.getBack() == false) {
            direction += ANGLE;
            if(direction >= 360) direction -= 360;
        } else {
            //小船返回
            direction -= ANGLE;
            if(direction <= 0) direction += 360;
        }
        BeginActivity.getBoatBehavior().getBoat().setDirection(direction);
    }


    public int getDirection() {return direction;}

}
