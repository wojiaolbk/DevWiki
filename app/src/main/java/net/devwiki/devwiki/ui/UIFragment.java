package net.devwiki.devwiki.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.net.NetFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UIFragment extends Fragment {


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ui, container, false);
    }

}
