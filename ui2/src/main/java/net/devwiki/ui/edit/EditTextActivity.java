package net.devwiki.ui.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.devwiki.ui.R;
import net.devwiki.base.BaseActivity;
import net.devwiki.ui.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditTextActivity extends BaseActivity {


    @BindView(R2.id.one_input_btn)
    Button mOneInputBtn;
    @BindView(R2.id.one_input_all_btn)
    Button mOneInputAllBtn;
    @BindView(R2.id.two_input_btn)
    Button mTwoInputBtn;
    @BindView(R2.id.two_input_all_btn)
    Button mTwoInputAllBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.one_input_btn, R2.id.one_input_all_btn, R2.id.one_input_all2_btn, R2.id.two_input_btn,
            R2.id.two_input2_btn, R2.id.two_input_all_btn})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R2.id.one_input_btn:
                intent = new Intent(EditTextActivity.this, OneInputActivity.class);
                break;
            case R2.id.one_input_all_btn:
                intent = new Intent(EditTextActivity.this, OneInputAllActivity.class);
                break;
            case R2.id.one_input_all2_btn:
                intent = new Intent(EditTextActivity.this, OneInputAll2Activity.class);
                break;
            case R2.id.two_input_btn:
                intent = new Intent(EditTextActivity.this, TwoInputActivity.class);
                break;
            case R2.id.two_input2_btn:
                intent = new Intent(EditTextActivity.this, TwoInput2Activity.class);
                break;
            case R2.id.two_input_all_btn:
                intent = new Intent(EditTextActivity.this, TwoInputAllActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
