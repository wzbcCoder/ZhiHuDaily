package com.example.administrator.zhihunews.fragment;

import android.os.Bundle;

/**
 * Created by Administrator on 2018/12/12.
 */

public class CommentsFragment extends NewsDetailFragment {
    public static CommentsFragment newInstance(int newsId) {
        //TODO(ZJD):添加返回操作
        CommentsFragment fragment = new CommentsFragment();
        Bundle args = new Bundle();
        args.putInt("NewsId", newsId);
        fragment.setArguments(args);
        return fragment;
    }
}
