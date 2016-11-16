package net.devwiki.devwiki.ui.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditTextActivity extends BaseActivity {


    @BindView(R.id.one_input_btn)
    Button mOneInputBtn;
    @BindView(R.id.one_input_all_btn)
    Button mOneInputAllBtn;
    @BindView(R.id.two_input_btn)
    Button mTwoInputBtn;
    @BindView(R.id.two_input_all_btn)
    Button mTwoInputAllBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.one_input_btn, R.id.one_input_all_btn, R.id.one_input_all2_btn, R.id.two_input_btn,
            R.id.two_input2_btn, R.id.two_input_all_btn})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.one_input_btn:
                intent = new Intent(EditTextActivity.this, OneInputActivity.class);
                break;
            case R.id.one_input_all_btn:
                intent = new Intent(EditTextActivity.this, OneInputAllActivity.class);
                break;
            case R.id.one_input_all2_btn:
                intent = new Intent(EditTextActivity.this, OneInputAll2Activity.class);
                break;
            case R.id.two_input_btn:
                intent = new Intent(EditTextActivity.this, TwoInputActivity.class);
                break;
            case R.id.two_input2_btn:
                intent = new Intent(EditTextActivity.this, TwoInput2Activity.class);
                break;
            case R.id.two_input_all_btn:
                intent = new Intent(EditTextActivity.this, TwoInputAllActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
