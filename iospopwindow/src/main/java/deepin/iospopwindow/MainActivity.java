package deepin.iospopwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.radioGroupID);
        List<String> list=new ArrayList<String>();
        list.add("服装33333");
        list.add("玩具44444");
        list.add("饰品5555");
        list.add("饰品6666");
        list.add("文具7777");
        list.add("文具8888");
        list.add("文具9999");

        int index=0;
        for(; index < list.size(); ++index){

            RadioButton button=new RadioButton(this);
            button.setId(index);
            button.setText(list.get(index));
//            button.setPointerIcon();

            radioGroup.addView(button);

//            LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) button
//                    .getLayoutParams();
//            layoutParams.setMargins(0, 0,  0, 0);//4个参数按顺序分别是左上右下
//            button.setLayoutParams(layoutParams);
        }


        findViewById(R.id.id_ios_pop_sheet_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BottomUpMenu(MainActivity.this)
                        .builder()
                        .setCancelBtnVisiable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("上传到群相册",
                                BottomUpMenu.SheetItemColor.Blue,
                                new BottomUpMenu.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                    }
                                })
                        .addSheetItem("拍照",
                                BottomUpMenu.SheetItemColor.Blue,
                                new BottomUpMenu.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                    }
                                })
                        .addSheetItem("保存到手机",
                                BottomUpMenu.SheetItemColor.Blue,
                                new BottomUpMenu.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                    }
                                }).show();

            }
        });
    }
}
