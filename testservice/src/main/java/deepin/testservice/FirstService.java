package deepin.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class FirstService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("leeTest------>", "FirstService onCreate" );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("leeTest------>", "FirstService onStartCommand" );
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("leeTest------>", "FirstService onDestroy" );
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.i("leeTest------>", "FirstService onBind" );
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("leeTest------>", "FirstService onUnbind" );
        return false;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i("leeTest------>", "FirstService onRebind" );
        super.onRebind(intent);
    }

    public FirstService() {
    }


    class MyBinder extends Binder {

        String strtime;
        public void setData(String str){
            Log.i("leeTest------>", "FirstService setData, strtime = " + str );
            strtime = str;
        }

        public String getData(){
            Log.i("leeTest------>", "FirstService getData, strtime = " + strtime );
            return  "restult : " + strtime;
        }

//        public void startDownload() {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    // 执行具体的下载任务
//                }
//            }).start();
//        }

    }
}
