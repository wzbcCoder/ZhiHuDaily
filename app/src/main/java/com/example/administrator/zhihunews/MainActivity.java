package com.example.administrator.zhihunews;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.zhihunews.fragment.NewsListFragment;

public class MainActivity extends AppCompatActivity {
    FragmentManager mFragmentManger;
    // Fragment 事务
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareFragment();
    }
    // 创建Fragment之前的操作
    private void prepareFragment(){
        // 通用的FragmentManager
        mFragmentManger = getSupportFragmentManager();
        mFragmentTransaction  = getFragmentManager().beginTransaction();
        NewsListFragment newsListFragment = NewsListFragment.newInstance();
        mFragmentTransaction.replace(R.id.NewsListFragment,newsListFragment);
        // 提交
        mFragmentTransaction.commit();
    }
}
