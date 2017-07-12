package net.devwiki.devwiki.module.view.animation.pic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import net.devwiki.devwiki.R;

/**
 * Created by zyz on 2017/7/3.
 */

public class FpsImageActivity extends AppCompatActivity {

    private VoiceAnimImageView waveIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fps_image);
        enableScreenKeepOn();
        waveIv = (VoiceAnimImageView) findViewById(R.id.wave_iv);
        waveIv.startRecordingVoiceAnim(80);
        waveIv.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 5000);
        enableScreenKeepOn();
    }

    public void enableScreenKeepOn() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void disableScreenKeepOn() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disableScreenKeepOn();
    }
}
