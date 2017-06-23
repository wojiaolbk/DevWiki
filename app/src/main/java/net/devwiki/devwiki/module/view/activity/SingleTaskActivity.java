package net.devwiki.devwiki.module.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.devwiki.devwiki.R;
import net.devwiki.log.DevLog;

/**
 * single task 模式的 activity
 * Created by zyz on 2017/6/23.
 */

public class SingleTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        DevLog.d("taskId:" + getTaskId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        DevLog.d("taskId:" + getTaskId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        DevLog.d("taskId:" + getTaskId());
    }

    @Override
    protected void onPause() {
        super.onPause();
        DevLog.d("taskId:" + getTaskId());
    }

    @Override
    protected void onStop() {
        super.onStop();
        DevLog.d("taskId:" + getTaskId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DevLog.d("taskId:" + getTaskId());
    }
}
