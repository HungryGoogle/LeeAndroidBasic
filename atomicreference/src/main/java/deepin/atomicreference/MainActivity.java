package deepin.atomicreference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.id_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String initialReference = "initial value referenced";
//
//                AtomicReference<String> atomicStringReference = new AtomicReference<String>(initialReference);
//
//                String newReference = "new value referenced";
//                boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
//                System.out.println("exchanged: " + exchanged);
//
//                exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
//                System.out.println("exchanged: " + exchanged);
//
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

    }
}
