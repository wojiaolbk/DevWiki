package net.devwiki.devwiki.ui.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.devwiki.common.widget.ViewHolder;
import net.devwiki.devwiki.R;
import net.devwiki.log.DevLog;

import java.util.List;

/**
 * ListView的适配器
 * Created by DevWiki on 2016/11/3.
 */

public class ListViewAdapter extends BaseAdapter {

    interface ViewType {
        int MALE = 0;
        int FEMALE = 1;
    }

    private Context mContext;
    private List<Person> mList;

    public ListViewAdapter(Context context, List<Person> dataList) {
        this.mContext = context;
        this.mList = dataList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Person getItem(int i) {
        return mList == null ? null
                : (i >= mList.size() ? null : mList.get(i));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        Person person = getItem(position);
        return person == null ? ViewType.MALE
                : (person.getSex() == Person.Sex.MALE ? ViewType.MALE : ViewType.FEMALE);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Person person = getItem(i);
        if (person == null) {
            DevLog.w("data is not allow null");
            return null;
        }
        ViewHolder holder;
        if (getItemViewType(i) == ViewType.MALE) {
            holder = ViewHolder.getHolder(mContext, null, viewGroup, R.layout.item_list_view_male);
        } else {
            holder = ViewHolder.getHolder(mContext, null, viewGroup, R.layout.item_list_view_female);
        }
        TextView nameTv = holder.get(R.id.name_tv);
        TextView sexTv = holder.get(R.id.sex_tv);
        nameTv.setText(person.getName());
        sexTv.setText(person.getSex() == Person.Sex.MALE ? "男" : "女");
        return holder.getConvertView();
    }
}
