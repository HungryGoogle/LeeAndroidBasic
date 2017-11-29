package deepin.testspinner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by li on 2017/11/20.
 */

public class MySpinnerButton extends Button {
    private Context context;

    public MySpinnerButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        setOnClickListener(new MySpinnerButtonOnClickListener());
    }

    public MySpinnerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOnClickListener(new MySpinnerButtonOnClickListener());
    }

    public MySpinnerButton(Context context) {
        super(context);
        this.context = context;
        setOnClickListener(new MySpinnerButtonOnClickListener());
    }

    /**
     * MySpinnerButton的点击事件
     */
    class MySpinnerButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            final MySpinnerDropDownItems mSpinnerDropDrownItems = new MySpinnerDropDownItems(context);
            if (!mSpinnerDropDrownItems.isShowing()) {
                mSpinnerDropDrownItems.showAsDropDown(MySpinnerButton.this);
            }
        }
    }

    /**
     * MySpinnerButton的下拉列表     *
     */
    class MySpinnerDropDownItems extends PopupWindow {

        private Context context;
        private LinearLayout mLayout;  // 下拉列表的布局
        private ListView mListView;    // 下拉列表控件
        private ArrayList<HashMap<String, String>> mData;

        public MySpinnerDropDownItems(Context context){
            super(context);

            this.context = context;
            // 下拉列表的布局
            mLayout = new LinearLayout(context);
            mLayout.setOrientation(LinearLayout.VERTICAL);

            // 下拉列表控件
            mListView = new ListView(context);
            mListView.setLayoutParams(new LinearLayout.LayoutParams(MySpinnerButton.this.getLayoutParams().width, LinearLayout.LayoutParams.WRAP_CONTENT));
//            mListView.setCacheColorHint(Color.TRANSPARENT);
            mData = new ArrayList<HashMap<String,String>>();
            for(int i=0; i<5; i++){
                HashMap<String, String> mHashmap = new HashMap<String, String>();
                mHashmap.put("spinner_dropdown_item_textview", "textView " + i);
                mData.add(mHashmap);
            }

            // 为listView设置适配器
            mListView.setAdapter(new MyAdapter(context,
                    mData, R.layout.spinner_dropdown_item,
                    new String[]{"spinner_dropdown_item_textview"}, new int[]{R.id.spinner_dropdown_item_textview}));

            // 设置listView的点击事件
            mListView.setOnItemClickListener(new MyListViewOnItemClickedListener());
            // 把下拉列表添加到layout中。
            mLayout.addView(mListView);

            setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            setContentView(mLayout);
//            setFocusable(true);

            mLayout.setFocusableInTouchMode(true);
        }

        /**
         * 适配器
         */
        public class MyAdapter extends BaseAdapter {

            private Context context;
            private List<? extends Map<String, ?>> mData;
            private int mResource;
            private String[] mFrom;
            private int[] mTo;
            private LayoutInflater mLayoutInflater;

            /**
             * 我的适配器的构造方法
             * @param context 调用方的上下文
             * @param data 数据
             */
            public MyAdapter(Context context, ArrayList<HashMap<String, String>> data, int resource, String[] from, int[] to){

                this.context = context;
                this.mData = data;
                this.mResource = resource;
                this.mFrom = from;
                this.mTo = to;
                this.mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            }

            /**
             * 系统在绘制ListView之前，将会先调用getCount方法来获取Item的个数
             */
            public int getCount() {

                return this.mData.size();
            }

            public Object getItem(int position) {

                return this.mData.get(position);
            }

            public long getItemId(int position) {

                return position;
            }

            /**
             * 每绘制一个 Item就会调用一次getView方法，
             * 在此方法内就可以引用事先定义好的xml来确定显示的效果并返回一个View对象作为一个Item显示出来。
             * 也 正是在这个过程中完成了适配器的主要转换功能，把数据和资源以开发者想要的效果显示出来。
             * 也正是getView的重复调用，使得ListView的使用更 为简单和灵活。
             * 这两个方法是自定ListView显示效果中最为重要的，同时只要重写好了就两个方法，ListView就能完全按开发者的要求显示。
             * 而 getItem和getItemId方法将会在调用ListView的响应方法的时候被调用到。
             * 所以要保证ListView的各个方法有效的话，这两个方法也得重写。
             */
            public View getView(int position, View contentView, ViewGroup parent) {

                contentView = this.mLayoutInflater.inflate(this.mResource, parent, false);

                // 设置contentView的内容和样式，这里重点是设置contentView中文字的大小
                for(int index=0; index<this.mTo.length; index++){
                    TextView textView = (TextView) contentView.findViewById(this.mTo[index]);
                    textView.setText(this.mData.get(position).get(this.mFrom[index]).toString());
                }

                return contentView;
            }
        }

        /**
         * listView的点击事件         *
         */
        class MyListViewOnItemClickedListener implements AdapterView.OnItemClickListener{

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView mTextView = (TextView) view.findViewById(R.id.spinner_dropdown_item_textview);
                String content = mTextView.getText().toString();
                MySpinnerButton.this.setText(content);
                MySpinnerDropDownItems.this.dismiss();
            }
        }
    }
}
