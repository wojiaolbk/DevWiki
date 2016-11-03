package net.devwiki.devwiki.ui.list;

import android.os.Bundle;
import android.widget.ListView;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewActivity extends BaseActivity {

    @BindView(R.id.list_vew)
    ListView mListVew;

    private ListViewAdapter mAdapter;
    private List<Person> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);

        mList = new ArrayList<>();
        mAdapter = new ListViewAdapter(getApplicationContext(), mList);
        mListVew.setAdapter(mAdapter);

        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            if (i%3==0 || i%5==0) {
                person.setSex(Person.Sex.MALE);
            } else {
                person.setSex(Person.Sex.FEMALE);
            }
            person.setName("Person-" + i);
            mList.add(person);
        }
        mAdapter.notifyDataSetChanged();
    }
}
