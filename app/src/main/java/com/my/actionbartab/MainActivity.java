package com.my.actionbartab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.my.actionbartab.one.TabActivity;
import com.my.actionbartab.three.ThreeActivity;
import com.my.actionbartab.two.BarActivity;

/**
 * Created by Administrator on 2016/4/28.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_mainAct_intoTabActivity;
    private Button btn_mainAct_intoBarActivity;
    private Button btn_mainAct_intoThreeActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_mainAct_intoTabActivity = (Button) findViewById(R.id.btn_mainAct_intoTabActivity);
        btn_mainAct_intoBarActivity = (Button) findViewById(R.id.btn_mainAct_intoBarActivity);
        btn_mainAct_intoThreeActivity= (Button) findViewById(R.id.btn_mainAct_intoThreeActivity);

        btn_mainAct_intoTabActivity.setOnClickListener(this);
        btn_mainAct_intoBarActivity.setOnClickListener(this);
        btn_mainAct_intoThreeActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mainAct_intoTabActivity:
                startActivity(new Intent(this, TabActivity.class));
                break;

            case R.id.btn_mainAct_intoBarActivity:
                startActivity(new Intent(this, BarActivity.class));
                break;

            case R.id.btn_mainAct_intoThreeActivity:
                startActivity(new Intent(this, ThreeActivity.class));
                break;
        }
    }
}
