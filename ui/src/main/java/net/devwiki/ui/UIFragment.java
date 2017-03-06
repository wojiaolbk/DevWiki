package net.devwiki.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.devwiki.ui.R;
import net.devwiki.ui.badge.BadgeActivity;
import net.devwiki.ui.edit.EditTextActivity;
import net.devwiki.ui.list.ListViewActivity;
import net.devwiki.ui.recycler.RecyclerActivity;

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

    @OnClick({R2.id.text_btn, R2.id.edit_btn, R2.id.list_view_btn, R2.id.recycler_btn,
            R2.id.bottom_nav_btn, R2.id.badge_btn})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R2.id.text_btn:
                intent = new Intent(getActivity(), TextViewActivity.class);
                break;
            case R2.id.edit_btn:
                intent = new Intent(getActivity(), EditTextActivity.class);
                break;
            case R2.id.list_view_btn:
                intent = new Intent(getActivity(), ListViewActivity.class);
                break;
            case R2.id.recycler_btn:
                intent = new Intent(getActivity(), RecyclerActivity.class);
                break;
            case R2.id.bottom_nav_btn:
                intent = new Intent(getActivity(), BottomNavActivity.class);
                break;
            case R2.id.badge_btn:
                intent = new Intent(getActivity(), BadgeActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
