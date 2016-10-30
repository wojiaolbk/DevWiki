package net.devwiki.devwiki.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavActivity extends BaseActivity {

    @BindView(R.id.nav_tv)
    TextView mNavTv;
    @BindView(R.id.bottom_nav)
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
                    case R.id.bottom_nav_ui:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R.id.bottom_nav_data:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R.id.bottom_nav_service:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R.id.bottom_nav_net:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R.id.bottom_nav_media:
                        mNavTv.setText(item.getTitle());
                        break;
                }
                return true;
            }
        });
    }
}
