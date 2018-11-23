package com.example.administrator.zhihunews.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.zhihunews.R;

import java.util.zip.Inflater;

/**
 * Created by Administrator on 2018/11/23.
 */

public class NewsListFragment extends BaseFragment {
    private View mView;

    // 初始化视图
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       mView =  inflater.inflate(R.layout.news_list_fragment_layout,container,false);
        return mView;
    }
    
    // 静态工厂方法获取Fragment实例
    public  static  NewsListFragment newInstance(){
        NewsListFragment newsListFragment = new NewsListFragment();
        return newsListFragment;
    }

}
