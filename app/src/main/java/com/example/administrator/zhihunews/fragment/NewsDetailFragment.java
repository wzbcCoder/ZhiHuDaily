package com.example.administrator.zhihunews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.administrator.zhihunews.R;

/**
 * Created by Administrator on 2018/11/30.
 */

public class NewsDetailFragment extends BaseFragment {
    private View mView;
    private WebView mDetail;
    // 新闻的id 从外部传参
    private int mNewsId;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.news_detail_fragment_layout, container, false);
        return mView;
    }

    private  void  initView(){
        mDetail = (WebView)mActivity.findViewById(R.id.web_view_detail);
    }
    private  void  initData(){
        mNewsId = getArguments().getInt("NewsId");

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }
    public static NewsDetailFragment newInstance(int  newsId) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putInt("NewsId", newsId);
        fragment.setArguments(args);
        return fragment;
    }

}
