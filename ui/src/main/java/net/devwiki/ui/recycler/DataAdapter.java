package net.devwiki.ui.recycler;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devwiki.ui.R;
import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recycler.BaseHolder;
import net.devwiki.ui.R2;

/**
 * Created by DevWiki on 2016/11/4.
 */

public class DataAdapter extends BaseAdapter<String, DataAdapter.DataHolder> {


    public DataAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCustomViewType(int position) {
        return 100;
    }

    @Override
    public DataHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new DataHolder(parent, R.layout.item_list_view_male);
    }

    @Override
    public void bindCustomViewHolder(DataHolder holder, int position) {
        String data = getItem(position);
        holder.nameTv.setText(data);
    }

    static class DataHolder extends BaseHolder {

        TextView nameTv;

        DataHolder(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
            nameTv = getView(R2.id.name_tv);
        }
    }
}
