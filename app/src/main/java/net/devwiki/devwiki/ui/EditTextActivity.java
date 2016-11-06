package net.devwiki.devwiki.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditTextActivity extends BaseActivity {


    @BindView(R.id.normal_et)
    EditText mNormalEt;
    @BindView(R.id.pass_et)
    EditText mPassEt;
    @BindView(R.id.email_et)
    EditText mEmailEt;
    @BindView(R.id.email_auto_tv)
    AutoCompleteTextView mEmailAutoTv;
    @BindView(R.id.email_multi_auto_tv)
    MultiAutoCompleteTextView mEmailMultiAutoTv;
    @BindView(R.id.email_text_input_et)
    TextInputEditText mEmailTextInputEt;
    @BindView(R.id.email_text_input_layout)
    TextInputLayout mEmailTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        ButterKnife.bind(this);

        mEmailEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
