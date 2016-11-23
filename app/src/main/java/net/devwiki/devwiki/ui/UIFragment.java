package net.devwiki.devwiki.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.ui.badge.BadgeActivity;
import net.devwiki.devwiki.ui.edit.EditTextActivity;
import net.devwiki.devwiki.ui.list.ListViewActivity;
import net.devwiki.devwiki.ui.recycler.RecyclerActivity;

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

    @OnClick({R.id.text_btn, R.id.edit_btn, R.id.list_view_btn, R.id.recycler_btn, R.id.bottom_nav_btn, R.id.badge_btn})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.text_btn:
                intent = new Intent(getActivity(), TextViewActivity.class);
                break;
            case R.id.edit_btn:
                intent = new Intent(getActivity(), EditTextActivity.class);
                break;
            case R.id.list_view_btn:
                intent = new Intent(getActivity(), ListViewActivity.class);
                break;
            case R.id.recycler_btn:
                intent = new Intent(getActivity(), RecyclerActivity.class);
                break;
            case R.id.bottom_nav_btn:
                intent = new Intent(getActivity(), BottomNavActivity.class);
                break;
            case R.id.badge_btn:
                intent = new Intent(getActivity(), BadgeActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
