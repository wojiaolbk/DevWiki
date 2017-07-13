package net.devwiki.devwiki.module.thread.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import net.devwiki.devwiki.R;
import net.devwiki.log.DevLog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {


    @BindView(R.id.result_tv)
    AppCompatTextView mResultTv;

    private Subscriber<Long> mSubSub;
    private Subscription mObsSub;
    private boolean isSubCancel = true;
    private boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);
    }

    private void start() {
        isFinish = false;
        mSubSub = new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                mResultTv.setText(R.string.complete);
            }

            @Override
            public void onError(Throwable e) {
                mResultTv.setText(e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                mResultTv.setText(String.valueOf(aLong));
                if (isFinish) {
                    DevLog.d("isSubCancel:" + isSubCancel);
                    if (isSubCancel) {
                        if (!mSubSub.isUnsubscribed()) {
                            mSubSub.unsubscribe();
                        }
                    } else {
                        if (!mObsSub.isUnsubscribed()) {
                            mObsSub.unsubscribe();
                        }
                    }
                }
            }
        };
        mObsSub = Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubSub);
    }

    @OnCheckedChanged({R.id.sub_rb, R.id.obs_rb})
    public void onChecked(CompoundButton view, boolean isChecked) {
        if (isChecked) {
            isSubCancel = view.getId() == R.id.sub_rb;
        }
        DevLog.d("isSubCancel:" + isSubCancel);
    }

    @OnClick({R.id.start_btn, R.id.stop_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_btn:
                start();
                break;
            case R.id.stop_btn:
                isFinish = true;
                break;
        }
    }
}
