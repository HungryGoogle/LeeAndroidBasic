package deepin.viewstub;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ViewStub textStub;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        findViewById(R.id.id_get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textStub = (ViewStub) findViewById(R.id.textStub);
                if (mTextView == null){
                    ViewGroup viewGroup = (ViewGroup) textStub.inflate(); // 加载相应的TextView
                    mTextView = viewGroup.findViewById(R.id.textId);
                }

                mTextView.setText("从ViewStub加载的TextView");
            }
        });

    }
}
