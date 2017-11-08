package com.bdxk.android.ui_android.ui.grid_viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bdxk.android.ui_android.R;
import com.bdxk.android.ui_android.adapter.BasePageAdapter;
import com.bdxk.android.ui_android.adapter.MyBaseAdapter;
import com.bdxk.android.ui_android.adapter.ViewHolder;
import com.bdxk.android.ui_android.common.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.line_point)
    LinearLayout linePoint;//指示器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_pager);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        final List<String> gridList = new ArrayList<>();
        for (int i = 0;i<42;i++){
            gridList.add("test"+(i+1));
        }
        List<View> list1 = new ArrayList<>();//页面
        final List<LinearLayout> pointViews = new ArrayList<>();//指示器
        int size = gridList.size();//item 的数量
        int pageNum = size/20;//整数个页面
        pageNum = size%20 > 0 ? ++pageNum : pageNum;//如果余数大于零，即不是20的整数倍，则页面加一
        for (int i = 0;i<pageNum;i++){
            final List<String> items = new ArrayList<>();
            items.addAll(gridList.subList(i*20,i*20+20>=size-1?size:i*20+20));
            NoScrollGridView gridView = (NoScrollGridView) LayoutInflater.from(this).inflate(R.layout.index_grid_layout,null);
            gridView.setAdapter(new MyBaseAdapter<String>(this,items,R.layout.index_grid_item) {
                @Override
                public void convert(ViewHolder helper, String item, int position) {
                    ((TextView)helper.getView(R.id.tvType))
                            .setText(items.get(position));
                }
            });
            list1.add(gridView);
            //创建指示器
            LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.index_grid_point,null);
            view.getChildAt(0).setBackgroundColor(Color.parseColor("#bbbbbb"));
            linePoint.addView(view);
            pointViews.add(view);
        }
        pointViews.get(0).getChildAt(0).setBackgroundColor(Color.parseColor("#666666"));
        viewPager.setAdapter(new BasePageAdapter(list1));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (LinearLayout view:pointViews){
                    view.getChildAt(0).setBackgroundColor(Color.parseColor("#bbbbbb"));
                }
                pointViews.get(position).getChildAt(0).setBackgroundColor(Color.parseColor("#666666"));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
