package deepin.iospopwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
