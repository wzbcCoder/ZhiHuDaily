package com.example.administrator.zhihunews.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.TextViewCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.decoration.SectionDecoration;

import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/28.
 */

public class BaseActivity extends AppCompatActivity {
    public Toolbar toolbar;
    public Toolbar toolbart;
    public TextView disanyeText;
    public ClipData.Item item;
    public boolean aBoolean = true;

    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;
    private TextView indextextView;

    private TextView tv_content;




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
                disanyeText = (TextView) findViewById(R.id.firstpage);
                disanyeText.setText("首页");
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
                        disanyeText = (TextView) findViewById(R.id.firstpage);
                        disanyeText.setText("首页");
                    }
                });
                break;
            case 3:
                toolbar= (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitleTextColor(Color.WHITE);//标题字体颜色
                toolbar.setTitle(title);
                disanyeText = (TextView) findViewById(R.id.firstpage);
                disanyeText.setText("评论区");
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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
//        return true;
//    }

//    //TODO menu点击事件
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.i_son:
//                SectionDecoration.aBoolean = true;
//                toolbar = (Toolbar) findViewById(R.id.toolbar);
////                toolbart = (Toolbar) findViewById(R.id.toolbart);
//                toolbar.setBackgroundColor(Color.rgb(63, 81, 181));
////                toolbart.setBackgroundColor(Color.rgb(63, 81, 181));
//                relativeLayout = (RelativeLayout) findViewById(R.id.index);
//                relativeLayout.setBackgroundColor(Color.rgb(242, 241, 241));
//                linearLayout = (LinearLayout) findViewById(R.id.base_swipe_item_container);
//                linearLayout.setBackgroundColor(Color.WHITE);
//                indextextView = (TextView) findViewById(R.id.item_title);
//                indextextView.setTextColor(Color.BLACK);
//                break;
//            case R.id.i_moon:
//                SectionDecoration.aBoolean = false;
//                toolbar= (Toolbar) findViewById(R.id.toolbar);
////                toolbart= (Toolbar) findViewById(R.id.toolbart);
//                toolbar.setBackgroundColor(Color.rgb(67,67,67));
////                toolbart.setBackgroundColor(Color.rgb(242,241,241));
//
//                relativeLayout = (RelativeLayout) findViewById(R.id.index);
//                relativeLayout.setBackgroundColor(Color.rgb(67,67,67));
////                linearLayout = (LinearLayout) findViewById(R.id.base_swipe_item_container);
////                linearLayout.setBackgroundColor(Color.rgb(108,108,108));
////                indextextView = (TextView) findViewById(R.id.item_title);
////                indextextView.setTextColor(Color.rgb(206,205,205));
//                break;
//            default:
//                break;
//        }
//        return true;
//    }


    public void clickson(MenuItem item) {
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.rgb(63, 81, 181));
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


    public void changeColor(){
        ArrayList<Map<String, String>> houseRes = new ArrayList<>();

        RecyclerView recy = (RecyclerView) findViewById(R.id.infolist);

        for (int i = 0; i < recy.getChildCount(); i++) {
//            Map<String, String> mDeviceHeaderMap = new HashMap<>();
            LinearLayout layout = (LinearLayout) recy.getChildAt(i);
            tv_content = (TextView) layout.findViewById(R.id.item_title);
            LinearLayout tv_description = (LinearLayout)layout.findViewById(R.id.base_swipe_item_container);
            tv_content.setTextColor(Color.rgb(206,205,205));

//            mDeviceHeaderMap.put(tv_content.getText().toString(), tv_description.getText().toString());

//            houseRes.add(mDeviceHeaderMap);
        }
    }
}
