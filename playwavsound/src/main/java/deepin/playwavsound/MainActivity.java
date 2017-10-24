package deepin.playwavsound;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {


    Button btn1;
    Button btn2;

    MediaPlayer mediaPlayer;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);



        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stopMedia();

                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.a);
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(1.0f,1.0f);
                mediaPlayer.start();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Long.valueOf("he");
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }

                stopMedia();

                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.a);
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(0.1f,0.1f);
                mediaPlayer.start();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stopMedia();
            }
        });


    }

    private void stopMedia() {
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    //TODO 资源释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
