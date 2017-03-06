package net.devwiki.ui.recycler;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import net.devwiki.ui.R;
import net.devwiki.base.BaseActivity;
import net.devwiki.ui.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerActivity extends BaseActivity {

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refresh_layout)
//    SwipeRefreshLayout mRefreshLayout;
    @BindView(R2.id.activity_recycler)
    RelativeLayout mActivityRecycler;

    private DataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new DataAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(true);

        for (int i = 0; i < 100; i++) {
            mAdapter.appendItem("Test-" + i);
        }
    }
}
