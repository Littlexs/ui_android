package com.bdxk.android.ui_android.popuwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.bdxk.android.ui_android.R;
import com.bdxk.android.ui_android.common.DeviceUtils;

/**
 * Created by BDXK on 2017/11/9.
 */

public class MyPopupWindow extends PopupWindow implements PopupWindow.OnDismissListener{

    private Activity activity;
    private int w,h;//弹出框的高宽
    private PopupWindow popupWindow;

    public MyPopupWindow(Activity activity){
        this.activity = activity;
        initView();
    }

    private void initView(){
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setContentView(LayoutInflater.from(activity).inflate(R.layout.popup_layout1,null));//设置布局
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setAnimationStyle(R.style.SelectPopupWindow);
        this.setOutsideTouchable(true);
        this.setFocusable(true);
        this.setOnDismissListener(this);
    }

    //显示
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            setBg(true);
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

    //消失
    public void dismissPopup(){
        popupWindow.dismiss();
    }


    /**
     * 弹出时屏幕变暗，否则正常
     * @param on   是否弹出
     */
    private void setBg(boolean on) {
        WindowManager.LayoutParams lp=activity.getWindow().getAttributes();
        lp.alpha=on?0.3f:1.0f;
        activity.getWindow().setAttributes(lp);
    }

    @Override
    public void onDismiss() {
        setBg(false);
    }
}
