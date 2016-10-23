package net.devwiki.devwiki.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.devwiki.devwiki.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UIFragment extends Fragment {

    public static final String TAG = "UIFragment";

    public UIFragment() {
        // Required empty public constructor
    }

    public static UIFragment newInstance() {
        return new UIFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ui, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.text_btn, R.id.edit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_btn:
                break;
            case R.id.edit_btn:
                break;
        }
    }
}
