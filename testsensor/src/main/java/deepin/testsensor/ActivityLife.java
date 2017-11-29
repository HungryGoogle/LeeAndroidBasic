package deepin.testsensor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActivityLife extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("leeTest---->","onCreate");
        setContentView(R.layout.activity_life);

        // 返回
        findViewById(R.id.id_test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLife.this, MainActivity.class));
            }
        });

        // 返回并删除本页
        findViewById(R.id.id_test_btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLife.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i("leeTest---->","onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("leeTest---->","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("leeTest---->","onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("leeTest---->","onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("leeTest---->","onStop");
    }
}
