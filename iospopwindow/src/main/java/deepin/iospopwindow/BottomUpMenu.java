package deepin.iospopwindow;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class BottomUpMenu {
    private Context context;
    private Dialog dialog;
    private TextView txt_title;
    private TextView txt_cancel;
    private LinearLayout scrolview_list_Layout;
    private ScrollView scrolview_Layout_content;
    private boolean showTitle = false;
    private List<SheetItem> sheetItemList;
    private Display display;


    private int iMenuItemHeight = 55; // 单位dp

    public BottomUpMenu(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public BottomUpMenu builder() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_actionsheet_new, null);

        // 设置为和屏幕一样宽
        int width = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        view.setMinimumWidth(width);

        scrolview_Layout_content = (ScrollView) view.findViewById(R.id.scrolview_Layout_content);
        scrolview_list_Layout = (LinearLayout) view.findViewById(R.id.lLayout_content);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_cancel = (TextView) view.findViewById(R.id.txt_cancel);
        txt_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
//        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.x = 0;
//        lp.y = 0;
//        dialogWindow.setAttributes(lp);

        return this;
    }

    public BottomUpMenu setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            return this;
        }

        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(title);
        return this;
    }

    public BottomUpMenu setCancelBtnVisiable(boolean btnVisiable) {
        if(btnVisiable){
            txt_cancel.setVisibility(View.VISIBLE);
        }else {
            txt_cancel.setVisibility(View.GONE);
        }

        return this;
    }

    public BottomUpMenu setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public BottomUpMenu setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }


    public BottomUpMenu addSheetItem(String strItem, SheetItemColor color,
                                     OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<SheetItem>();
        }
        sheetItemList.add(new SheetItem(strItem, color, listener));
        return this;
    }

    private void setSheetItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }

        int size = sheetItemList.size();

        if (size >= 7) {
            LayoutParams params = (LayoutParams) scrolview_Layout_content
                    .getLayoutParams();
            params.height = display.getHeight() / 2;
            scrolview_Layout_content.setLayoutParams(params);
        }

        for (int i = 1; i <= size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i - 1);
            String strItem = sheetItem.name;
            SheetItemColor color = sheetItem.color;
            final OnSheetItemClickListener listener = (OnSheetItemClickListener) sheetItem.itemClickListener;

            TextView textView = new TextView(context);
            textView.setText(strItem);
            textView.setTextSize(18);
            textView.setGravity(Gravity.CENTER);

            if (size == 1) {
                if (showTitle) {
                    textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                } else {
                    textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                }
            } else {
                if (showTitle) {
                    if (i >= 1 && i < size) {
                        textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    }
                } else {
                    if (i == 1) {
                        textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    } else if (i < size) {
                        textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    }
                }
            }

            if (color == null) {
                textView.setTextColor(Color.parseColor(SheetItemColor.Blue.getName()));
            } else {
                textView.setTextColor(Color.parseColor(color.getName()));
            }

            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int) (iMenuItemHeight * scale + 0.5f);
            textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));

            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(index);
                    dialog.dismiss();
                }
            });

            scrolview_list_Layout.addView(textView);
        }
    }

    public void show() {
        setSheetItems();
        dialog.show();
    }

    public interface OnSheetItemClickListener {
        void onClick(int which);
    }

    public class SheetItem {
        String name;
        OnSheetItemClickListener itemClickListener;
        SheetItemColor color;

        public SheetItem(String name, SheetItemColor color,
                         OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.itemClickListener = itemClickListener;
        }
    }

    public enum SheetItemColor {
        Blue("#037BFF"), Red("#FD4A2E");

        private String name;

        private SheetItemColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
