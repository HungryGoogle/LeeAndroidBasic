package deepin.testjson;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.id_create_json).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject rootObject = new JSONObject();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("roomid", "223456789");
                    jsonObject.put("url", "http://www.zh.com?");
                    jsonObject.put("title", "音视频邀请");
                    jsonObject.put("content", "邀你音视频分享");

                    // 加入到root
                    rootObject.put("cmd_type", "video_chat");
                    rootObject.put("cmd_id", "invite");
                    rootObject.put("cmd_data", jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                final String strResult = rootObject.toString();
                Log.i("leeTest------>", strResult);
            }
        });
    }
}
