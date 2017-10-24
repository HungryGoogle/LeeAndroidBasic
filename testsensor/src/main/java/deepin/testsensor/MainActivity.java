package deepin.testsensor;


import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static android.os.PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK;

public class MainActivity extends Activity implements SensorEventListener {

    public static final String TAG = "SensorTest";

    boolean bUserProximity = false;

    private SensorManager sensorManager;
    Sensor mSensor;
    private PowerManager localPowerManager = null;// 电源管理对象
    private PowerManager.WakeLock localWakeLock = null;// 电源锁

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        localPowerManager = (PowerManager) getSystemService(POWER_SERVICE);

        // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        localWakeLock = this.localPowerManager.newWakeLock(PROXIMITY_SCREEN_OFF_WAKE_LOCK, "Tag");// 第一个参数为电源锁级别，第二个是日志tag

        findViewById(R.id.id_life_cycle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityLife.class));
                sensorManager.unregisterListener(MainActivity.this);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        boolean result = sensorManager.registerListener(this,mSensor,
                SensorManager.SENSOR_DELAY_UI);

        bUserProximity = true;
        Log.i("leeTest------>", "registerListener result = " + result);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("leeTest------>", "unregisterListener");
        bUserProximity = false;
        sensorManager.unregisterListener(this, mSensor);
        sensorManager = null;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(!bUserProximity){
            return;
        }

        if(localWakeLock == null){
            return;
        }

        float[] values = event.values;
        Log.i("leeTest------>", "value = " + event.sensor.getType());
        switch (event.sensor.getType()) {
            case Sensor.TYPE_PROXIMITY:

                if (values[0] == 0.0) {
                    // 贴近手机
                    System.out.println("hands up");
                    Log.i("leeTest------>", String.valueOf(values[0]));

                    // 判断是否已经申请了锁
                    if (localWakeLock.isHeld()) {
                        return;
                    } else {
                        // 申请设备电源锁
                        localWakeLock.acquire();
                    }
                } else {
                    // 远离手机
                    System.out.println("hands moved");
                    Log.i("leeTest------>", String.valueOf(values[0]));

                    localWakeLock.setReferenceCounted(false);
                    localWakeLock.release(); // 释放设备电源锁

                    break;
                }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.i("leeTest------>", "onAccuracyChanged, i = " + i);
    }
}