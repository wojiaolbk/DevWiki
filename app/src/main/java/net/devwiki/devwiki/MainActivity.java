package net.devwiki.devwiki;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private static final String SHORTCUT_ID_BLOG = "Blog";
    private static final String SHORTCUT_ID_GITHUB = "GitHub";

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
        mNavView.setCheckedItem(R.id.nav_ui);

        addShortcut();
    }

    private void addShortcut() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            List<ShortcutInfo> infoList = shortcutManager.getDynamicShortcuts();
            boolean isHadBlog = false;
            boolean isHadGitHub = false;
            for (ShortcutInfo shortcutInfo : infoList) {
                if (SHORTCUT_ID_BLOG.equals(shortcutInfo.getId()) ) {
                    isHadBlog = true;
                }
                if (SHORTCUT_ID_GITHUB.equals(shortcutInfo.getId())) {
                    isHadGitHub = true;
                }
            }
            ShortcutInfo shortcut;
            if (!isHadBlog) {
                shortcut = new ShortcutInfo.Builder(this, SHORTCUT_ID_BLOG)
                        .setShortLabel(getString(R.string.blog_short_label))
                        .setLongLabel(getString(R.string.blog_long_label))
                        .setIcon(Icon.createWithResource(this, R.drawable.ic_launcher))
                        .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.devwiki.net")))
                        .build();
                shortcutManager.addDynamicShortcuts(Arrays.asList(shortcut));
            }
            if (!isHadGitHub) {
                shortcut = new ShortcutInfo.Builder(this, SHORTCUT_ID_GITHUB)
                        .setShortLabel(getString(R.string.github_short_label))
                        .setLongLabel(getString(R.string.github_long_label))
                        .setIcon(Icon.createWithResource(this, R.drawable.ic_github))
                        .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Dev-Wiki/DevWiki")))
                        .build();
                shortcutManager.addDynamicShortcuts(Arrays.asList(shortcut));
            }
        }
    }

    private void openGitHubPage() {
        Uri uri = Uri.parse("https://github.com/Dev-Wiki/DevWiki");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        startActivity(intent);
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
