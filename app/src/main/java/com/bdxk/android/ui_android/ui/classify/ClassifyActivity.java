package com.bdxk.android.ui_android.ui.classify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bdxk.android.ui_android.R;
import com.bdxk.android.ui_android.adapter.LeftAdapter;
import com.bdxk.android.ui_android.adapter.RightAdapter;
import com.bdxk.android.ui_android.adapter.RightItemAdapter;
import com.bdxk.android.ui_android.entity.Classify;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassifyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, RightAdapter.ChildItemClick, AbsListView.OnScrollListener {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.leftList)
    ListView leftList;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.rightList)
    ListView rightList;

    List<Classify> classifies = new ArrayList<>();//分类列表
    List<Classify.Child> childList = new ArrayList<>();
    Classify leftTarget;//左侧选中的条目
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private int selectCount;//右侧选中的子菜单数
    private int leftSelectCount;//左侧选中的菜单数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        ButterKnife.bind(this);
        initClassify();//模拟数据
        initView();
    }

    private void initView() {
        //右侧分类
        leftAdapter = new LeftAdapter(this, classifies);
        leftList.setAdapter(leftAdapter);
        leftList.setOnItemClickListener(this);
        //左侧分类
        rightAdapter = new RightAdapter(this, childList);
        rightList.setAdapter(rightAdapter);
        rightAdapter.setChildItemClick(this);
        rightList.setOnScrollListener(this);
    }

    private void initClassify() {
        for (int i = 0; i < 20; i++) {
            List<Classify.Child> childList = new ArrayList<>();//分类列表
            for (int j = 0; j < 5; j++) {
                List<Classify.Child.Item> items = new ArrayList<>();
                for (int a = 0; a < 12; a++) {
                    Classify.Child.Item item = new Classify.Child.Item();
                    item.setName("test" + (a + 1));
                    item.setCheck(false);//默认都不选中
                    items.add(item);
                }
                Classify.Child child = new Classify.Child("child" + (i+1) + "-" + (j+1), items);
                childList.add(child);
            }
            Classify classify = new Classify(String.valueOf(i + 1), childList);
            classify.setCheck(false);//默认都不选中
            classify.setCheckChild(false);//默认没有选择子菜单
            if (i == 0) {
                classify.setCheck(true);//默认第一个选中
                leftTarget = classify;
            }
            classifies.add(classify);
        }
        this.childList.clear();
        this.childList.addAll(classifies.get(0).getChildList());
    }

    /**
     * 左侧点击回调
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) return;
        for (Classify classify : classifies) {
            classify.setCheck(false);
        }
        classifies.get(position - 1).setCheck(true);
        leftTarget = classifies.get(position - 1);
        if (leftTarget.getChildList()==null || leftTarget.getChildList().size()==0){
            tvInfo.setText("点击可以多选，限制3个");
            leftAdapter.notifyDataSetChanged();
            rightAdapter.refresh(new ArrayList<Classify.Child>());
        }else {
            tvInfo.setText(leftTarget.getChildList().get(0).getName());
            leftAdapter.notifyDataSetChanged();
            rightAdapter.refresh(classifies.get(position - 1).getChildList());
        }
    }

    /**
     * 选择右侧子选项回调
     *
     */
    @Override
    public void check(RightItemAdapter rightItemAdapter, List<Classify.Child.Item> itemList, boolean isCheck, int position) {
        if (selectCount<3 && !isCheck){
            selectCount++;
            itemList.get(position).setCheck(!isCheck);
            rightItemAdapter.refresh(itemList);
            leftTarget.setCheckChild(selectCount!=0);
            leftAdapter.notifyDataSetChanged();
        }else if (isCheck){
            selectCount = selectCount==0?0:--selectCount;
            itemList.get(position).setCheck(!isCheck);
            rightItemAdapter.refresh(itemList);
            leftTarget.setCheckChild(selectCount!=0);
            leftAdapter.notifyDataSetChanged();
        }else {
            Toast.makeText(this,"最多选3个子菜单",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 右边list的滑动监听
     *
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    /**
     * 右边list的滑动监听
     *
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (totalItemCount!=0){
            tvInfo.setText(leftTarget.getChildList().get(firstVisibleItem).getName());
        }
    }
}
