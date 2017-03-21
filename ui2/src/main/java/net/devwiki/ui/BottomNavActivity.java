package net.devwiki.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import net.devwiki.ui.R;
import net.devwiki.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavActivity extends BaseActivity {

    @BindView(R2.id.nav_tv)
    TextView mNavTv;
    @BindView(R2.id.bottom_nav)
    BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        ButterKnife.bind(this);

        mNavTv.setText(R.string.nav_ui);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R2.id.bottom_nav_ui:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R2.id.bottom_nav_data:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R2.id.bottom_nav_service:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R2.id.bottom_nav_net:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R2.id.bottom_nav_media:
                        mNavTv.setText(item.getTitle());
                        break;
                }
                return true;
            }
        });
    }
}
