package com.example.administrator.zhihunews.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2018/11/23.
 */

public class ClintApplication extends Application {
    // 请求队列
    private static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue= Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }


}
