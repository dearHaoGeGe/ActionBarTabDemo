package com.my.actionbartab.bean;

/**
 * 封装一个Tab的类
 * tab的文字
 *
 * Created by Administrator on 2016/4/27.
 */
public class MyTab {

    private String text;        //标题的文字
    private Class fragment;     //每一个tab所对应的页面fragment

    public MyTab(String text, Class fragment) {
        this.text = text;
        this.fragment = fragment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
