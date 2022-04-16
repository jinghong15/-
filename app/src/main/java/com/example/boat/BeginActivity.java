package com.example.boat;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boat.Concentration.ConcentrationThread;
import com.example.boat.GPS.PermissionUtils;
import com.example.boat.begin.BoatBehavior;
import com.example.boat.distance.DistanceThread;
import com.example.boat.electric.ElectricThread;
import com.example.boat.elude.EludeThread;
import com.example.boat.speed.SpeedThread;
import com.example.boat.time.TimeThread;

import java.util.Timer;
import java.util.TimerTask;

public class BeginActivity extends AppCompatActivity {
    public static final int TIMER = 1;
    public static final int GPS = 2;
    public static final int ELUDE = 3;
    public static final int CONCENTRATION = 4;
    public static final int SPEED = 5;
    public static final int ELECTRIC = 6;
    public static final int DISTANCE = 7;
    public static boolean back;
    public static boolean beginOrNot;

    private LocationManager mLocationManager;
    private TextView txt_1;
    private TextView txt_2;
    private TextView txt_3;
    private TextView txt_speed, txt_direction, txt_concentration, txt_distance, txt_electric;
    final Timer timer = new Timer();
    private Thread thread1, thread2, thread3, thread4, thread5, thread6;
    private TextView tvShow;
    private static BoatBehavior boatBehavior;

    private static Activity ac;

    private String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static final int REQUEST_PERMISSION_CODE = 12;//定位权限

    public static BoatBehavior getBoatBehavior() {
        return boatBehavior;
    }

    public static void setBoatBehavior(BoatBehavior boatBehavior) {
        BeginActivity.boatBehavior = boatBehavior;
    }

    public static Activity getAc() {
        return ac;
    }

    public static Boolean getBack() { return back; }
    public static void setBack(boolean flag) { back = flag;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        ac = this;
        init();
        startLocate();
        autoSubmit();
        back = false;
        beginOrNot = false;
    }
    /**
     * 用线程实现每隔一段时间自动执行代码
     */
    private void autoSubmit(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=0;
                mHandler.sendMessage(message);

                //这里填写自动执行的代码
            }
        },1000, 60000);// delay 表示初始化延时。period 表示前一次执行结束到下一次执行开始的间隔时间
        //1秒=1000毫秒
        //1分钟=60000毫秒
        //10分钟=600000毫秒
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                System.out.println("每隔一段时间执行一次GPS定位");

                startLocate();
            }
        }
    };

    /**
     * 关闭定时自动提交
     */
    private void cancelTimer(){
        timer.cancel();
    }


    public void doClick(View view) {
        if (!PermissionUtils.hasPermissions(this, permissions)) {
            PermissionUtils.requestPermissions(this, REQUEST_PERMISSION_CODE, permissions);
        } else {
            startLocate();
        }
    }
    public void disClick(View view) {
        mLocationManager.removeUpdates(locationListener);
        cleanText();
        cancelTimer();
        Intent myIntent;
        myIntent = new Intent(BeginActivity.this, MainActivity.class);
        startActivity(myIntent);
        this.finish();
    }

    public void init()
    {
        txt_1=findViewById(R.id.txt_1);
        txt_2=findViewById(R.id.txt_2);
        txt_3=findViewById(R.id.txt_3);

        tvShow = (TextView) findViewById(R.id.tv_one);

        //
        txt_speed=findViewById(R.id.txt_speed);
        txt_concentration=findViewById(R.id.txt_concentration);
        txt_direction=findViewById(R.id.txt_direction);
        txt_electric=findViewById(R.id.txt_electric);
        txt_distance=findViewById(R.id.txt_distance);
        //
        boatBehavior = new BoatBehavior();
        //计时
        thread1 = new Thread(new TimeThread(tvShow));
        thread1.start();
        //检测碰撞
        thread2 = new Thread(new EludeThread(txt_direction));
        thread2.start();
        //检测石油浓度
        thread3 = new Thread(new ConcentrationThread(txt_concentration));
        thread3.start();
        //速度
        thread4 = new Thread(new SpeedThread(txt_speed));
        thread4.start();
        //电量
        thread5 = new Thread(new ElectricThread(txt_electric));
        thread5.start();
        //连距离
        thread6 = new Thread(new DistanceThread(txt_distance));
        thread6.start();
    }

    @SuppressLint("MissingPermission")
    private void startLocate() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);//获取定位权限
        boolean providerEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);//isProviderEnabled函数返回一个bool类型,判断当前参数请求的权限是否打开
        if (providerEnabled) {
            /*
             * par1:定位类型,这里使用的是GPS
             * par2:更新周期(ms)
             * par3:更新最小范围(cm)
             * par4:监听委托(使用匿名函数进行逻辑处理)
             * */
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            Toast.makeText(this, "请打开GPS", Toast.LENGTH_SHORT).show();
        }
    }

    private LocationListener locationListener = new LocationListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onLocationChanged(Location location) {
            //位置信息变化时触发
            txt_1.setText("定位方式：" + location.getProvider());
            txt_2.setText("纬度：" + location.getLatitude());
            txt_3.setText("经度：" + location.getLongitude());
            if(beginOrNot == false) {
                //x 经度
                boatBehavior.getBoat().setPosition_x(location.getLongitude());
                //y 纬度
                boatBehavior.getBoat().setPosition_y(location.getLatitude());
                beginOrNot = true;
            } else {//开始移动
                boatBehavior.getBoat().setLongitude(location.getLongitude());
                boatBehavior.getBoat().setLatitude(location.getLatitude());
            }
      }
    };

    @SuppressLint("SetTextI18n")
    private void cleanText()
    {
        txt_1.setText("定位方式：" +"???");
        txt_2.setText("纬度：" +"0.0");
        txt_3.setText("经度：" +"0.0");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //销毁线程

    }

    public boolean OnKeyDown(int keyCode, KeyEvent event){
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent myIntent;
            myIntent = new Intent(BeginActivity.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return false;
    }
}
