package deepin.testspinner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    // 数据源
    private String[] city = {"北京", "上海", "广州", "深圳", "长沙", "南京", "杭州"};

    private List<String> list = null;

    private ArrayAdapter adapter = null;

    private Spinner spinner = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list = new ArrayList<>();
        list.add("here1");
        list.add("here2");
        list.add("here3");

//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner = findViewById(R.id.id_spinner);
//        spinner.setAdapter(adapter);

//        spinner.setOnItemSelectedListener(this);
    }
}
