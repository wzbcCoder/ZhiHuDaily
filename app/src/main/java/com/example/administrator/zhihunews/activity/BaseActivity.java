package com.example.administrator.zhihunews.activity;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zhihunews.R;

/**
 * Created by Administrator on 2018/11/28.
 */

public class BaseActivity extends AppCompatActivity {
    public Toolbar toolbar;


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




}
