package net.devwiki.devwiki.module.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.module.view.activity.LaunchModeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * UI相关
 * Created by zyz on 2017/3/21.
 */

public class UIFragment extends Fragment {

    public static UIFragment newInstance() {
        return new UIFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ui, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.activity_btn})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.activity_btn:
                intent = new Intent(getActivity(), LaunchModeActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
