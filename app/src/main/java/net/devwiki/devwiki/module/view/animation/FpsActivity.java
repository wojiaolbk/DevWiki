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

public class FpsActivity extends AppCompatActivity {

    private AppCompatImageView waveIv;
    private AnimationDrawable mAnimationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fps);
        enableScreenKeepOn();
        waveIv = (AppCompatImageView) findViewById(R.id.wave_iv);
        int fps = getIntent().getIntExtra(AnimationActivity.FPS_TYPE, 1);
        DevLog.d("fsp:" + fps);
        switch (fps) {
            case 1:
                waveIv.setImageResource(R.drawable.ic_chat_record_wave_1);
                break;
            case 2:
                waveIv.setImageResource(R.drawable.ic_chat_record_wave_2);
                break;
            case 3:
                waveIv.setImageResource(R.drawable.ic_chat_record_wave_3);
                break;
            case 5:
                waveIv.setImageResource(R.drawable.ic_chat_record_wave_5);
                break;
            case 10:
                waveIv.setImageResource(R.drawable.ic_chat_record_wave_10);
                break;
            case 20:
                waveIv.setImageResource(R.drawable.ic_chat_record_wave_20);
                break;
        }

        mAnimationDrawable = (AnimationDrawable) waveIv.getDrawable();
        if (mAnimationDrawable != null) {
            mAnimationDrawable.start();
        }
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
