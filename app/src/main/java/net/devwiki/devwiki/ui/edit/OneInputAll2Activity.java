package net.devwiki.devwiki.ui.edit;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OneInputAll2Activity extends BaseActivity {

    @BindView(R.id.logo_iv)
    ImageView mLogoIv;
    @BindView(R.id.email_et)
    EditText mEmailEt;
    @BindView(R.id.ok_btn)
    Button mOkBtn;
    @BindView(R.id.activity_one_input)
    ScrollView mActivityOneInput;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_input_all2);
        ButterKnife.bind(this);

        mHandler = new Handler();
        mActivityOneInput.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mActivityOneInput.getRootView().getHeight() - mActivityOneInput.getHeight() > 100) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mActivityOneInput.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                }
            }
        });
    }
}
