package com.bdxk.android.ui_android.entity;

import java.util.List;

/**
 * Created by BDXK on 2017/11/8.
 * 分类菜单
 */

public class Classify {

    private String type;
    private List<Child> childList;
    private boolean isCheck;//是否选中，自己添加的字段，跟后台返回无关
    private boolean isCheckChild;//是否有选中子菜单，自己添加的字段，跟后台返回无关

    public Classify(String type, List<Child> childList) {
        this.type = type;
        this.childList = childList;
    }

    public boolean isCheckChild() {
        return isCheckChild;
    }

    public void setCheckChild(boolean checkChild) {
        isCheckChild = checkChild;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public static class Child{
        private String name;
        private List<Item> items;

        public Child(String name, List<Item> items) {
            this.name = name;
            this.items = items;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public static class Item{
            private String name;
            private boolean isCheck;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }
        }
    }


}
