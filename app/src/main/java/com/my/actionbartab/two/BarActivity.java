package com.my.actionbartab.two;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import com.my.actionbartab.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 不能继承AppCompatActivity，继承之后会报错
 * ActionBar逐渐被ToolBar取代
 * 解释   http://www.zhihu.com/question/35709367
 * <p>
 * 参考   http://blog.csdn.net/guolin_blog/article/details/18234477
 * <p>
 * Created by Administrator on 2016/4/28.
 */
public class BarActivity extends Activity {

    private static final String TAG = "BarActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        setOverflowShowingAlways();     //设置手机显示三个点

        //为Activity左上角加一个返回的小箭头（这个返回时直接返回！！！不是向Back键一层一层返回）
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 创建菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //引入菜单
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        // 配置SearchView的属性
        //...

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 菜单的点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_1:
                Log.d(TAG, "" + item.getTitle().toString());
                Toast.makeText(BarActivity.this, "第一个", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_2:
                Log.d(TAG, "" + item.getTitle().toString());
                Toast.makeText(BarActivity.this, "第三个", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_3:
                Log.d(TAG, "" + item.getTitle().toString());
                Toast.makeText(BarActivity.this, "第四个", Toast.LENGTH_SHORT).show();
                return true;

            /**
             * 当点击ActionBar图标的时候，系统同样会调用onOptionsItemSelected()方法，
             * 并且此时的itemId是android.R.id.home
             */
            case android.R.id.home:
                Log.d(TAG, "" + item.getTitle().toString());
                Toast.makeText(BarActivity.this, "finish当前的Activity", Toast.LENGTH_SHORT).show();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 这个让点击三个点里面的item显示图片和文字的，如果不加这个只能显示文字
     *
     * @param featureId
     * @param menu
     * @return
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 在以前的手机中如果有menu键就不显示三个点，
     * 加上这个方法之后就告诉系统所有手机都没有menu键，
     * 这个就可以让所有手机都显示三个点
     */
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
