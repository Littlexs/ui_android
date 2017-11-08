package com.bdxk.android.ui_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bdxk.android.ui_android.R;
import com.bdxk.android.ui_android.entity.Classify;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BDXK on 2017/11/8.
 */

public class RightItemAdapter extends BaseAdapter {

    private Context context;
    private List<Classify.Child.Item> itemList;

    public RightItemAdapter(Context context, List<Classify.Child.Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.right_item_child, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Classify.Child.Item item = itemList.get(position);
        viewHolder.text.setText(item.getName());
        viewHolder.text.setSelected(item.isCheck());
        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childItemClick!=null){
                    childItemClick.check(item.isCheck(),position);
                }
            }
        });
        return convertView;
    }

    private ItemClick childItemClick;

    public void setChildItemClick(ItemClick childItemClick){
        this.childItemClick = childItemClick;
    }

    public interface ItemClick{
        void check(boolean isCheck, int position);
    }

    public void refresh( List<Classify.Child.Item> childList){
        this.itemList = childList;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.text)
        TextView text;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
