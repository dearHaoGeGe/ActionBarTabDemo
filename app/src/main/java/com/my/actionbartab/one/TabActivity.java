package com.my.actionbartab.one;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.my.actionbartab.R;
import com.my.actionbartab.bean.MyTab;
import com.my.actionbartab.fragment.ChatFragment;
import com.my.actionbartab.fragment.FriendFragment;
import com.my.actionbartab.fragment.MomentFragment;
import com.my.actionbartab.fragment.MoreFragment;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {

    private List<MyTab> tabList = new ArrayList<>();
    private ViewPager viewPager;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        //添加4个fragment到自定义的Tab中去
        tabList.add(new MyTab("聊天", ChatFragment.class));
        tabList.add(new MyTab("朋友", FriendFragment.class));
        tabList.add(new MyTab("时刻", MomentFragment.class));
        tabList.add(new MyTab("更多", MoreFragment.class));

        initActionBar();
    }

    private void initActionBar() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        actionBar = getSupportActionBar();        //获得v7中的ActionBar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);    // 设置为导航模式

        // 通过循环遍历将创建的四个Tab加入到actionBar中
        for (MyTab tab : tabList) {
            ActionBar.Tab t = actionBar.newTab();     // 使用v7下边的Tab
            t.setText(tab.getText());
            t.setTabListener(this);                 // 设置TabListener监听器
            actionBar.addTab(t);
        }
        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(this);
    }

    /***************************************
     * 下边是OnPageChangeListener实现的方法
     ***************************************/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        actionBar.selectTab(actionBar.getTabAt(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 为viewpager设置的适配器
     */
    private class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            try {
                return (Fragment) tabList.get(position).getFragment().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabList.size();
        }
    }


    /********************************
     * 下边是TabListener所实现的方法
     ********************************/

    /**
     *  当Tab被单次选中
     */
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    /**
     *
     */
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    /**
     *  当Tab被双次选中
     */
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
