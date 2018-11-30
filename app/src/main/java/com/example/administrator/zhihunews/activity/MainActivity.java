package com.example.administrator.zhihunews.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
//import com.example.administrator.zhihunews.db.until.DatabaseHelper;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.db.until.MyDatabaseHelper;
import com.example.administrator.zhihunews.fragment.BaseFragment;
import com.example.administrator.zhihunews.fragment.NewsDetailFragment;
import com.example.administrator.zhihunews.fragment.NewsListFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends BaseActivity {
    FragmentManager mFragmentManger;
    // Fragment 事务
    FragmentTransaction mFragmentTransaction;
    MyDatabaseHelper mMySqliteHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewsListFragment newsListFragment = NewsListFragment.newInstance();
        switchFragment(newsListFragment);
        initView();
    }

    private void initView() {
        setTitle("首页", 1);//设置标题内容，该方法继承自父类，所以再写一次
    }

    //    // 跳转到新闻细节fragment
//    public void switchFragment(NewsDetailFragment newsDetailFragment){
//        mFragmentTransaction.replace(R.id.NewsListFragment, newsDetailFragment);
//        // 提交
//        mFragmentTransaction.commit();
//    }
    private void perpareFragmentManager() {
        // 通用的FragmentManager
        mFragmentManger = getSupportFragmentManager();
        mFragmentTransaction = getFragmentManager().beginTransaction();
    }

    // 创建Fragment之前的操作
    public void switchFragment(Fragment fragment) {

        // 准备FragmentManager
        perpareFragmentManager();

        mFragmentTransaction.replace(R.id.NewsListFragment, fragment);
        // 提交
        mFragmentTransaction.commit();
    }


}
