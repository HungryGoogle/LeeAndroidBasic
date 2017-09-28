package deepin.testsensor;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.os.Bundle;
import android.util.Log;

import static android.os.PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK;

public class MainActivity extends Activity implements SensorEventListener {

    public static final String TAG = "SensorTest";


    private SensorManager sensorManager;
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

    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        float[] values = event.values;
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
                    if (localWakeLock.isHeld()) {
                        return;
                    } else {
                        localWakeLock.setReferenceCounted(false);
                        localWakeLock.release(); // 释放设备电源锁
                    }
                    break;
                }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}