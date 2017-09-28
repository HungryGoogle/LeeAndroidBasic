package deepin.lambda;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.IntSupplier;

import static java.lang.String.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.id_test1).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                // 1 使用 lambda expression
                Callable<Integer> c2 = true ? (() -> 42) : (() -> 24);
                try {
                    System.out.println("leeTest------->" + c2.call());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        findViewById(R.id.id_test2).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                // 2 使用 lambda expression
                Runnable race2 = () -> Log.i("leeTest------->", "Hello world !");
                race2.run();
            }
        });
    }

}
