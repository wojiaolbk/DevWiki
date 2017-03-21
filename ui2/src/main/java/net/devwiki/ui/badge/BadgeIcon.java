package net.devwiki.ui.badge;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.devwiki.ui.R;
import net.devwiki.log.DevLog;
import net.devwiki.ui.R2;

/**
 *
 * Created by DevWiki on 2016/11/23.
 */

public class BadgeIcon extends RelativeLayout {

    private View rootView;
    private ImageView badgeIv;
    private TextView badgeTv;

    public BadgeIcon(Context context) {
        super(context);
        initView();
    }

    public BadgeIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BadgeIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        rootView = View.inflate(getContext(), R.layout.view_badge_icon, this);
        badgeIv = (ImageView) rootView.findViewById(R2.id.badge_iv);
        badgeTv = (TextView) rootView.findViewById(R2.id.badge_tv);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        DevLog.d("width:" + rootView.getWidth());
        DevLog.d("height:" + rootView.getHeight());
        RelativeLayout.LayoutParams params = (LayoutParams) badgeIv.getLayoutParams();
        params.width = rootView.getWidth();
        params.height = rootView.getHeight();
        badgeIv.setLayoutParams(params);

        params = (LayoutParams) badgeTv.getLayoutParams();
        params.width = rootView.getWidth() / 5;
        params.height = rootView.getWidth() / 5;
        badgeTv.setLayoutParams(params);
        super.onDraw(canvas);
    }

    public void setIcon(@DrawableRes int resId) {
        badgeIv.setImageResource(resId);
    }

    public void setCount(int count) {
        if (count == 0) {
            badgeTv.setText("");
        } else if (count > 99){
            badgeTv.setText("99");
        } else {
            badgeTv.setText(String.valueOf(count));
        }
    }
}
