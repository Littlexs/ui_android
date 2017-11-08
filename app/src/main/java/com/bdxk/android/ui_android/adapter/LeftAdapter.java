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

public class LeftAdapter extends BaseAdapter {

    private Context context;
    private List<Classify> classifies;

    public LeftAdapter(Context context, List<Classify> classifies) {
        this.context = context;
        this.classifies = classifies;
    }

    @Override
    public int getCount() {
        return classifies.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return classifies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.left_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position==0){
            viewHolder.text.setText("全部行业");
        }else {
            Classify classify = classifies.get(position-1);
            viewHolder.text.setSelected(classify.isCheck());
            viewHolder.text.setText(classify.getType());
            viewHolder.tvInfo.setVisibility(classify.isCheckChild()?View.VISIBLE:View.GONE);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.tvInfo)
        TextView tvInfo;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
