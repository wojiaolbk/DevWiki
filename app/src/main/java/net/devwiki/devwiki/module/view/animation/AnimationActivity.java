package net.devwiki.devwiki.module.view.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.WindowManager;

import net.devwiki.devwiki.R;

/**
 * 动画测试界面
 * Created by zyz on 2017/7/3.
 */

public class AnimationActivity extends AppCompatActivity {

    public static final String FPS_TYPE = "fpsType";

    private AppCompatButton fps1Btn;
    private AppCompatButton fps2Btn;
    private AppCompatButton fps3Btn;
    private AppCompatButton fps5Btn;
    private AppCompatButton fps10Btn;
    private AppCompatButton fps20Btn;
    private View.OnClickListener mClickListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        enableScreenKeepOn();

        fps1Btn = (AppCompatButton) findViewById(R.id.fps_1_btn);
        fps2Btn = (AppCompatButton) findViewById(R.id.fps_2_btn);
        fps3Btn = (AppCompatButton) findViewById(R.id.fps_3_btn);
        fps5Btn = (AppCompatButton) findViewById(R.id.fps_5_btn);
        fps10Btn = (AppCompatButton) findViewById(R.id.fps_10_btn);
        fps20Btn = (AppCompatButton) findViewById(R.id.fps_20_btn);
        mClickListener = new ClickListener();
        fps1Btn.setOnClickListener(mClickListener);
        fps2Btn.setOnClickListener(mClickListener);
        fps3Btn.setOnClickListener(mClickListener);
        fps5Btn.setOnClickListener(mClickListener);
        fps10Btn.setOnClickListener(mClickListener);
        fps20Btn.setOnClickListener(mClickListener);
    }

    public void enableScreenKeepOn() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void disableScreenKeepOn() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AnimationActivity.this, FpsActivity.class);
            switch (v.getId()) {
                case R.id.fps_1_btn:
                    intent.putExtra(FPS_TYPE, 1);
                    break;
                case R.id.fps_2_btn:
                    intent.putExtra(FPS_TYPE, 2);
                    break;
                case R.id.fps_3_btn:
                    intent.putExtra(FPS_TYPE, 3);
                    break;
                case R.id.fps_5_btn:
                    intent.putExtra(FPS_TYPE, 5);
                    break;
                case R.id.fps_10_btn:
                    intent.putExtra(FPS_TYPE, 10);
                    break;
                case R.id.fps_20_btn:
                    intent.putExtra(FPS_TYPE, 20);
                    break;
            }
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disableScreenKeepOn();
    }
}
