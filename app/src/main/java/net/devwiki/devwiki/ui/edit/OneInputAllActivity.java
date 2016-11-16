package net.devwiki.devwiki.ui.edit;

import android.os.Bundle;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.base.BaseActivity;

import butterknife.ButterKnife;

public class OneInputAllActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_input_all);
        ButterKnife.bind(this);
    }
}
