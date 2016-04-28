package com.my.actionbartab.three;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.my.actionbartab.R;

import java.lang.reflect.Method;

/**
 * 参考   http://blog.csdn.net/guolin_blog/article/details/25466665
 *
 * Created by Administrator on 2016/4/28.
 */
public class ThreeActivity extends Activity {

    private static final String TAG = "ThreeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //为Activity左上角加一个返回的小箭头（这个返回时直接返回！！！不是向Back键一层一层返回）
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 菜单的点击事件
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.three, menu);
        //分享ActionBar
        MenuItem shareItem=menu.findItem(R.id.action_4_three);
        ShareActionProvider provider= (ShareActionProvider) shareItem.getActionProvider();
        provider.setShareIntent(getDefaultIntent());

        return super.onCreateOptionsMenu(menu);
    }

    private Intent getDefaultIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return intent;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_1_three:
                Log.d(TAG, "" + item.getTitle().toString());
                Toast.makeText(ThreeActivity.this, "添加好友", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_2_three:
                Log.d(TAG, "" + item.getTitle().toString());
                Toast.makeText(ThreeActivity.this, "拍照", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_3_three:
                Log.d(TAG, "" + item.getTitle().toString());
                Toast.makeText(ThreeActivity.this, "邮件", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_4_three:
                Log.d(TAG, "" + item.getTitle().toString());
                Toast.makeText(ThreeActivity.this, "分享", Toast.LENGTH_SHORT).show();
                return true;

            case android.R.id.home:
                Log.d(TAG, "" + item.getTitle().toString());
                Toast.makeText(ThreeActivity.this, "finish当前的Activity", Toast.LENGTH_SHORT).show();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
