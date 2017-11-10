package com.bdxk.android.ui_android.popuwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bdxk.android.ui_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopuActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popu);
        ButterKnife.bind(this);
    }

    public void doClick(View view) {
        MyPopupWindow popupWindow = new MyPopupWindow(this);
        popupWindow.showPopupWindow(btn1);
    }
}
