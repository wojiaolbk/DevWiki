package net.devwiki.devwiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import net.devwiki.devwiki.data.DataFragment;
import net.devwiki.devwiki.device.DeviceFragment;
import net.devwiki.devwiki.media.MediaFragment;
import net.devwiki.devwiki.net.NetFragment;
import net.devwiki.devwiki.service.ServiceFragment;
import net.devwiki.devwiki.ui.UIFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.content_fl)
    FrameLayout mContentFl;
    @BindView(R.id.content_main)
    RelativeLayout mContentMain;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mToolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGitHubPage();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        mFragmentManager.beginTransaction().replace(R.id.content_fl, UIFragment.newInstance()).commit();
    }

    private void openGitHubPage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_ui:
                mFragmentManager.beginTransaction().replace(R.id.content_fl, UIFragment.newInstance()).commit();
                break;
            case R.id.nav_service:
                mFragmentManager.beginTransaction().replace(R.id.content_fl, ServiceFragment.newInstance()).commit();
                break;
            case R.id.nav_data:
                mFragmentManager.beginTransaction().replace(R.id.content_fl, DataFragment.newInstance()).commit();
                break;
            case R.id.nav_network:
                mFragmentManager.beginTransaction().replace(R.id.content_fl, NetFragment.newInstance()).commit();
                break;
            case R.id.nav_media:
                mFragmentManager.beginTransaction().replace(R.id.content_fl, MediaFragment.newInstance()).commit();
                break;
            case R.id.nav_device:
                mFragmentManager.beginTransaction().replace(R.id.content_fl, DeviceFragment.newInstance()).commit();
                break;
            case R.id.nav_share:

                break;
            case R.id.nav_send:

                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
