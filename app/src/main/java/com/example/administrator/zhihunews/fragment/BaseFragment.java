package com.example.administrator.zhihunews.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.zhihunews.activity.BaseActivity;

/**
 * Created by Administrator on 2018/11/23.
 */

public abstract class BaseFragment extends Fragment {

    //这个activity就是MainActivity
    public Activity mActivity;
    public BaseActivity baseActivity;


    // Fragment被创建
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();// 获取所在的activity对象
    }

    // 初始化Fragment布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = initView(inflater,container,savedInstanceState);
            return view;
    }

    //初始化布局, 子类必须实现
    protected abstract View initView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState);


}
