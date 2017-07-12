package net.devwiki.devwiki.module.view.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.WindowManager;

import net.devwiki.devwiki.R;
import net.devwiki.log.DevLog;

/**
 * Created by zyz on 2017/7/3.
 */

public class FpsCustomActivity extends AppCompatActivity {

    private VoiceRecordAnimView waveIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fps_custom);
        enableScreenKeepOn();
        waveIv = (VoiceRecordAnimView) findViewById(R.id.wave_iv);
        waveIv.startRecordingVoiceAnim(80);
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
