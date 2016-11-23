package net.devwiki.devwiki.ui.badge;

import android.os.Bundle;
import android.widget.RelativeLayout;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BadgeActivity extends BaseActivity {

    @BindView(R.id.badge_icon)
    BadgeIcon mBadgeIcon;
    @BindView(R.id.activity_badge)
    RelativeLayout mActivityBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);
        ButterKnife.bind(this);

        mBadgeIcon.setIcon(R.drawable.ic_launcher);
        mBadgeIcon.setCount(10);
    }
}
