package com.bdxk.android.ui_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.bdxk.android.ui_android.ui.classify.ClassifyActivity;
import com.bdxk.android.ui_android.ui.grid_viewpager.GridViewPagerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void openActivity(Class<?> clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }

    @OnClick(R.id.btn1)
    public void onViewClicked() {
        openActivity(GridViewPagerActivity.class);
    }
    @OnClick(R.id.btn2)
    public void onViewClicked2() {
        openActivity(ClassifyActivity.class);
    }
}
