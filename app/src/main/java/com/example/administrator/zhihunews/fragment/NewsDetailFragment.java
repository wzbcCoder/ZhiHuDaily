package com.example.administrator.zhihunews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.zhihunews.R;
import com.example.administrator.zhihunews.app.ClintApplication;
import com.example.administrator.zhihunews.db.daoImp.NewsItemDaoImp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/30.
 */

public class NewsDetailFragment extends BaseFragment {
    private View mView;
    private WebView mDetail;
    // 新闻的id 从外部传参
    private int mNewsId;
    private final String tag = "NewsDetailFragment";

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.news_detail_fragment_layout, container, false);
        return mView;
    }

    private void initView() {
        mDetail = (WebView) mActivity.findViewById(R.id.web_view_detail);
    }

    private void initData() {
        mNewsId = getArguments().getInt("NewsId");

        Log.d(tag, "news id is  " + mNewsId);
        fetchNewsDetail();
    }

    // 调用api 获取制定id的新闻
    private void fetchNewsDetail() {
        // 细节新闻的地址
        String url = "http://news-at.zhihu.com/api/4/news/" + mNewsId;
        System.out.println(url);
        StringRequest mRequest = new StringRequest(Request.Method.GET, url,

                // 响应成功
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        HashMap<String,String> newsHashMap= parseNewsDetail(response);
                        String htmlNewsDetail = matchDetailHTML(newsHashMap);


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // 响应失败
                        System.out.println("error ----");
                    }
                }
        );
        ClintApplication.getmRequestQueue().add(mRequest);
    }

    private HashMap<String, String> parseNewsDetail(String newsDetailJson) {
        JSONObject newsDetailJsonObject;
        HashMap<String,String> newsHashMap = new HashMap<>();
        try {
            newsDetailJsonObject = new JSONObject(newsDetailJson);
            JSONArray cssList = (JSONArray) newsDetailJsonObject.get("css");
            String css = (String) cssList.get(0);
            String body = (String)newsDetailJsonObject.get("body");
            String image = (String) newsDetailJsonObject.get("image");
            String js = (String) ((JSONArray) newsDetailJsonObject.get("css")).get(0);
            String title = (String) newsDetailJsonObject.get("title");
            newsHashMap.put("css",css);
            newsHashMap.put("body",body);
            newsHashMap.put("image",image);
            newsHashMap.put("js",js);
            newsHashMap.put("title",title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsHashMap;
    }

    // 读取newsHashMap中的数据 转换为html
    private  String  matchDetailHTML(HashMap<String,String> newsHashMap){
        //TODO:添加对hashMap的解析 然后拼接成HTML
        return newsHashMap.get("body");
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    public static NewsDetailFragment newInstance(int newsId) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putInt("NewsId", newsId);
        fragment.setArguments(args);
        return fragment;
    }

}
