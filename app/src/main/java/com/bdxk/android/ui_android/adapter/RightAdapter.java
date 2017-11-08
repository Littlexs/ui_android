package com.bdxk.android.ui_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bdxk.android.ui_android.R;
import com.bdxk.android.ui_android.common.NoScrollGridView;
import com.bdxk.android.ui_android.entity.Classify;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BDXK on 2017/11/8.
 */

public class RightAdapter extends BaseAdapter {

    private Context context;
    private List<Classify.Child> childList;

    public RightAdapter(Context context, List<Classify.Child> childList) {
        this.context = context;
        this.childList = childList;
    }

    @Override
    public int getCount() {
        return childList.size();
    }

    @Override
    public Object getItem(int position) {
        return childList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.right_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Classify.Child child = childList.get(position);
        viewHolder.text.setText(child.getName());
        final RightItemAdapter rightItemAdapter = new RightItemAdapter(context,child.getItems());
        viewHolder.noScrollGridView.setAdapter(rightItemAdapter);
        rightItemAdapter.setChildItemClick(new RightItemAdapter.ItemClick() {
            @Override
            public void check(boolean isCheck, int position) {
                if (childItemClick!=null){
                    childItemClick.check(rightItemAdapter,child.getItems(),isCheck,position);
                }
            }
        });
//        viewHolder.noScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (childItemClick!=null){
//                    childItemClick.check(rightItemAdapter,child.getItems(),position);
//                }
//            }
//        });
        return convertView;
    }

//    private void updateName(ListView listview, List<Classify.Child.Item> items,RightItemAdapter rightItemAdapter, int position) {
//        rightItemAdapter.refresh(items);
//        int firstVisiblePosition = listview.getFirstVisiblePosition();
//        int lastVisiblePosition = listview.getLastVisiblePosition();
//        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
//            View view = listview.getChildAt(position - firstVisiblePosition);
//            if (view.getTag() instanceof ViewHolder) {
//                //获取指定view对应的ViewHolder
//                rightItemAdapter.refresh(items);
//            }
//        }
//    }

    private ChildItemClick childItemClick;

    public void setChildItemClick(ChildItemClick childItemClick){
        this.childItemClick = childItemClick;
    }

    public interface ChildItemClick{
        void check(RightItemAdapter rightItemAdapter,List<Classify.Child.Item> itemList,boolean isCheck, int position);
    }

    public void refresh( List<Classify.Child> childList){
        this.childList = childList;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.noScrollGridView)
        NoScrollGridView noScrollGridView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
