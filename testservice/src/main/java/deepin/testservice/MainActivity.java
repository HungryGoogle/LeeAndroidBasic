package deepin.testservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.id_start_service)
    Button idStartService;
    @BindView(R.id.id_stop_service)
    Button idStopService;
    @BindView(R.id.id_bind_service)
    Button idBindService;
    @BindView(R.id.id_unbind_service)
    Button idUnbindService;
    @BindView(R.id.id_set_data)
    Button idSetData;
    @BindView(R.id.id_get_data)
    Button idGetData;

    private FirstService.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("leeTest------>", "MainActivity onServiceConnected");
            myBinder = (FirstService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("leeTest------>", "MainActivity onServiceDisconnected");
            myBinder = null;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        findViewById(R.id.id_start_service).setOnClickListener(this);
        findViewById(R.id.id_stop_service).setOnClickListener(this);
        findViewById(R.id.id_bind_service).setOnClickListener(this);
        findViewById(R.id.id_unbind_service).setOnClickListener(this);
        findViewById(R.id.id_get_data).setOnClickListener(this);
        findViewById(R.id.id_set_data).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent startIntent;
        switch (view.getId()) {
            case R.id.id_start_service:
                startIntent = new Intent(this, FirstService.class);
                startService(startIntent);
                break;
            case R.id.id_stop_service:
                startIntent = new Intent(this, FirstService.class);
                stopService(startIntent);
                break;
            case R.id.id_bind_service:
                startIntent = new Intent(this, FirstService.class);
                bindService(startIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.id_unbind_service:
                if (myBinder != null) {
                    unbindService(connection);
                }

                myBinder = null;
                break;
            case R.id.id_set_data:
                if (myBinder != null) {
                    myBinder.setData("hello");
                }

                break;
            case R.id.id_get_data:
                if (myBinder != null) {
                    myBinder.getData();
                }
                break;
        }
    }
}
