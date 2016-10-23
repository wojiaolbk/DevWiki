package net.devwiki.devwiki.device;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.ui.UIFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceFragment extends Fragment {


    public DeviceFragment() {
        // Required empty public constructor
    }

    public static DeviceFragment newInstance() {
        return new DeviceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device, container, false);
    }

}
