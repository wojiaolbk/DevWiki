package net.devwiki.ui.badge;

import android.os.Bundle;
import android.widget.RelativeLayout;

import net.devwiki.ui.R;
import net.devwiki.base.BaseActivity;
import net.devwiki.ui.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BadgeActivity extends BaseActivity {

    @BindView(R2.id.badge_icon)
    BadgeIcon mBadgeIcon;
    @BindView(R2.id.activity_badge)
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
