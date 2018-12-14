package com.example.administrator.zhihunews.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.zhihunews.R;

import org.jsoup.Connection;

/**
 * Created by Administrator on 2018/11/28.
 */

public class BaseActivity extends AppCompatActivity {
    public Toolbar toolbar;
    public ClipData.Item item;
    /**
     *
     * @param title 标题栏标题
     * @param type  标题类型，1为带菜单栏的标题栏，2为带back键的标题栏
     */
    public void setTitle(String title,int type){

//        toolbar= (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitleTextColor(Color.WHITE);//标题字体颜色
//        toolbar.setTitle(title);
        switch (type){
            case 1:
                toolbar= (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitleTextColor(Color.WHITE);//标题字体颜色
                toolbar.setTitle(title);
                setSupportActionBar(toolbar);//设置为actionbar
                toolbar.setNavigationIcon(R.drawable.layer);
                break;
            case 2:
                toolbar= (Toolbar) findViewById(R.id.toolbart);
                toolbar.setTitleTextColor(Color.WHITE);//标题字体颜色
                toolbar.setTitle(title);
                setSupportActionBar(toolbar);//设置为actionbar
                toolbar.setNavigationIcon(R.drawable.back);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
                break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void clickmoon(MenuItem item) {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        UiUtils.switchAppTheme(BaseActivity.this);
    }

    public void clickson(MenuItem item) {
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.rgb(63,81,181));

    }

//    private int theme = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        //切换主题必须放在onCreate()之前
//        if (savedInstanceState == null) {
//            theme = UiUtils.getAppTheme(BaseActivity.this);
//        } else {
//            theme = savedInstanceState.getInt("theme");
//        }
//        setTheme(theme);
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    public void click(MenuItem item) {
//        UiUtils.switchAppTheme(BaseActivity.this);
//        toolbar= (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setBackgroundColor(Color.BLACK);
//        reload();
//    }
//
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);//进入动画
        finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        startActivity(intent);

    }
}
